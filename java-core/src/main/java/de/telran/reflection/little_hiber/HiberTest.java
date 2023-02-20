package de.telran.reflection.little_hiber;

public class HiberTest {
    public static void main(String[] args) {
        LittleHiber.createTable(Cat.class);
        Cat[] cats = {
                new Cat(1, "Barsik", "white"),
        new Cat(2, "Barsik2", "white"),
        new Cat(3, "Barsik3", "white")
        };

        for (Cat cat : cats) {
            LittleHiber.insertObjectToDatabase(cat);
        }

        LittleHiber.createTable(Employee.class);
        Employee emp = new Employee(
                20,
                "Vasya",
                50,
                100500,
                "vasuya@mail.ru",
                "999999999",
                "kjsdfbsfbkjbdfv"
        );

        LittleHiber.insertObjectToDatabase(emp);
    }
}
