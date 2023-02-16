package de.telran.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AnotherMultithreadingExample {

    static final int SIZE = 50_000_000;

    public static void main(String[] args) {
        float[] standard = new float[SIZE];
        Arrays.fill(standard, 1f);
        long start = System.currentTimeMillis();
        sequentialMethod(standard, 1, 0);
        long time = System.currentTimeMillis()  - start;
        System.out.printf("One thread process time: %d ms.\n", time);
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);

        List<float[]> list = testMultiMethod(2, 50, 1);

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Comparing standard with arr # %d : %b\n", i, Arrays.equals(standard, list.get(i)));
        }
    }

    private static List<float[]> testMultiMethod(int minThreads, int maxThreads, int step) {
        List<float[]> list = new LinkedList<>();
        for (int i = minThreads; i <= maxThreads ; i += step) {
            float[] arr = new float[SIZE];
            Arrays.fill(arr, 1);
            long start = System.currentTimeMillis();
//            multiMethod(arr, i);
            multiMethodWithoutExecutors(arr, i);
            System.out.printf("Multimethod with %d thread time: %d.\n", i, System.currentTimeMillis() - start);
            list.add(arr);
        }
        return list;
    }

    private static void multiMethodWithoutExecutors(float[] arr, int threads) {
        try {
            List<Thread> list = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                int j = i;
                Thread t = new Thread(() -> sequentialMethod(arr, threads, j));
                t.start();
                list.add(t);
            }
            for (Thread thread : list) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void multiMethod(float[] arr, int threads) {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            int j = i;
            executorService.execute(() -> sequentialMethod(arr, threads, j));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sequentialMethod(float[] data, int offset, int startIndex) {
        if (offset < 1) offset = 1;
        if (startIndex < 0) startIndex = 0;
        for (int index = startIndex; index < data.length; index += offset) {
            float currentValue = data[index];
            data[index] = computeValue(index, currentValue);
        }
    }

    private static float computeValue(int index, float currentValue) {
        return (float)(currentValue * Math.sin(0.2f + index / 5.0) * Math.cos(0.2f + index / 5.0) * Math.cos(0.4f + index / 2.0));
    }

}
