package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapStorage extends AbstractStorage {
    protected HashMap<String, Resume> storage = new HashMap<String, Resume>();

    /*public String toString() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Resume> r : storage.entrySet()) {
            res.append(r.getKey()).append(", ");
        }
        return res.toString();
    }*/

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(searchKey.toString(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey.toString());
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey.toString());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
