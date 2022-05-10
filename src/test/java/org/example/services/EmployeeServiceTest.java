package org.example.services;

import junit.framework.TestCase;
import org.example.customClasses.CustomList;
import org.example.daos.DaoFactory;
import org.example.daos.EmployeeDao;
import org.example.entities.Employee;

public class EmployeeServiceTest extends TestCase {

    public void setUp() throws Exception {
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.initTables();
        employeeDao.fillTables();
    }

    public void testGetAllEmployees() {
        CustomList<Employee> employees = EmployeeService.getAllEmployees();
        assertEquals(3, employees.get(2).getId());
        assertEquals(1, employees.get(0).getId());
    }

    public void testRegister() {
        Employee employee = new Employee("Eric", "Cantona");
        EmployeeService.register(employee);
        assertEquals(4, employee.getId());
    }

    public void testGetEmployeeByName() {
        Employee employee  = EmployeeService.getEmployeeByName("Employee1");
        assertEquals(1, employee.getId());
    }
}