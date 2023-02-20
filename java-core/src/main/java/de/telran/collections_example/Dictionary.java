package de.telran.collections_example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {

    private final Map<String, Set<String>> entries = new TreeMap<>();

    public void add(String originalWord, String translation) {
        Set<String> entries = getTranslations(originalWord);
        entries.add(translation);
    }

    private Set<String> getTranslations(String originalWord) {
        return entries.computeIfAbsent(originalWord, key -> new HashSet<>());
//        return entries.computeIfAbsent(originalWord, new Function<String, Set<String>>() {
//            @Override
//            public Set<String> apply(String s) {
//                return new HashSet<>();
//            }
//        });
    }

    public Set<String> get(String originalWord) {
        return getTranslations(originalWord);
    }

    public Set<String> getAllWords() {
        return entries.keySet();
    }

    public boolean delete(String key, String value) {
        Set<String> values = entries.get(key);
        if (values == null) return false;
        else return values.remove(value);
    }

    public void print() {
//        System.out.println(entries);
        for (Map.Entry<String, Set<String>> stringSetEntry : entries.entrySet()) {
            System.out.print(stringSetEntry.getKey() + ": " + stringSetEntry.getValue());
        }
    }
}
