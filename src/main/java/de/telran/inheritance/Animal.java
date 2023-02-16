package de.telran.inheritance;

import java.util.Objects;

public abstract class Animal {
    protected String name;

    private Animal() {
//        super();
        System.err.println("Animal born");
    }

    public Animal(String name) {
        this();
        this.name = name;
    }

    public abstract void voice();

    public void walk() {
        System.out.printf("%s walks on paws%n", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
