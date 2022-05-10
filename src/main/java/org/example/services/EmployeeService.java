package org.example.services;

import org.example.customClasses.CustomList;
import org.example.daos.DaoFactory;
import org.example.daos.EmployeeDao;
import org.example.entities.Employee;

public class EmployeeService {

    private EmployeeDao employeeDao = DaoFactory.getEmployeeDao();

    public static CustomList<Employee> getAllEmployees() {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        return employeeDao.getAllEmployees();
    }

    public static Employee register(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.createEmployee(employee);
        return employee;
    }

    public static Employee getEmployeeByName(String name) {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        Employee employee = employeeDao.getEmployeeByUsername(name);
        return employee;
    }
}
