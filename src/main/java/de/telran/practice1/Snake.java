
package de.telran.practice1;

public class Snake extends Animal {

    public Snake(String name) {
        super(name);
    }

    @Override
    public void voice() {
        System.out.printf("%s shhhshshhh Bandarlogs%n", name);
    }

    @Override
    public void walk() {
        System.out.printf("%s crawl%n", name);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "name='" + name + '\'' +
                '}';
    }
}
