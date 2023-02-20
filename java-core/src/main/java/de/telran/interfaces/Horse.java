package de.telran.interfaces;

public class Horse implements Moving {
    @Override
    public void move() {
        System.out.println("Horse run");
    }
}
