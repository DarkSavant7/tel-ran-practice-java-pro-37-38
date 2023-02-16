package de.telran.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Multithreading {

    public static void main(String[] args) {
//        callable();
//        futureWithExecutor();

        Thread t = new Thread(() -> {
            System.out.println("Start");
            int counter = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.printf("Iteration #%d\n", counter++);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();

        try {
            Thread.sleep(5000);
            System.out.println("Stopping");
            t.interrupt();
//            t.suspend();
//            t.resume();
//            t.stop();
            System.out.println("Stopped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void futureWithExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> "Hello!");

        try {
            String result =  future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void callable() {

//        Callable
//        Future
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //...... some code
                Thread.sleep(2000);
                System.out.println("!!!!!!!!!!!!!!!!!w");
                return "HELLO";
//                throw new RuntimeException("AAAAAAAAAAA");
            }
        });

        new Thread(futureTask).start();

//        futureTask.isDone()
        try {
//            String result = futureTask.get();
            String result = futureTask.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("timeout");
        }
    }
}
