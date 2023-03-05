package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
        storage[size] = null;
    }

    @Override
    protected void insertElement(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            //System.out.println(storage[i]);
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
