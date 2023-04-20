package ru.iljicheva.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Ignore
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveOverflow() {
    }
}
