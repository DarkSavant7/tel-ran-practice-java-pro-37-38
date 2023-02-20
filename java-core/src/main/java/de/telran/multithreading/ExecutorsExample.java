package de.telran.multithreading;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        ExecutorService executorService = Executors.newCachedThreadPool();

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        System.out.println("Start giving tasks");
        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
//            executorService.scheduleAtFixedRate(() -> {
                        System.out.printf("Task #%d started. Thread name is %s\n", j, Thread.currentThread().getName());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.printf("Task #%d finished\n", j);
                        }
                    });
//            }, 3, 5, TimeUnit.SECONDS);
        }
        System.out.println("Finish giving tasks");
        executorService.shutdown();
//       List<Runnable> tasksUndone = executorService.shutdownNow();
        System.out.println("Shutdown called");

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Executor service shut down");
    }
}
