package de.telran.practice1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public static void main(String[] args) {
//        employeeExample();
//        zooExample();
        Animal a = new Parrot("Kesha");
       Parrot p = (Parrot) a;
       p.speak();
    }

    private static void zooExample() {
        Animal anonymousAnimal = new Animal("FFF") {
            @Override
            public void voice() {
                System.out.println("AAAAAA");
            }
        };

        Animal[] animals = {
                new Cat("Barsik"),
                new Bird("Chizik"),
                new Dog("Bobik"),
                new Snake("Kaa"),
                new Parrot("Kesha"),
                anonymousAnimal
        };

        for (int i = 0; i < animals.length; i++) {
            animals[i].voice();
            animals[i].walk();

//            if (animals[i] instanceof Bird) {
//                ((Bird) animals[i]).fly();
//            }
            if (animals[i] instanceof Bird bird) {
                bird.fly();
            }

            if (animals[i] instanceof Parrot p) {
                p.speak();
            }
        }
    }

    private static void employeeExample() {
        var employee = new Employee("Vasya", "manager", 21.23, 30, LocalDate.parse("2022-10-01"));
//        employee.setSalary(employee.ge);
        employee.setPosition("chief");
        System.out.println(employee);

        var someDate = LocalDate.of(2022, 10, 10);
        LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 10, 15, 14);

        System.out.printf("Vasya emp date is before some date ? >> %b%n",
                employee.getEmploymentDate().isBefore(someDate));
        System.out.printf("Time between dates is %s%n",
                DAYS.between(employee.getEmploymentDate(), someDate));

        double a = 0.8;
        double b = 0;

        for (int i = 0; i < 800; i++) {
            b += 0.001;
        }

        System.out.println(a == b);
        System.out.println(b);

        BigDecimal bd = new BigDecimal("100500.234324324234");

        bd.multiply(BigDecimal.valueOf(10), new MathContext(4, RoundingMode.HALF_UP));
    }
}