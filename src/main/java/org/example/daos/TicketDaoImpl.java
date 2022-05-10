package org.example.daos;

import org.example.customClasses.CustomArrayList;
import org.example.customClasses.CustomList;
import org.example.entities.Ticket;

import java.sql.*;

public class TicketDaoImpl implements TicketDao {

    Connection connection;

    public TicketDaoImpl() {
        connection = ConnectionManager.getConnection();
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
                ticket.setId(id);
                ticket.setStatus("pending");
                System.out.println("Ticket ID is : " + id);
            } else {
                System.out.println("Something went wrong when adding a ticket!");
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
        CustomList<Ticket> tickets = new CustomArrayList<>();
        String sql = "select * from tickets order by time_created;";
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
            Timestamp createdAt = resultSet.getTimestamp("time_created");
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
    public CustomList<Ticket> getAllTicketsEmployee(int employeeId) {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets where employee_id = ?;";
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
    public CustomList<Ticket> getAllPendingTickets() {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets where status = 'pending';";
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
    public CustomList<Ticket> getAllPastTickets() {
        CustomList<Ticket> tickets = new CustomArrayList<Ticket>();
        String sql = "select * from tickets where status != 'pending';";
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
    public void acceptTicket(Ticket ticket, String decision) {
        String sql = "update tickets set status = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, decision);
            preparedStatement.setInt(2, ticket.getId());
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

    @Override
    public void initTables() {
        // we don't see any ? placeholders because this statement will be the same every time
        String sql = "drop table if exists tickets cascade; create table tickets(\n" +
                "\tid serial primary key,\n" +
                "\tprice integer,\n" +
                "\tdescription varchar(200),\n" +
                "\tstatus varchar(50),\n" +
                "\ttime_created timestamp,\n" +
                "\temployee_id int,\n" +
                "\tforeign key (employee_id) references employees(id)\n" +
                "\tON DELETE cascade\n" +
                "\t);";

        // we could add a procedure as well as so we can test it with h2
        try {
            // creating a statement instead of preparinf it
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void fillTables() {
        String sql = "insert into tickets(id, price, description, status, time_created, employee_id) values (default, 11, 'Ticket1', 'pending', current_timestamp, 1);\n";
        sql += "insert into tickets(id, price, description, status, time_created, employee_id) values (default, 11, 'Ticket2', 'accepted', current_timestamp, 1);\n";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}