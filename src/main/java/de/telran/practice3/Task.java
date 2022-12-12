package de.telran.practice3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;

public class Task {
    public static void main(String[] args) {
//        employeeServiceDemo();
//        customIteratorExample();
//        iteratorSimpleDemo();
//        spliterator();

        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        scanner.nextLine();
        String s =  scanner.nextLine();

        System.out.println(a);
        System.out.println(s);

    }

    private static void spliterator() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        Spliterator<Integer> spliterator = list.spliterator();
//
//        boolean flag = false;
//
//        do {
//            flag = spliterator.tryAdvance(System.out::println);
//        }
//        while (flag);

        Spliterator spliterator2 = spliterator.trySplit();
        Spliterator spliterator3 = spliterator.trySplit();

        System.out.println("First split:");
        spliterator.forEachRemaining(System.out::print);
        System.out.println();

        System.out.println("Second split:");
        spliterator2.forEachRemaining(System.out::print);
        System.out.println();

        System.out.println("Third split:");
        spliterator3.forEachRemaining(System.out::print);
        System.out.println();
    }

    private static void iteratorSimpleDemo() {
        List<String> someList = new ArrayList<>(List.of("January", "February", "March"));

//        Iterator<String> iter = someList.iterator();
////
////        while (iter.hasNext()) {
////            String s = iter.next();
////            System.out.println(s);
////            if (s.equals("February")) {
////                iter.remove();
////            }
////        }
////
////        System.out.println("=====================");
////
////        iter = someList.iterator();
////        iter.forEachRemaining(System.out::println);

        ListIterator<String> iterator = someList.listIterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);

            if (s.equals("March")) {
                iterator.set("September");
            }

            if (s.equals("January")) {
                iterator.add("December");
            }
        }

        System.out.println("====================");

        System.out.println(someList);
    }

    private static void customIteratorExample() {
        String[] strings = {
                "January",
                "February",
                "March"
        };

        ArrayIterator<String> iterator = new ArrayIterator<>(strings);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static class ArrayIterator<TYPE> implements Iterator<TYPE> {

        private TYPE[] arr;
        private int pointer = 0;

        public ArrayIterator(TYPE[] arr) {
            this.arr = arr;
        }

        @Override
        public boolean hasNext() {
            return pointer < arr.length;
        }

        @Override
        public TYPE next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr[pointer++];
        }
    }

    private static void employeeServiceDemo() {
        EmployeeRepo employeeRepo = new EmployeeRepo();
        EmployeeService employeeService = new EmployeeService(employeeRepo);

        List<Employee> list = employeeService.findAll();
        System.out.println(list);
        System.out.println();
        System.out.println();

        Employee employeeToPromote = list.get(3);

        employeeService.promote(employeeToPromote.getId());

        System.out.println(list);
//
//        Employee employee1 = new Manager("Vasya");
//        Employee employee2 = new TopManager("Petya");
//
//        System.out.println(employee1);
//
//        employeeService.changePosition(employee1, PositionType.PRIVATE);
//
//        System.out.println(employee1);
    }
}
