package de.telran;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TestTask {
    public static void main(String[] args) {

        arrayJokes();
        stringWordCount();

    }


    /**
     * Write the solution and paste the code
     * Replace every element of an array with an odd index to zero
     * The task:
     * 1. Create an array of 8 random integers from the interval [1;10]
     * 2. Display the array to the screen as a string
     * 3. Replace every odd-indexed element with zero
     * 4. Display the array again on a separate line
     */
    private static void arrayJokes() {
        int[] arr = new int[8];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 1) {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Write the solution and paste the code
     * How to determine the number of words in a line
     * The task:
     * Write a method that determines how many words you entered from the console.
     */
    private static void stringWordCount() {
        Scanner sc = new Scanner(System.in);
        //Вводим предложение в консоль
        System.out.println("Введите слова одной строкой через пробел");
        String input = sc.nextLine();
        //Начальное количество слов равно 0
        int count = 0;

        String[] words = input.split(" ");

        for (String word : words) {
            if (!word.isBlank()) {
                count++;
            }
        }
        //Если ввели хотя бы одно слово, тогда считать, иначе конец программы
//        if (input.length() != 0) {
//            count++;
//            input = input.trim();
//            //Проверяем каждый символ, не пробел ли это
//            for (int i = 0; i < input.length(); i++) {
//                if (input.charAt(i) == ' ') {
//                    //Если пробел - увеличиваем количество слов на 1
//                    count++;
//                }
//            }
//        }

        System.out.println("Вы ввели " + count + " слов");
    }
}
