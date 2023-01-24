/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int pointer = 0;

    void clear() {
        for (int i = 0; i <= pointer; i++) {
            if (storage[i] == null){
                break;
            }
            storage[i] = null;
        }
        pointer = 0;
    }

    void save(Resume r) {
        storage[pointer] = r;
        pointer++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < pointer; i++) {
            if(storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= pointer; i++) {
            if(storage[i].uuid.equals(uuid)){
                for (int j = i; j <= pointer; j++) {
                    if(storage[j+1] != null) {
                        storage[j] = storage[j + 1];
                    }
                }
                pointer--;
                storage[pointer] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[pointer];
        for (int i = 0; i < pointer; i++) {
            resume[i] = storage[i];
        }
        //System.arraycopy(storage, 0, resume, 0, pointer); The same function may be like that.
        return resume;
    }

    int size() {
        return pointer;
    }
}
