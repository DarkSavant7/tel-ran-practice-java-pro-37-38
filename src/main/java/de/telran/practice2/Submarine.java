package de.telran.practice2;

public class Submarine implements Moving, Attacking {
    @Override
    public void move() {
        System.out.println("Submarine dive");
    }

    @Override
    public void attack() {
        System.out.println("Submarine throws rockets");
    }
}
