package de.telran.practice15;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Classwork {
    //^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.?\b){4}$
    //^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)(\.(?!$)|$)){4}$
    //^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\.(?!$)|$)){4}$

    public static void main(String[] args) {
//        filterUsingRegex();
//        pattern();


//        List<File> files = new ArrayList<>();
//        searchFiles(new File("src"), files, ".java", 0);
//        System.out.println("\nFinally: " + files);

        List<File> files = searchFiles(new File("src"), ".java");
        files.forEach(System.out::println);

    }

    private static void searchFiles(File root, List<File> list, String ext, int n) {
        List<File> medium = Stream.of((Objects.requireNonNull(root.listFiles())))
                .filter(f -> {
                    if (f.isDirectory()) {
                        searchFiles(f, list, ext, n + 1);
                        return false;
                    } else {
                        return f.getName().endsWith(ext);
                    }
                })
                .toList();
        list.addAll(medium);

        for (File fl : medium) {
            System.out.println("localList -> " + fl.getAbsolutePath());
        }
        System.out.println("" + n + ":" + medium);
    }

    private static List<File> searchFiles(File root, String ext) {
        List<File> files = new ArrayList<>();
        List<File> medium = Stream.of((Objects.requireNonNull(root.listFiles())))
                .filter(f -> {
                    if (f.isDirectory()) {
                        files.addAll(searchFiles(f, ext));
                        return false;
                    } else {
                        return f.getName().endsWith(ext);
                    }
                })
                .toList();
        files.addAll(medium);
        return files;
    }

    private static void pattern() {
        String rawData = "AaaaaaBbbbbb11111CccccDddddd222EeeeeeFfff3333";
//        String[] arr = rawData.split("\\d+");
        Pattern pattern = Pattern.compile("([A-Z][a-z]+)([A-Z][a-z]+)(\\d+)");
//        String[] arr = pattern.split(rawData);
        String[] arr = pattern.split(rawData);

        System.out.println(Arrays.toString(arr));
    }

    private static void filterUsingRegex() {
        String rawData = "aaaaa 11111 jfjfgj kjdfgjndfsj fjfjfjf 2222 333333";
        String[] rawArray = rawData.split("\\s");
        List<String> words = new ArrayList<>();
        List<Long> numbers = new ArrayList<>();
        for (String element : rawArray) {
            if (element.matches("\\d+")) {
                numbers.add(Long.parseLong(element));
            } else {
                words.add(element);
            }
        }

        System.out.println("Words: ");
        System.out.println(words);
        System.out.println("\nNumbers: ");
        System.out.println(numbers);
    }
}
