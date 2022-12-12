package de.telran.practice1;

public class Parrot extends Bird {

    public Parrot(String name) {
        super(name);
        System.err.println("Parrot born");
    }

    public void speak() {
        System.out.printf("%s is good%n", name);
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "name='" + name + '\'' +
                '}';
    }
}
