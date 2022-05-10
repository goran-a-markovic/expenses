package org.example.daos;

import org.example.entities.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
