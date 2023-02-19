package ru.Iljicheva.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.iljicheva.basejava.exception.ExistStorageException;
import ru.iljicheva.basejava.exception.NotExistStorageException;
import ru.iljicheva.basejava.exception.StorageException;
import ru.iljicheva.basejava.model.Resume;
import ru.iljicheva.basejava.storage.ArrayStorage;
import ru.iljicheva.basejava.storage.Storage;

import static org.junit.Assert.assertEquals;

public class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume resume4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        asserEqualesSize(3);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingResume() throws Exception {
        Resume newResume = new Resume(UUID_4);
        storage.update(newResume);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
    }

    @Test
    public void save() throws Exception {
        storage.save(resume4);
        assertEquals(resume4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() throws Exception {
        storage.save(resume3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveToOverflowArray() throws Exception {
        try {
            for (int i = 0; i < ArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        asserEqualesSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistingResume() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        storage.save(resume4);
        Resume resume = storage.get(resume4.getUuid());
        assertEquals(resume, resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        asserEqualesSize(0);
    }

    public void asserEqualesSize(int size){
        assertEquals(size, storage.size());
    }
}
