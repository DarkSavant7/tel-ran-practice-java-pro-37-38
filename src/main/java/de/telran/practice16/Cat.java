package de.telran.practice16;

import java.io.Serializable;
import java.util.Objects;

public class Cat extends Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String color;
    private  int age;
    public Cat() {
        super("");
        System.out.println("Cat born");
    }

    public Cat(String name, String color) {
        this();
        this.name = name;
        this.color = color;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat cat)) return false;
        return Objects.equals(name, cat.name) && Objects.equals(color, cat.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
