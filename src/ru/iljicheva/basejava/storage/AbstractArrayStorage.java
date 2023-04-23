package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    /*public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            res.append(storage[i].getUuid()).append(", ");
        }
        return res.toString();
    }*/

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(List.of(Arrays.copyOf(storage, size)));
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey > -1;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        insertElement((Integer) searchKey, r);
        size++;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doDelete(Object searchKey) {
        fillDeletedElement((Integer) searchKey);
        size--;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(int index, Resume r);

}