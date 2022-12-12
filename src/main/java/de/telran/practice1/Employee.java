package de.telran.practice1;

import java.time.LocalDate;
import java.time.Year;

//private -> "" -> protected -> public
public class Employee {
    private String name;
    private String position;
    private double salary;
    private int bornYear;
    private LocalDate employmentDate;

    public Employee(String name, String position, double salary, int age, LocalDate employmentDate) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.bornYear = Year.now().getValue() - age;
        this.employmentDate = employmentDate;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return Year.now().getValue() - bornYear;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return String.format("Employee: name=%s, position=%s, salary=%s, age=%d, employment date=%s",
                name, position, salary, getAge(), employmentDate);
    }
}
