package org.example.daos;

import junit.framework.TestCase;
import org.example.customClasses.CustomList;
import org.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;

public class EmployeeDaoImplTest extends TestCase {

    private EmployeeDao employeeDao;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.initTables();
        employeeDao.fillTables();
    }

    public void testAddEmployee() {
        Employee employee = new Employee("Erin", "Brokovich");
        employeeDao.createEmployee(employee);
        assertEquals(4, employee.getId());
    }

    public void testGetByUsername() {
        Employee employee = employeeDao.getEmployeeByUsername("Employee1");
        assertEquals(1, employee.getId());
    }

    public void testGetAll() {
        CustomList<Employee> employeeList = employeeDao.getAllEmployees();
        assertEquals(3, employeeList.get(2).getId());
    }

}