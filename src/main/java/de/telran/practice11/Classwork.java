package de.telran.practice11;

import de.telran.practice1.Animal;
import de.telran.practice1.Bird;
import de.telran.practice1.Cat;
import de.telran.practice1.Dog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Classwork {
    public static void main(String[] args) {
//        simpleSpliteratorExample();
//        spliteratorWithRemaining();

//        foreachExample();
//        simpleLambdaCaptureExample();
//        splitting();
//        streamExample();

//        simpleTerminalExample();

        List<CatBag> bags = List.of(
                new CatBag(List.of(new Cat("Barsik"), new Cat("Murzik"), new Cat("Tom"))),
                new CatBag(List.of(new Cat("Murka"))),
                new CatBag(List.of(new Cat("Bob"), new Cat("John"), new Cat("Spike")))
        );

//       List<Cat> cats = bags.stream()
        List<CatBag> cats = bags.stream()
//                .map(b -> b.cats)
                .flatMap(bag -> bag.cats.stream())
                .map(c -> new CatBag(List.of(c)))
                .toList();

//       bags.stream()

        System.out.println(cats);


    }


    private static void simpleTerminalExample() {
        Set<Cat> cats = new HashSet<>(Arrays.asList(
                new Cat("Tom"),
                new Cat("Vaska"),
                new Cat("Barsik"),
                new Cat("Murzik"),
                new Cat("Murka"),
                new Cat("Tom"),
                new Cat("Tom"),
                new Cat("Spike")
        ));

        Cat b = cats.stream()
//                .allMatch(c -> c.getName().length() >= 3);
//                .anyMatch(c -> c.getName().length() > 3);
//                .noneMatch(c -> c.getName().length() < 3);
//                .forEach(System.out::println);
//        .findFirst().get();
//        .findAny().get();
                .reduce((c1, c2) -> c1.getName().length() > c2.getName().length() ? c1 : c2).get();
//        .reduce()


        System.out.println(b);
    }

    static class CatBag {
        List<Cat> cats;

        public CatBag() {
            this.cats = new ArrayList<>();
        }

        public CatBag(List<Cat> cats) {
            this.cats = new ArrayList<>(cats);
        }

        void add(Cat cat) {
            cats.add(cat);
        }

        @Override
        public String toString() {
            return "CatBag{" +
                    "cats=" + cats +
                    '}';
        }
    }

    private static void streamExample() {
        //        Iterable<Cat> cats = Arrays.asList(
        List<Cat> cats = Arrays.asList(
                new Cat("Tom"),
                new Cat("Vaska"),
                new Cat("Barsik"),
                new Cat("Murzik"),
                new Cat("Murka"),
                new Cat("Tom"),
                new Cat("Tom"),
                new Cat("Spike")
        );
//
//        List.of(
//                new Cat("Tom"),
//                new Cat("Vaska"),
//                new Cat("Barsik"),
//                new Cat("Murzik"),
//                new Cat("Murka"),
//                new Cat("Tom"),
//                new Cat("Tom"),
//                new Cat("Spike")
//        );

//        StreamSupport.stream(
//        cats.spliterator(), false);
//        Stream.of(new Cat("Vaska"),
//                new Cat("Barsik"),
//                new Cat("Murzik"),
//                new Cat("Murka"),
//                new Cat("Tom"),
//                new Cat("Spike"));
//        cats.parallelStream()
        Random random = new Random();
//        List<Dog> dogs = cats.stream()
//        random.doubles();
//        Dog d = cats.stream()
        Map<String, Dog> dogs = cats.stream()
//               .parallel()
                .sorted(Comparator.comparing(Animal::getName))
//                .filter(c -> !c.getName().endsWith("ik"))
                .filter(new Predicate<Cat>() {
                    @Override
                    public boolean test(Cat cat) {
                        return !cat.getName().endsWith("ik");
                    }
                })
//                .distinct()
//                .peek(System.out::println)
                .map(c -> new Dog(c.getName()))
//                .map(cat -> {
//                    System.out.println(cat);
//                    return cat;
//                })
//                .limit(2)
//                .mapMultiToLong()
//                .dropWhile(d -> d.getName().length() == 5)
//                .skip(3)
//                .takeWhile(d -> d.getName().length() == 5)

//                .toList();
//              .collect(Collectors.toList());
                .collect(Collectors.toMap(
                        Animal::getName,
                        Function.identity(),
                        (d1, d2) -> d2));
//                .collect(Collectors.toList());
//                .max((d1, d2) -> d1.getName().compareTo(d2.getName()))
//                .orElse(new Dog("UNKNOWN"));

//        System.out.println(dogs);
        System.out.println(dogs);
    }

    private static void splitting() {
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 0);

        Spliterator<Integer> spliterator1 = ints.spliterator();

        Spliterator<Integer> spliterator2 = spliterator1.trySplit();
        Spliterator<Integer> spliterator3 = spliterator1.trySplit();
        Spliterator<Integer> spliterator4 = spliterator2.trySplit();
        Spliterator<Integer> spliterator5 = spliterator4.trySplit();
//        Spliterator<Integer> spliterator6 = spliterator4.trySplit();


        System.out.println("SPLIT1:");
        spliterator1.forEachRemaining(System.out::println);
        System.out.println("SPLIT2:");
        spliterator2.forEachRemaining(System.out::println);
        System.out.println("SPLIT3:");
        spliterator3.forEachRemaining(System.out::println);
        System.out.println("SPLIT4:");
        spliterator4.forEachRemaining(System.out::println);
        System.out.println("SPLIT5:");
        spliterator5.forEachRemaining(System.out::println);
//        System.out.println("SPLIT6:");
//        spliterator6.forEachRemaining(System.out::println);
    }

    private static void simpleLambdaCaptureExample() {
        List<String> list = new ArrayList<>(List.of("One", "Two", "Three"));

        for (int i = 0; i < list.size(); i++) {
            int j = i;
            new Thread(() -> System.out.println("Running word " + list.get(j) + "Thread: " + Thread.currentThread().getName())).start();
        }
    }

    private static void foreachExample() {
        List<String> list = new ArrayList<>(List.of("One", "Two", "Three"));

        for (String s : list) {
            s = "Str: " + s + " something";
            System.out.println(s);
        }

        System.out.println("AFTER LOOP");
        System.out.println(list);
    }

    private static void spliteratorWithRemaining() {
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 0);

        Spliterator<Integer> spliterator = ints.spliterator();

        boolean result = false;
        int counter = 0;
        do {
            counter++;
            result = spliterator.tryAdvance(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println(integer);
                }
            });
        } while (result && counter < 6);

        System.out.println("AFTER LOOP");
        int count = counter;

        spliterator.forEachRemaining(e -> System.out.println(e + count + " element"));
    }

    private static void simpleSpliteratorExample() {
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 0);

        Spliterator<Integer> spliterator = ints.spliterator();

        boolean result = false;
        do {
            result = spliterator.tryAdvance(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println(integer);
                }
            });
        } while (result);
    }
}
