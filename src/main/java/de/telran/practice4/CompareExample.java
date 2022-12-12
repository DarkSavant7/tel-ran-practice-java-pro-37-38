package de.telran.practice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

public class CompareExample {
    public static void main(String[] args) {
//        boxesCompare();
//        comparatorString();

//        for (int i : Range.fromTo(-100, 100)) {
//            System.out.println(i);
//        }

        List<String> list = List.of("aaa", "bbb", "ccc", "ddd", "eeee");

        Random random = new Random();
        random.nextInt(10, 20);

        LombokTestClass testClass = new LombokTestClass(12, "sdf");

        for (String s : CollectionRange.fromTo(list, 1, 3)) {
            System.out.println(s);
        }
    }

    static class CollectionRange<T> implements Iterable<T> {

        private int from;
        private int to;
        private Collection<T> collection;

        private CollectionRange(Collection<T> collection, int from, int to) {
            this.from = from;
            this.to = to;
            this.collection = collection;
        }

        public static <T> CollectionRange<T> fromTo(Collection<T> collection, int from, int to) {
            if (from < 0) throw new IllegalArgumentException();
            return new CollectionRange<T>(collection, from, to);
        }

        @Override
        public Iterator<T> iterator() {
            return new CollectionRangeIterator<T>(this);
        }
    }

    static class CollectionRangeIterator<T> implements Iterator<T> {

        private CollectionRange<T> range;
        private int pointer;
        private List<T> list;

        public CollectionRangeIterator(CollectionRange<T> range) {
            this.range = range;
            this.pointer = range.from;
            this.list = new ArrayList<>(range.collection);
        }

        @Override
        public boolean hasNext() {
            return pointer < range.to && pointer < range.collection.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(pointer++);
        }
    }

    static class Range implements Iterable<Integer> {

        private int from;
        private int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public static Range fromTo(int from, int to) {
            return new Range(from, to);
        }

        @Override
        public Iterator<Integer> iterator() {
            return new RangeIterator(this);
        }
    }

    static class RangeIterator implements Iterator<Integer> {

        private Range range;
        private int pointer;

        public RangeIterator(Range range) {
            this.range = range;
            this.pointer = range.from;
        }

        @Override
        public boolean hasNext() {
            return pointer < range.to;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return pointer++;
        }
    }

    private static void comparatorString() {
        List<String> strings = new ArrayList<>(List.of("Vasya", "Petyaa", "Alik"));
        strings.sort((s1, s2) -> s1.length() - s2.length());

        System.out.println(strings);
    }

    private static void boxesCompare() {
        Box[] boxes = {
                new Box(4, 5, "A"),
                new Box(2, 3, "AAAAAAAAAAAAAAAAAAAAA"),
                new Box(10, 10, "jxfvlnj"),
                new FurnitureBox(20, 20, "fsdsfdsdf"),
                new ShoeBox(2, 2, "shoe"),
        };
        System.out.println(Arrays.toString(boxes));

        Arrays.sort(boxes);
        System.out.println();

        System.out.println(Arrays.toString(boxes));

        Arrays.sort(boxes, (b1, b2) -> b2.name.compareTo(b1.name));

        System.out.println();

        System.out.println(Arrays.toString(boxes));
    }

    static class Box implements Comparable<Box> {
        private int height;
        private int width;
        private String name;

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

        public Box(int height, int width, String name) {
            this.height = height;
            this.width = width;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Box box)) return false;
            return height == box.height && width == box.width;
        }

        @Override
        public int hashCode() {
            return Objects.hash(height, width);
        }

        @Override
        public String toString() {
            return "Box{" +
                    "height=" + height +
                    ", width=" + width +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Box o) {
            return height * width + name.length()
                    - o.height * o.width - o.name.length();
        }
    }

    static class ShoeBox extends Box {
        public ShoeBox(int height, int width, String name) {
            super(height, width, name);
        }
    }

    static class FurnitureBox extends Box {
        public FurnitureBox(int height, int width, String name) {
            super(height, width, name);
        }
    }
}
