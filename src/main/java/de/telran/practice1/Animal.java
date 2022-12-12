package de.telran.practice1;

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
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                '}';
    }
}
