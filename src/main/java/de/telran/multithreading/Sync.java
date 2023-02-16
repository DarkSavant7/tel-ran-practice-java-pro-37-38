package de.telran.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sync {

    static int flag;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//        simpleCountDownLathExample();
//        anotherCdlExample();
//        semaphoreExample();
//        simpleBarrierExample();
//        barrierWithJoin();
//        barrierWithRunnableExample();
//        synchronizedCollections();

    }

    private static void synchronizedCollections() {
        List<String> strings = new ArrayList<>();
        List<String> syncStrings1 = new Vector<>();
        List<String> syncStrings2 = new CopyOnWriteArrayList<>();
        List<String> syncStrings3 = Collections.synchronizedList(strings);

        Set<String> set = new HashSet<>();
        Set<String> syncSet = new CopyOnWriteArraySet<>();
        Set<String> syncSet1 = Collections.synchronizedSet(set);

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> syncMap1 = new Hashtable<>();
        Map<String, Integer> syncMap2 = Collections.synchronizedMap(map);
        Map<String, Integer> syncMap3 = new ConcurrentHashMap<>();
    }

    private static void barrierWithRunnableExample() {
        int carsCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(carsCount, () -> {
            if (flag == 0) {
                System.out.println("\nSTART");
                flag = 1;
            } else {
                System.out.println("\nFINISH");
            }
        });
        for (int i = 0; i < carsCount; i++) {
            int j = i + 1;
            new Thread(() -> {
                try {
                    System.out.printf("Car #%d preparing\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d on the start line\n", j);
                    barrier.await();
                    System.out.printf("Car #%d riding\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d finished\n", j);
                    barrier.await();
                    System.out.printf("Car #%d goes to garage\n", j);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void barrierWithJoin() throws InterruptedException, BrokenBarrierException {
        int carsCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(carsCount + 1);
        for (int i = 0; i < carsCount; i++) {
            int j = i + 1;
            new Thread(() -> {
                try {
                    System.out.printf("Car #%d preparing\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d on the start line\n", j);
                    barrier.await();
                    System.out.printf("Car #%d riding\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d finished\n", j);
                    barrier.await();
                    System.out.printf("Car #%d goes to garage\n", j);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        while (barrier.getNumberWaiting() < carsCount) {
            Thread.sleep(100);
        }
        System.out.println();
        System.out.println("All cars ready");
        System.out.println("START!!!");
        barrier.await();

        while (barrier.getNumberWaiting() < carsCount) {
            Thread.sleep(100);
        }
        System.out.println();
        System.out.println("All cars finished");
        barrier.await();
    }

    private static void simpleBarrierExample() {
        int carsCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(carsCount);
        for (int i = 0; i < carsCount; i++) {
            int j = i + 1;
            new Thread(() -> {
                try {
                    System.out.printf("Car #%d preparing\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d on the start line\n", j);
                    barrier.await();
//                    barrier.getNumberWaiting();
//                    barrier.getParties();
                    System.out.printf("Car #%d riding\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car #%d finished\n", j);
                    barrier.await();
                    System.out.printf("Car #%d goes to garage\n", j);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void semaphoreExample() {
        //        Semaphore semaphore = new Semaphore(2);
        Semaphore semaphore = new Semaphore(3, true);
        for (int i = 0; i < 10; i++) {
            int j = i + 1;
            new Thread(() -> {
                try {
                    System.out.printf("Car $%d before the bridge\n", j);
                    semaphore.acquire();
                    System.out.printf("Car $%d rides by the bridge for a long time\n", j);
                    Thread.sleep((long) (300 * j + 400 * Math.random()));
                    System.out.printf("Car $%d finished\n", j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }

    private static void anotherCdlExample() throws InterruptedException {
        int threadCount = 10;
        CountDownLatch cdl = new CountDownLatch(threadCount + 1);
        System.out.println("Begin");

        for (int i = 0; i < threadCount; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    System.out.printf("Thread #%d started\n", j);
                    Thread.sleep((long) (300 * j + 500 * Math.random()));
                    cdl.countDown();
                    cdl.await();
                    System.out.printf("Thread #%d job done\n", j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        while (cdl.getCount() > 1) {
            Thread.sleep(100);
        }
        System.out.println("ALL JOBS DONE!!!");
        cdl.countDown();
    }

    private static void simpleCountDownLathExample() {
        int threadCount = 10;
        CountDownLatch cdl = new CountDownLatch(threadCount / 2);
//        CountDownLatch cdl = new CountDownLatch(threadCount + 1);
        System.out.println("Begin");

        for (int i = 0; i < threadCount; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    System.out.printf("Thread #%d started\n", j);
                    Thread.sleep((long) (300 * j + 500 * Math.random()));
                    System.out.printf("Thread #%d job done\n", j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            }).start();
        }

        try {
            cdl.await();
//            cdl.await(3, TimeUnit.SECONDS);
            System.out.println("ALL JOBS DONE!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
