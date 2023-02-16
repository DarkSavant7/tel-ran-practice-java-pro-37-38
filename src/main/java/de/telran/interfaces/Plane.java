package de.telran.interfaces;

public class Plane implements Moving {
    @Override
    public void move() {
        System.out.println("Plane fly");
    }
}
