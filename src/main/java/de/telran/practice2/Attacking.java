package de.telran.practice2;

@FunctionalInterface
public interface Attacking {
    String SOME_FIELD = "SOME";

    void attack();

    //since 1.8
    default void doDefault() {
        System.out.println("DEFAULT!!!!");
        doPrivate();
    }

    //since 9
    static void doStatic() {
        System.out.println("STATIC");
    }

    //since 9
    private void doPrivate() {
        System.out.println("PRIVATE!!!!");
    }
}
