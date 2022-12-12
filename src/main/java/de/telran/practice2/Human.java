package de.telran.practice2;

public class Human implements Moving, Attacking {
    @Override
    public void move() {
        System.out.println("Human walks");
    }

    public void doSomething() {
        System.out.println("Something");
    }

    @Override
    public void attack() {
        System.out.println("Human shoots");
    }

    @Override
    public void doDefault() {
//        Attacking.super.doDefault();
        System.out.println("HUMAN DEFAULT");
    }
}
