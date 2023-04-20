package ru.iljicheva.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArrayStorageTest.class,
                SortedStorageTest.class,
                ListStorageTest.class,
                MapStorageTest.class,
                MapStorageNewKeyTest.class
        })
public class AllStorageTest {
}
