package de.telran.multithreading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

public class SyncOperations {

    // пример применения Semaphore в работе небольшого ресторана, в котором всего 5 столиков, но в людей, желающих
    // пообедать больше, чем свободных столов

    public static class Example1 {
        public static void main(String[] args) throws Exception {
            // устанавливая флаг true, Semaphore будет раздавать разрешения в порядке очереди
            Semaphore semaphore = new Semaphore(5, true);
            // свободные столики (5 штук) - по умолчанию создаются с параметром false
            // true - занято, false - свободно
            boolean[] freeTables = new boolean[5];
            // список людей, кто желает пообедать в этом ресторане
            String[] personNames = new String[]{"Roman", "Alena", "Kira", "Anna", "Leo", "Bob", "Vladislav", "Inna"};

            for (String personName : personNames) {
                new Thread(new Person(personName, semaphore, freeTables)).start();
                Thread.sleep(700);
            }

        }
    }

    static class Person implements Runnable {
        private final String personName;
        private final Semaphore semaphore;
        private final boolean[] freeTables;

        public Person(String personName, Semaphore semaphore, boolean[] freeTables) {
            this.personName = personName;
            this.semaphore = semaphore;
            this.freeTables = freeTables;
        }

        @Override
        public void run() {
            System.out.println("-> " + personName + " подошёл в ресторан.");
            try {
                // запрашивается доступ, если доступ не разрешён - поток ожидает разрешения
                semaphore.acquire();
                int parkingNumber = -1;

                synchronized (freeTables) {
                    for (int i = 0; i < 5; i++) {
                        if (!freeTables[i]) {

                            // определяем место как занятое
                            freeTables[i] = true;

                            // наличие свободного места гарантирует Semaphore
                            parkingNumber = i;

                            System.out.println("[!] " + personName + " был размещён за столиком #" + (i + 1));
                            break;
                        }
                    }
                }

                // время на приготовление ланча и его завершение
                Thread.sleep(5000);

                synchronized (freeTables) {
                    freeTables[parkingNumber] = false;
                }

                semaphore.release();
                synchronized (freeTables) {
                    System.out.println("<- " + personName + " закончил свой ланч и покинул ресторан");
                    System.out.println("[!] столик " + (parkingNumber + 1) + " освободился");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


// Пример применения CountDownLatch в процессе отправления людей в отпуск, у нас 8 человек, которые
// желают отправиться в отпуск. В аэропорту они выполняют обычные действия, такие как регистрация,
// сдача багажа. Делают они это по отдельности, а после этого уже группой садятся в самолет и вылетают

    public static class Example2 {
        public static void main(String[] args) throws Exception {

            CountDownLatch countDownLatch = new CountDownLatch(8);

            // список людей, кто желает полететь на отдых
            String[] personNames = new String[]{"Roman", "Alena", "Kira", "Anna", "Leo", "Bob", "Vladislav", "Inna"};

            for (String personName : personNames) {
                new Thread(new PersonInAirport(personName, countDownLatch)).start();
                Thread.sleep(700);
            }

        }
    }

    static class PersonInAirport implements Runnable {
        private final String personName;
        private final CountDownLatch countDownLatch;

        public PersonInAirport(String personName, CountDownLatch countDownLatch) {
            this.personName = personName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            try {
                // список дел, которые буду выполнены в рамках каждого потока (человека) самостоятельно
                System.out.println("-> " + personName + " приехал в аэропорт.");
                System.out.println("[!] " + personName + " сдал багаж");
                System.out.println("[!] " + personName + " успел перекусить");

                countDownLatch.countDown();

                System.out.println();
                countDownLatch.await();

                // финальное - выполнится то, что находится после await() метода
                System.out.println(personName + " группой вылетел на отдых");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

// представим, что у нас есть фуникулер, который поднимает людей на вершину холма, он может перевести
// ограниченное количество людей, например 2
// люди приходят на платформу, но фуникулер перевозит только двоих, мы фиксируем это в консоли
// в конечном итоге каждый человек будет находиться на вершине холма с помощью нашего фуникулера
// ((CyclicBarrier'а)

    public static class Example3 {
        public static void main(String[] args) throws Exception {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Funicular());

            // список людей, кто желает забраться на вершину горы
            String[] personNames = new String[]{"Roman", "Alena", "Kira", "Anna", "Leo", "Bob", "Vladislav", "Inna"};

            for (String personName : personNames) {
                new Thread(new PersonOnFunicular(personName, cyclicBarrier)).start();
                Thread.sleep(400);
            }
        }
    }

    static class Funicular implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Funicular took some people and is going to the top of hill");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class PersonOnFunicular implements Runnable {

        private final String personName;
        private final CyclicBarrier cyclicBarrier;

        public PersonOnFunicular(String personName, CyclicBarrier cyclicBarrier) {
            this.personName = personName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("-> " + personName + " in waiting on platform");
                cyclicBarrier.await();
                System.out.println("<- " + personName + " on the top");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

// Задача из логистики, как выбрать наиболее оптимальный путь при доставке товаров
//  A  _ _ _ _             _ _ _ _ B
//            \          /
//              - - E - -
//    _ _ _ _ /          \ _ _ _ _
//  C                              D
//
// E - точка обмена

// необходимо доставить посылки двумя грузовиками
// грузовик 1: посылка A -> C и A -> D
// грузовик 2: посылка B -> C и B -> D

// каждый грузовик может поехать по наиболее прямому пути, но это не оптимально
// можно создать пункт E для обмена конечными посылками, что
// итоговые конечные точки были одинаковые, то есть
// грузовик 1: посылка A -> C и A -> D, после обмена: A -> D и B -> D
// грузовик 2: посылка B -> C и B -> D, после обмена: B -> C и A -> C


    public static class Example4 {
        //Создаем обменник, который будет обмениваться типом String

        public static void main(String[] args) throws InterruptedException {
            Exchanger<String> exchanger = new Exchanger<>();

            //Формируем груз для 1 грузовика
            String[] p1 = new String[]{"{посылка A->D}", "{посылка A->C}"};

            //Формируем груз для 2 грузовика
            String[] p2 = new String[]{"{посылка B->C}", "{посылка B->D}"};


            //Отправляем 1 грузовик А -> D
            new Thread(new Truck(1, "A", "D", p1, exchanger)).start();

            Thread.sleep(100);

            //Отправляем 2 грузовик В -> С
            new Thread(new Truck(2, "B", "C", p2, exchanger)).start();
        }
    }

    static class Truck implements Runnable {
        private final int number;
        private final String departure;
        private final String destination;
        private final String[] parcels;
        private final Exchanger<String> exchanger;

        public Truck(int number, String departure, String destination, String[] parcels, Exchanger<String> exchanger) {
            this.number = number;
            this.departure = departure;
            this.destination = destination;
            this.parcels = parcels;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            Random r = new Random();
            try {
                System.out.println("В грузовик " + number + " погрузили: " + parcels[0] + " и " + parcels[1]);
                System.out.println("Грузовик " + number + " выехал из пункта " + departure + " в пункт " + destination + " с остановкой в пункте E");

                Thread.sleep(r.nextInt(2, 5) * 1000L);

                System.out.println("Грузовик " + number + " приехал в пункт Е");

                //При вызове exchange() поток блокируется и ждет пока другой
                // поток вызовет exchange(), после этого произойдет обмен посылками
                parcels[1] = exchanger.exchange(parcels[1]);

                System.out.println("В грузовик " + number + " переместили посылку для пункта " + destination);

                Thread.sleep(r.nextInt(2, 5) * 1000L);

                System.out.println("Грузовик " + number + " приехал в " + destination + " и доставил: " + parcels[0] + " и " + parcels[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

// крайне простой пример работы Phaser, который демонстрирует, что мы можем поделить поток на фазы и
// контролировать положение исполнения в рамках каждого потока

    public static class Example5 {

        public static void main(String[] args) throws InterruptedException {
            Phaser phaser = new Phaser(1);
            int curPhase;

            System.out.println("Starting threads");

            new Thread(new MyThread(phaser, "A")).start();
            new Thread(new MyThread(phaser, "B")).start();
            new Thread(new MyThread(phaser, "C")).start();


            curPhase = phaser.getPhase();

            // ожидаем завершения всеми потоками первой фазы
            phaser.arriveAndAwaitAdvance();
//            phaser.
            System.out.println("Phase " + curPhase + " completed");


            curPhase = phaser.getPhase();

            // ожидаем завершения всеми потоками второй фазы
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phase " + curPhase + " completed");

            // снимаем основной поток исполнения с регистрации
            phaser.arriveAndDeregister();

            Thread.sleep(2000);

            if (phaser.isTerminated())
                System.out.println("Phaser is terminated");

        }
    }

    static class MyThread implements Runnable {
        Phaser phaser;
        String name;

        public MyThread(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("Thread " + name + " is starting first phase");
            phaser.arriveAndAwaitAdvance();

            // небольшая пауза, чтобы не нарушать порядок вывода. Только для демонстрации, в реальности этого может не быть
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread " + name + " is starting second phase");
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread " + name + " is starting third phase");
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
        }
    }
}
