package ru.iljicheva.basejava.storage;

import ru.iljicheva.basejava.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapStorageNewKey extends AbstractStorage {
    final Comparator<Resume> uuidComparator = Comparator.comparing(Resume::getUuid);
    final Comparator<Resume> fullNameComparator = Comparator.comparing(Resume::getFullName);
    final Comparator<Resume> pcomp = uuidComparator.thenComparing(fullNameComparator);
    protected HashMap<String, Resume> storage = new HashMap<String, Resume>();

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Resume> r : storage.entrySet()) {
            res.append(r.getKey()).append(", ");
        }
        return res.toString();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> sortedMap = storage.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(pcomp))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(new ArrayList<>(sortedMap.values()));

        return new ArrayList<>(sortedMap.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
