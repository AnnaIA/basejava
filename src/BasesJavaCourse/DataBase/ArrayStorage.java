package BasesJavaCourse.DataBase;

import BasesJavaCourse.Info.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (resumeLocation(r.uuid) == -1 && size < 10000) {
            storage[size] = r;
            size++;
        } else {
            System.out.printf("The resume with uuid = %s did not save. This uuid exists or storage is crowded\n", r.uuid);
        }
    }

    public Resume get(String uuid) {
        int resumeLocation = resumeLocation(uuid);
        if (resumeLocation > -1){
            return storage[resumeLocation];
        }
        System.out.printf("The resume with uuid = %s does not exist\n", uuid);
        return null;
    }

    public void delete(String uuid) {
        int resumeLocation = resumeLocation(uuid);
        if (resumeLocation(uuid) > -1) {
            for (int j = resumeLocation; j < size; j++) {
                storage[j] = storage[j + 1];
            }
            size--;
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
        int resumeLocation = resumeLocation(r.uuid);
        if ( resumeLocation > -1) {
            storage[resumeLocation] = r;
            System.out.printf("The resume with uuid = %s has been updated\n", r.uuid);
        } else {
            System.out.printf("The resume with uuid = %s does not exist\n", r.uuid);
        }
    }

    private int resumeLocation(String uuid) {
        Resume resume = new Resume();
        resume.uuid = uuid;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(resume.uuid)){   //checking that the resume exists in the storage
                return i;                               //return resume location in the storage
            }
        }
        return -1;
    }
}
