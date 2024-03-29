package ru.iljicheva.basejava;

import ru.iljicheva.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Class<Resume> mClassObject = Resume.class;
        Method method = mClassObject.getDeclaredMethod("toString");
        System.out.println(method.invoke(r));
        System.out.println(r);
    }
}
