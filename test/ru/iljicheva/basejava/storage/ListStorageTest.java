package ru.iljicheva.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void saveToOverflowArray(){
    }
}
