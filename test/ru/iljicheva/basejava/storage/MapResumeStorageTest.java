package ru.iljicheva.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapResumeStorageTest extends AbstractStorageTest {

    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }

    @Override
    @Ignore
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveOverflow() {
    }
}
