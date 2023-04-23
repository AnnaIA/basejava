package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Resume> r : storage.entrySet()) {
            res.append(r.getKey()).append(", ");
        }
        return res.toString();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
