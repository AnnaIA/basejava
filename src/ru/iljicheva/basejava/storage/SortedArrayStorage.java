package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteExistingObj(int index) {
        for (int j = index; j <= size; j++) {
            storage[j] = storage[j + 1];
        }
    }

    @Override
    protected void saveObj(int index, Resume r) {
        index = Math.abs(index) - 1;
        if (index < size) {
            for (int i = size; i > index; i--) {
                storage[i] = storage[i + 1];
            }
        }
        storage[index] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}