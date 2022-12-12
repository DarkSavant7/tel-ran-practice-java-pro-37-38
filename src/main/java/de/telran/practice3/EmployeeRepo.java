package de.telran.practice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class EmployeeRepo {
    private ArrayList<Employee> db;
//    private HashSet<Employee> db;

    public EmployeeRepo() {
        this.db = new ArrayList<>(List.of(
                new Manager("A"),
                new Manager("B"),
                new PrivateEmployee("C"),
                new PrivateEmployee("D"),
                new TopManager("E")
        ));

//        this.db = new HashSet<>();
    }

    public Employee save(Employee employee) {
        db.add(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        Employee employeeToUpdate = findById(employee.getId());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setPosition(employee.getPosition());
//        return save(employeeToUpdate);
        return employeeToUpdate;
    }

    public Employee findById(UUID id) {
        for (Employee employee : db) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Employee find(Employee employee) {
        for (Employee employee1 : db) {
            if (employee.equals(employee1)) {
                return employee1;
            }
        }
        return null;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(db);
    }
}
