package de.telran.practice5;

import de.telran.practice1.Animal;
import de.telran.practice1.Cat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericsExample {

    public static void main(String[] args) {
//        simpeBoxDemo();
//        genericExample();
//        rawUseDemo();
//        boxWithNims();
//        animalBox();

        var list = List.of(1, 2, 3);
        System.out.println(getFirstObject(list));

        List<Cat> cats = new ArrayList<>(Arrays.asList(
                new Cat("aaa"),
                new Cat("bbb"),
                new Cat("ccc"),
                new Cat("ddd"))

        );
        List<? extends Animal> animals = cats;
//        List<? super Animal> animals = cats;
//        animals.add(new Cat("ddd"));
//        Collections.copy();

        System.out.println(animals);
    }

    private static <SOME> SOME getFirstObjectGen(List<SOME> list) {
        return list.get(0);
    }

    private static Object getFirstObject(List list) {
        return list.get(0);
    }

    private static void animalBox() {
        BoxWithAnimals<Animal> box = new BoxWithAnimals<>();
        BoxWithAnimals<Cat> box1 = new BoxWithAnimals<>(
                new Cat("aaa"),
                new Cat("bbb"),
                new Cat("ccc"),
                new Cat("ddd")
        );

        box1.shift(box);
    }

    private static void boxWithNims() {
        BoxWithNumbers<Number> ints1 = new BoxWithNumbers<>(1, 2, 3, 4, 5, 6, 7);
        BoxWithNumbers<Integer> ints2 = new BoxWithNumbers<>(10, 12);
        BoxWithNumbers<Float> floats = new BoxWithNumbers<>(10f, 12f);

        System.out.println(ints2.equalsAvg(floats));
        System.out.println(ints1.equalsAvg(floats));
    }

    private static void rawUseDemo() {
        BoxGeneric boxInt1 = new BoxGeneric(100500);
        BoxGeneric boxInt2 = new BoxGeneric(500100);

        BoxGeneric boxString1 = new BoxGeneric("Hello");
        BoxGeneric boxString2 = new BoxGeneric("World");

        //Many strings of code...
        boxInt2.setObj("Java");
        //Many strings of code...


        if (boxInt2.getObj() instanceof Integer && boxInt1.getObj() instanceof Integer) {
            int sum = (Integer) boxInt1.getObj() + (Integer) boxInt2.getObj();
            System.out.println(sum);
        } else {
            System.out.println("Error");
        }
    }

    private static void genericExample() {
        BoxGeneric<Integer> boxInt1 = new BoxGeneric<>(100500);
        BoxGeneric<Integer> boxInt2 = new BoxGeneric<>(500100);

        BoxGeneric<String> boxString1 = new BoxGeneric<>("Hello");
        BoxGeneric<String> boxString2 = new BoxGeneric<>("World");

        //Many strings of code...
//        boxInt2.setObj("Java");
        boxInt2.setObj(14);
        //Many strings of code...

        int sum = boxInt2.getObj() + boxInt1.getObj();

        System.out.println(sum);

        System.out.println(boxInt1.getClass().getName());
    }

    private static void simpeBoxDemo() {
        Box boxInt1 = new Box(100500);
        Box boxInt2 = new Box(500100);

        Box boxString1 = new Box("Hello");
        Box boxString2 = new Box("World");

        //Many strings of code...
        boxInt2.setObj("Java");
        //Many strings of code...


        if (boxInt2.getObj() instanceof Integer && boxInt1.getObj() instanceof Integer) {
            int sum = (Integer) boxInt1.getObj() + (Integer) boxInt2.getObj();
            System.out.println(sum);
        } else {
            System.out.println("Error");
        }
    }
}
