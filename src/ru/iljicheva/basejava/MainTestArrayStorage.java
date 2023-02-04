package ru.iljicheva.basejava;

import ru.iljicheva.basejava.storage.ArrayStorage;
import ru.iljicheva.basejava.model.Resume;

/**
 * Test for your BasesJavaCourse.DataBase.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.uuid = "uuid1";
        Resume r2 = new Resume();
        r2.uuid = "uuid2";
        Resume r3 = new Resume();
        r3.uuid = "uuid3";

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("\nGet r1: " + ARRAY_STORAGE.get(r1.uuid));
        System.out.printf("Size: %d\n", ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.printf("\nUpdate resume with uuid = %s \n", r3.uuid);
        ARRAY_STORAGE.update(r3);
        printAll();

        System.out.printf("\nDelete resume with uuid = %s \n", r1.uuid);
        ARRAY_STORAGE.delete(r1.uuid);
        printAll();

        System.out.println("\nClear storage.");
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
