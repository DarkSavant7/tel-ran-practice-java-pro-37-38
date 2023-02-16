package de.telran.multithreading;

import java.util.Arrays;

public class MultithreadingExample {

    private static final int SIZE = 100_000_000;

    public static void main(String[] args) {
        float[] arr1 = createArr(SIZE);
        runWithEstimation(() -> runCalculation(arr1, 0), "Single thread method");

        float[] arr2 = createArr(SIZE);
        runWithEstimation(() -> parallelMethod(arr2), "Two thread method");

        System.out.println("Arrays equal: " + Arrays.equals(arr1, arr2));
    }

    private static void runCalculation(float[] arr, int offset) {
        for (int index = 0; index < arr.length; index++) {
            arr[index] = computeValue(index + offset, arr[index]);
        }
    }

    private static void parallelMethod(float[] data) {
        int halfSize = data.length / 2;
        float[] arr1 = Arrays.copyOfRange(data, 0, halfSize);
        float[] arr2 = Arrays.copyOfRange(data, halfSize, data.length);

        Thread thread1 = new Thread(() -> runCalculation(arr1, 0));
        Thread thread2 = new Thread(() -> runCalculation(arr2, halfSize));

        thread1.start();
        thread2.start();

        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }

        System.arraycopy(arr1, 0, data, 0, halfSize);
        System.arraycopy(arr2, 0, data, halfSize, halfSize);

    }

    private static float[] createArr(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    private static void runWithEstimation(Runnable action, String actionName) {
        long startTime = System.currentTimeMillis();
        action.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.printf("Method %s run took %d ms\n", actionName, duration);
    }

    private static float computeValue(int index, float currentValue) {
        return (float) (currentValue * Math.sin(0.2f + index / 5.0) * Math.cos(0.2f + index / 5.0) * Math.cos(0.4f + index / 2.0));
    }

}
