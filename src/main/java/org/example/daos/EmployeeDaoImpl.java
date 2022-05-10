package org.example.daos;

import org.example.customClasses.CustomArrayList;
import org.example.customClasses.CustomList;
import org.example.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionManager.getConnection();
    }


    @Override
    public void createEmployee(Employee employee) { //TODO more lines??
        String sql = "insert into employees (id, username, password) values (default, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            //should there be line here for id?
            //No, because when we're inserting a new guy to the database, he's gonna get the ID automatically,
            // it says default above, we're not passing anything, it's not a question mark
            //That's why it's important to have a constructor without an ID, just Username and password
            //so we are able to create a guy without an id here
            int employeesCreated = preparedStatement.executeUpdate();
            if (employeesCreated == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Employee ID is : " + id);
            } else {
                System.out.println("Something went wrong when running your transaction!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Employee getEmployeeById(int id) {
        Employee e = new Employee();
        String sql = "select * from employees where id = ?;";
        ResultSet resultSet = null;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                e.setId(resultSet.getInt("id"));
                e.setUsername(resultSet.getString("username"));
                e.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return e;
    }

    @Override
    public CustomList<Employee> getAllEmployees() {
        CustomList<Employee> employeeList = new CustomArrayList<>();
        String sql = "select * from employees;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee();

                int employeeId = resultSet.getInt("id");
                employee.setId(employeeId);

                String username = resultSet.getString("username");
                employee.setUsername(username);

                String password = resultSet.getString("password");
                employee.setPassword(password);

                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

//    public Employee getUserByName(String name) {
//        String sql = "select * from employee where name = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                Employee employee = getEmployee(resultSet);
//                return employee;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        Employee employee = new Employee();
        String sql = "select * from employees where username = ?;";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                System.out.println(employee.toString());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return employee;
    }

    @Override
    public void updateEmployee(int id) {
        String sql = "update employees set username = ? where id = ?;";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int updatedEmployee = preparedStatement.executeUpdate();
            if (updatedEmployee == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
