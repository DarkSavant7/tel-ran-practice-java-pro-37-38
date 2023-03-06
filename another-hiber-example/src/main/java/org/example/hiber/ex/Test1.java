package org.example.hiber.ex;

import org.example.hiber.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test1 {
    public static void main(String[] args) {
        Employee employee = new Employee("Elina", "S", "It", 2500);
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory())
        {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

            System.out.println("DONE!");
            session.close();
        }
    }
}