package de.telran.collections_example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {
    private static final String words =
            "Lie upon the lie " +
                    "upon the lie " +
                    "upon the lie " +
                    "upon the lie " +
                    "And it all comes down to this " +
                    "That a pig is a pig is a pig is a pig";

    private static TreeSet<String> getWords(String[] arr) {
        return new TreeSet<>(Arrays.asList(arr));
    }

//    private static Set<String> getWordsTest(String[] arr) {
//        return Set.of(arr);
//    }

    private static HashMap<String, Integer> getWordsCount(String[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < arr.length; i++) {
//            String word = arr[i];
//            map.put(word, map.getOrDefault(word, 0) + 1);
//        }

        for (String s : arr) {
            map.merge(s, 1, Integer::sum);
//            map.merge(s, 1, (value1, value2) -> value1 + value2);
//            map.merge(s, 1, new BiFunction<Integer, Integer, Integer>() {
//                @Override
//                public Integer apply(Integer integer, Integer integer2) {
//                    return integer + integer2;
//                }
//            });
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getWords(words.toLowerCase().split(" ")));
        System.out.println(getWordsCount(words.toLowerCase().split(" ")));

        Dictionary dictionary = new Dictionary();
        dictionary.add("run", "бежать");
        dictionary.add("run", "управлять");
        dictionary.add("fine", "штраф");
        dictionary.add("fine", "хороший");
        dictionary.add("fine", "утонченный");

        System.out.println(dictionary.get("fine"));
        System.out.println(dictionary.get("run"));

        dictionary.print();
    }
}
