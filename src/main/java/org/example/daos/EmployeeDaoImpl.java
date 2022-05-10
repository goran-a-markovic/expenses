package org.example.daos;

import org.example.customClasses.CustomArrayList;
import org.example.customClasses.CustomList;
import org.example.entities.Employee;

import java.sql.*;


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
                employee.setId(id);
                System.out.println("Employee ID is : " + id);
            } else {
                System.out.println("Something went wrong when adding a user!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//
//    @Override
//    public Employee getEmployeeById(int id) {
//        Employee e = new Employee();
//        String sql = "select * from employees where id = ?;";
//        ResultSet resultSet = null;
//
//        try (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                e.setId(resultSet.getInt("id"));
//                e.setUsername(resultSet.getString("username"));
//                e.setPassword(resultSet.getString("password"));
//            }
//
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//        return e;
//    }

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

//    @Override
//    public void updateEmployee(int id) {
//        String sql = "update employees set username = ? where id = ?;";
//
//        try (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, id);
//            int updatedEmployee = preparedStatement.executeUpdate();
//            if (updatedEmployee == 1) System.out.println("Update successful!");
//            else System.out.println("Something went wrong with the update");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // we need this method if we're using an h2 database, keep in mind that our database gets "reset" every time, we run the program
    @Override
    public void initTables() {
        // we don't see any ? placeholders because this statement will be the same every time
        String sql = "drop table if exists employees cascade; create table employees(\n" +
                "\tid serial primary key,\n" +
                "\tusername varchar(50),\n" +
                "\tpassword varchar(50)\n" +
                "\t);";
        // we could add a procedure as well as so we can test it with h2
        try {
            // creating a statement instead of preparinf it
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("initializing tables");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void fillTables() {
        String sql = "insert into employees(id, username, password) values(default, 'Employee1', 'emp1');\n";
        sql += "insert into employees(id, username, password) values(default, 'Employee2', 'emp2');\n";
        sql += "insert into employees(id, username, password) values(default, 'Employee3', 'emp3');\n";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("filling with data");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
