package de.telran.practice6;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class StringsExample {
    public static void main(String[] args) {
//        stingAndStringBuilderComparison();
//        stringBuffer();
//        stringsComparing();
//        concatExamples();

        String s = "I love coding njfgnjdfbgnjdfgnjdfg very much  ";

        System.out.println("Str length: " + s.length());
        System.out.println(s.charAt(2));
        System.out.println(s.toLowerCase());
        System.out.println(s.toUpperCase());
        System.out.println(s.contains("very"));
        System.out.println(s.compareTo("Java"));
        System.out.println(s.endsWith("much"));
        System.out.println(s.startsWith("I l"));
        System.out.println(s.substring(7, 13));
        int startIndex = s.indexOf("coding");
        int endIndex = s.indexOf("very") + 4;
        System.out.println(s.substring(startIndex, endIndex));

        String[] arr = s.split("\\s");
        System.out.println(Arrays.toString(arr));

        System.out.println(s.repeat(3));

        String[] arr1 = s.split("o");
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr1[i].trim();
        }

        System.out.println(Arrays.toString(arr1));

    }

    private static void concatExamples() {
        int a = 10;
        System.out.println("Str" + 10 + 14 + 5);
        System.out.println("Str" + (10 + 14 + 5));
        System.out.println( 10 + 14 + 5 + "Str");
        System.out.println("Str " + a++);
        System.out.println("Str " + ++a);
    }

    private static void stringsComparing() {
        Scanner scanner = new Scanner(System.in);
        String s1 = "Hi";
        String s2 = "Hi";
        String s3 = new String("Hi");
        String s4 = new String(s1).intern();
        String s5 = "H";
        String s6 = "i";
        String s7 = s5 + s6;
        String s71 = "H" + "i";
        char[] chars = {'H', 'i'};
        String s8 = new String(chars).intern();
        String s9 = new String(new byte[]{72, 105});
        String s10 = scanner.next();

//        System.out.println(s1 == s2);
//        System.out.println(s1 == s3);
//        System.out.println(s1 == s4);
//        System.out.println(s1 == s7);
//        System.out.println(s1 == s8);
//        System.out.println(s1 == s9);
//        System.out.println(s1 == s10);

        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s4));
        System.out.println(s1.equals(s7));
        System.out.println(s1.equals(s8));
        System.out.println(s1.equals(s9));
        System.out.println(s1.equals(s10));

//        System.out.println((int)chars[0]);
//        System.out.println((int)chars[1]);
    }

    private static void stringBuffer() {
        StringBuffer s = new StringBuffer("Hello world!");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 500_000; i++) {
            s.append(i);
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("String buffer time: " + time);
    }

    private static void stingAndStringBuilderComparison() {
        String s = "Hello world!";
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 500_000; i++) {
            s += i;
        }

        long time = System.currentTimeMillis() - startTime;
        System.out.println("String time: " + time);

        StringBuilder sb = new StringBuilder("Hello world!");
        startTime = System.currentTimeMillis();

        for (int i = 0; i < 500_000; i++) {
            sb.append(i);
        }

        time = System.currentTimeMillis() - startTime;
        System.out.println("String builder time: " + time);

        System.out.println("Strings are equal: " + s.equals(sb.toString()));
    }
}
