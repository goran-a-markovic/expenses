package org.example.dao;

import org.example.ConnectionFactory;
import org.example.customClasses.CustomArrayList;
import org.example.customClasses.CustomList;
import org.example.entities.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TicketDaoImpl implements TicketDao {

    Connection connection;

    public TicketDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insert(Ticket ticket) {
        String sql = "INSERT INTO tickets (id, price, description, status, time_created, employee_id) values (default, ?, ?, ?, current_timestamp, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, ticket.getPrice());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getStatus());
            preparedStatement.setInt(4, ticket.getEmployeeId());;
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                System.out.println("Ticket ID is : " + id);
            } else {
                System.out.println("Something went wrong when running your transaction!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //
    @Override
    public Ticket getTicketById(int id) {
        String sql = "select * from tickets where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = getTicket(resultSet);
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    @Override
    public CustomList<Ticket> getAllTickets() {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Ticket ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket getTicket(ResultSet resultSet) {
        try {
            int idData = resultSet.getInt("id");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            String status = resultSet.getString("status");
            int employeeId = resultSet.getInt("employee_id");
            Date createdAt = resultSet.getDate("created_at");
            return new Ticket (idData, price, description, status, createdAt, employeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    @Override
    public CustomList<Ticket> getPendingTickets(int employeeId) {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets where status = 'pending' and employee_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Ticket ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public CustomList<Ticket> getPastTickets(int employeeId) {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets where status != 'pending' and employee_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Ticket ticket = getTicket(resultSet);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void acceptTicket(int id, String decision) {
        String sql = "update tickets set status = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, id);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Update successful!");
            else System.out.println("Something went wrong with the update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void update(Book book) {
//        String sql = "update book set name = ?, author = ?, description = ?, year = ? where id = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, book.getName());
//            preparedStatement.setString(2, book.getAuthor());
//            preparedStatement.setString(3, book.getDescription());
//            preparedStatement.setInt(4, book.getYear());
//            preparedStatement.setInt(5, book.getId());
//            int count = preparedStatement.executeUpdate();
//            if (count == 1) System.out.println("Update successful!");
//            else System.out.println("Something went wrong with the update");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}