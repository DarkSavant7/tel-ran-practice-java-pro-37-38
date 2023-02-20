package de.telran.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Locks {

    public static void main(String[] args) {
//        reentrantLock();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println("READ 1 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READ 1 finished");
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println("READ 2 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READ 2 finished");
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println("READ 3 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READ 3 finished");
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.writeLock().lock();
                System.out.println("WRITE 1 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("WRITE 1 finished");
                lock.writeLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.writeLock().lock();
                System.out.println("WRITE 2 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("WRITE 2 finished");
                lock.writeLock().unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println("READ 4 started");
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READ 4 finished");
                lock.readLock().unlock();
            }
        }).start();
    }

    private static void reentrantLock() {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            System.out.println("Before lock 1");

            try {
                lock.lock();
                System.out.println("Got lock 1");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Release lock 1");
                lock.unlock();
            }
        }).start();

//        new Thread(() -> {
//            System.out.println("Before lock 2");
//
//            try {
//                lock.lock();
//                System.out.println("Got lock 2");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println("Release lock 2");
//                lock.unlock();
//            }
//        }).start();

//        new Thread(() -> {
//            System.out.println("Before lock 3");
//
//
//            if (lock.tryLock()) {
//                try {
//                    System.out.println("Got lock 3");
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    System.out.println("Release lock 3");
//                    lock.unlock();
//                }
//            } else {
//                System.out.println("Lock 3 is busy -> ignore");
//            }
//        }).start();

        new Thread(() -> {
            System.out.println("Before lock 4");


            try {
                if (lock.tryLock(4, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("Got lock 4");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Release lock 4");
                        lock.unlock();
                    }
                } else {
                    System.out.println("Lock 4 is busy -> ignore");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
