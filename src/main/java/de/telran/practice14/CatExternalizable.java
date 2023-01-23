package de.telran.practice14;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Objects;

public class CatExternalizable implements Externalizable {

    private String name;
    private String color;
    private  int age;
    public CatExternalizable() {
        System.out.println("Cat born");
    }

    public CatExternalizable(String name, String color) {
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
        if (!(o instanceof CatExternalizable cat)) return false;
        return Objects.equals(name, cat.name) && Objects.equals(color, cat.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(decrypt(name));
        out.writeObject(color);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         String encrypted = (String) in.readObject();
         this.color = (String) in.readObject();
         this.age = (int) in.readObject();
         this.name = decrypt(encrypted);
    }

    private String decrypt(String value) {
        return new StringBuilder(value).reverse().toString();
    }
}
