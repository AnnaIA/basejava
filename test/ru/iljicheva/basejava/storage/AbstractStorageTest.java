package ru.iljicheva.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.iljicheva.basejava.exception.ExistStorageException;
import ru.iljicheva.basejava.exception.NotExistStorageException;
import ru.iljicheva.basejava.exception.StorageException;
import ru.iljicheva.basejava.model.Resume;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractStorageTest {
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final Resume RESUME_NOT_EXIST;

    static {
        RESUME_1 = new Resume(UUID_1, "name 1");
        RESUME_2 = new Resume(UUID_2, "name 2");
        RESUME_3 = new Resume(UUID_3, "name 3");
        RESUME_4 = new Resume(UUID_4, "name 4");
        RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST, "name");
    }

    private final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistingResume() throws Exception {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAllSorted().toArray(new Resume[0]);
        if (storage.getClass().isInstance(new MapStorage()) || storage.getClass().isInstance(new MapStorageNewKey())) {
            assertEquals(storage.size(), expected.length);
        } else {
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() throws Exception {
        storage.save(RESUME_3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < ArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        assertSize(2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistingResume() throws Exception {
        storage.delete(UUID_4);
        assertGet(RESUME_4);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        assertGet(RESUME_NOT_EXIST);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume r) {
        Resume resume = storage.get(r.getUuid());
        assertEquals(resume, r);
    }
}
