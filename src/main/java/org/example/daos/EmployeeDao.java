package org.example.daos;

import org.example.customClasses.CustomList;
import org.example.entities.Employee;

public interface EmployeeDao {
    void createEmployee(Employee employee);

    Employee getEmployeeByUsername(String username);

//    Employee getEmployeeById(int id);

    CustomList<Employee> getAllEmployees(); //should this be pluralized?

//    void updateEmployee(int id);

    public void initTables();
    public void fillTables();

}
