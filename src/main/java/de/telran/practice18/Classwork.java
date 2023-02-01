package de.telran.practice18;

public class Classwork {

    public static void main(String[] args) {
        var runtime = Runtime.getRuntime();

        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.totalMemory());
    }
}
