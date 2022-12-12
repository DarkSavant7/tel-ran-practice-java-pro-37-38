package de.telran.practice2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public class Practice {

    public static void main(String[] args) {
//        interfacesDemo();

        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        integers.sort((o1, o2) -> o2 - o1);
        integers.sort(Integer::compare);

        integers.remove(5);
//        System.out.println(integers);

//        integers.replaceAll(new UnaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer * 2;
//            }
//        });

//        integers.replaceAll(i -> i * 2);
//        integers.forEach(i -> System.out.println(i));
//        integers.forEach(System.out::println);
//        System.out.println(integers);

        WeekDay day = WeekDay.TUESDAY;

        System.out.println(WeekDay.TUESDAY == day);


        Oper oper = Oper.MUL;
        System.out.println(oper.operate(10, 14));
        oper = Oper.SUB;
        System.out.println(oper.operate(10, 14));
    }

    private static void interfacesDemo() {
        Moving[] units = {
                new Horse(),
                new Human(),
                new Plane(),
                new Submarine(),
                new Moving() {
                    @Override
                    public void move() {
                        System.out.println("Anon");
                    }
                },
                () -> System.out.println("Anon"),
                () -> System.out.println("Lambda"),
                () -> System.out.println("sdfb")
        };

        for (Moving unit : units) {
            unit.move();
            System.out.println(unit.getClass().getName());

            if (unit instanceof Attacking) {
                ((Attacking) unit).attack();
                ((Attacking) unit).doDefault();
            }
        }


        System.out.println(Attacking.SOME_FIELD);
        System.out.println(Human.SOME_FIELD);
    }
}
