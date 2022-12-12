package de.telran.practice7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SetExample {
    public static void main(String[] args) {
//        simpleSetExample();
//        setBoxesExample();

//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new LinkedHashMap<>();
//        Map<String, String> map = new TreeMap<>();
        TreeMap<String, String> map = new TreeMap<>();

        map.put("One", "January");
        map.put("Two", "February");
        map.put("Three", "March");
//        map.put("Three", "HGHohuadfhjsvfad");
        map.putIfAbsent("Three", "HGHohuadfhjsvfad");
//        System.out.println(map.values());

//        List<String> values = new ArrayList<>(map.values());
//        Set<String> values = new HashSet<>(map.values());
//        Set<String> keys = map.keySet();
        Set<Map.Entry<String, String>> entries = map.entrySet();

//        entries.iterator();
//
//        for (Map.Entry<String, String> entry : entries) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        map.forEach((k, v) -> System.out.println(k + ": " + v));
//        System.out.println(map);

        System.out.println(map.get("One"));
        System.out.println(map.get("Five"));
        System.out.println(map.getOrDefault("Five", "Default"));

        map.replaceAll((k, v) -> 12 + v + ":" + k);

        System.out.println(map);



//        Map<Box, Integer> boxIntegerMap = new HashMap<>();
//
//        boxIntegerMap.put(new Box(1, 1), 1);
//        boxIntegerMap.put(new Box(2, 1), 1);
//        boxIntegerMap.put(new Box(31, 1), 1);
//        boxIntegerMap.put(new Box(3, 1), 1);
//
//        System.out.println(boxIntegerMap);
//
//        System.out.println(133 % 16);
    }

    private static void setBoxesExample() {
        //        Set<Box> boxes = new HashSet<>();
//        Set<Box> boxes = new TreeSet<>((b1, b2) -> b1.getHeight() * b1.getWidth() - b2.getHeight() * b2.getWidth());
//        Set<Box> boxes = new TreeSet<>();
//        Set<Box> boxes = new LinkedHashSet<>();
//        Set<Box> boxes = new TreeSet<>(Comparator.comparingInt(b -> b.getHeight() * b.getWidth()));
//        Set<Box> boxes = new TreeSet<>(new Comparator<Box>() {
//            @Override
//            public int compare(Box b1, Box b2) {
//                return b1.getHeight() * b1.getWidth() - b2.getHeight() * b2.getWidth();
//            }
//        });

        Set<Box> boxes = new TreeSet<>(
                (b1, b2) -> b1.getHeight() * b1.getWidth() - b2.getHeight() * b2.getWidth()
        );
        boxes.add(new Box(1, 1));
        boxes.add(new Box(2, 2));
        boxes.add(new Box(3, 3));
        boxes.add(new Box(1, 1));

        System.out.println(boxes);
    }

    private static void simpleSetExample() {
        //Iterable -> Collection -> List / Set / Queue

//        Set<String> months = new HashSet<>();
        Set<String> months = new TreeSet<>((s1, s2) -> s1.length() - s2.length());

        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");

//        System.out.println(months);
        months.addAll(List.of("March", "April", "May", "June"));
//        System.out.println(months);

//        for (String month : months) {
//            System.out.println(month);
//        }

//        Iterator<String> iterator = months.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

//        months.forEach(System.out::println);
        months.forEach(s -> System.out.println(s + " month"));
    }

    static class Box implements Comparable<Box> {
        private int height;
        private int width;

        public Box(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Box box)) return false;
            return height == box.height && width == box.width;
        }

        @Override
        public int hashCode() {
//            return Objects.hash(height, width);
            return 1;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "height=" + height +
                    ", width=" + width +
                    '}';
        }

        @Override
        public int compareTo(Box o) {
            return height * width - o.height * o.width;
        }
    }
}
