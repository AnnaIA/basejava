package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
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
            System.out.printf("The resume with uuid = %s did not save. Storage is crowded.\n", r.getUuid());
        } else if (getIndex(r.getUuid()) > 0) {
            System.out.printf("The resume with uuid = %s did not save. This uuid exists.\n", r.getUuid());
        } else {
            saveObj(index, r);
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
        if (getIndex(uuid) > -1) {
            deleteExistingObj(index);
            System.out.printf("The resume with uuid = %s has been deleted successful\n", uuid);
            size--;
        } else {
            System.out.printf("The resume with uuid = %s does not exist\n", uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.printf("The resume with uuid = %s does not exist\n", uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
            System.out.printf("The resume with uuid = %s has been updated\n", r);
        } else {
            System.out.printf("The resume with uuid = %s does not exist\n", r);
        }
    }

    protected abstract void deleteExistingObj(int index);

    protected abstract void saveObj(int index, Resume r);

    protected abstract int getIndex(String uuid);
}