package org.example.daos;

import org.example.entities.Manager;

import java.sql.*;

public class ManagerDaoImpl implements ManagerDao {

    Connection connection;

    public ManagerDaoImpl() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Manager getManagerByUsername(String username) {
        Manager manager = new Manager();
        String sql = "select * from managers where username = ?;";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("usao u ovaj nekst jebeni");
                manager.setId(resultSet.getInt("id"));
                manager.setUsername(resultSet.getString("username"));
                manager.setPassword(resultSet.getString("password"));
                System.out.println(manager.toString());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return manager;
    }

    @Override
    public void initTables() {
        // we don't see any ? placeholders because this statement will be the same every time
        String sql = "drop table if exists managers cascade; create table managers(\n" +
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
        String sql = "insert into managers(id, username, password) values(default, 'Manager1', 'emp1');\n";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("filling with data");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
