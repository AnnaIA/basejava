package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void deleteExistingObj(int index) {
        storage[index] = storage[size - 1];
        storage[size] = null;
    }

    @Override
    protected void saveObj(int index, Resume r) {
        storage[size] = r;
    }


    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {   //checking that the resume exists in the storage
                return i;                               //return resume location in the storage
            }
        }
        return -1;
    }
}
