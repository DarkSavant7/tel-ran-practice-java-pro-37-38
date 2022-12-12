package de.telran.practice3;

import java.util.List;
import java.util.UUID;

public class EmployeeService {

    private EmployeeRepo repo;
    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public Employee changePosition(Employee employee, PositionType positionType) {
        employee.setPosition(positionType);
        return employee;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee promote(UUID employeeId)  {
        Employee emp = repo.findById(employeeId);
//        Employee emp = new Manager("");

        //.... some business logic

        PositionType type = emp.getPosition();
        if (type == PositionType.PRIVATE) {
            emp.setPosition(PositionType.MANAGER);
        } else if (type == PositionType.MANAGER) {
            emp.setPosition(PositionType.TOP);
        } else {
            throw new RuntimeException("TOP cannot be promouted");
        }
        return repo.save(emp);
    }
}
