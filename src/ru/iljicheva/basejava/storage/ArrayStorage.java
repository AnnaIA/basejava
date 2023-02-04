package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage has been cleared.");
    }

    public void save(Resume r) {
        if (size > STORAGE_LIMIT){
            System.out.printf("The resume with uuid = %s did not save. Storage is crowded.\n", r.uuid);
        } else if (getIndex(r.uuid) > 0){
            System.out.printf("The resume with uuid = %s did not save. This uuid exists.\n", r.uuid);
        } else {
            storage[size] = r;
            size++;
            System.out.printf("The resume with uuid = %s has been saved successful.\n", r.uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0){
            System.out.printf("The resume with uuid = %s does not exist\n", uuid);
            return null;

        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (getIndex(uuid) > -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            System.out.printf("The resume with uuid = %s has been deleted successful\n", uuid);
        } else {
            System.out.printf("The resume with uuid = %s does not exist\n", uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        int resumeLocation = getIndex(r.uuid);
        if ( resumeLocation > -1) {
            storage[resumeLocation] = r;
            System.out.printf("The resume with uuid = %s has been updated\n", r.uuid);
        } else {
            System.out.printf("The resume with uuid = %s does not exist\n", r.uuid);
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)){   //checking that the resume exists in the storage
                return i;                               //return resume location in the storage
            }
        }
        return -1;
    }
}
