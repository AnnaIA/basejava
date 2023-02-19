package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.exception.ExistStorageException;
import ru.iljicheva.basejava.exception.NotExistStorageException;
import ru.iljicheva.basejava.exception.StorageException;
import ru.iljicheva.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size > STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (getIndex(r.getUuid()) > 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(index, r);
            size++;
            System.out.printf("The resume with uuid = %s has been saved successful.\n", r.getUuid());
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage has been cleared.");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (getIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            System.out.printf("The resume with uuid = %s has been deleted successful\n", uuid);
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
            System.out.printf("The resume with uuid = %s has been updated\n", r);
        }
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(int index, Resume r);

    protected abstract int getIndex(String uuid);
}