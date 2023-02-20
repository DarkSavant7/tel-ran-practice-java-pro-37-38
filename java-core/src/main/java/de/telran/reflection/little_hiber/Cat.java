package de.telran.reflection.little_hiber;

@Table(name = "cats")
public class Cat {

    @HField
    @Id(autoincrement = true)
    private int id;

    @HField
//    @Id(autoincrement = true)
    private String name;

    @HField
    private String color;

    public Cat( String name, String color) {
        this.name = name;
        this.color = color;
    }
    public Cat(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
