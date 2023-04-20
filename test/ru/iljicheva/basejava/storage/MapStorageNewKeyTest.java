package ru.iljicheva.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageNewKeyTest extends AbstractStorageTest {

    public MapStorageNewKeyTest() {
        super(new MapStorageNewKey());
    }

    @Override
    @Ignore
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveOverflow() {
    }
}
