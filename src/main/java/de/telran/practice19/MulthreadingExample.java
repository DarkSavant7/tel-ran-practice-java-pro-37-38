package de.telran.practice19;

public class MulthreadingExample {

    static volatile char currentLetter = 'a';
    static Object mon = new Object();

    //Locks

    public static void main(String[] args) {
        new Thread(() -> printLetter(5, 'a')).start();
        new Thread(() -> printLetter(5, 'b')).start();
        new Thread(() -> printLetter(5, 'c')).start();
    }

    private static void printLetter(int count, char letter) {
        for (int i = 0; i < count; i++) {
            printLetter(letter);
        }
    }

    static void printLetter(char letter) {
     synchronized (mon)  {
         try {
             while (letter != currentLetter) {
                 mon.wait();
             }
             System.out.print(letter);
             if (letter == 'c') System.out.println();
             updateLetter();
             mon.notifyAll();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
    }

    private static void updateLetter() {
        switch (currentLetter) {
            case 'a' -> currentLetter = 'b';
            case 'b' -> currentLetter = 'c';
            case 'c' -> currentLetter = 'a';
            default -> throw new RuntimeException("Unknown letter");
        }
    }
}
