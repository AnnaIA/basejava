package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.exception.ExistStorageException;
import ru.iljicheva.basejava.exception.NotExistStorageException;
import ru.iljicheva.basejava.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    final static Comparator<Resume> uuidComparator = Comparator.comparing(Resume::getUuid);
    final static Comparator<Resume> fullNameComparator = Comparator.comparing(Resume::getFullName);
    final static Comparator<Resume> pcomp = uuidComparator.thenComparing(fullNameComparator);

    protected HashMap<String, Resume> storage = new HashMap<String, Resume>();
    @Override
    public void update(Resume r) {
        Object searchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        list.sort(pcomp);
        return list;
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);
    protected abstract List<Resume> doGetAll();

    protected abstract void doDelete(Object searchKey);
}
