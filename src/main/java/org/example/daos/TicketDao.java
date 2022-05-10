package org.example.daos;

import org.example.customClasses.CustomList;
import org.example.entities.Ticket;

import java.sql.ResultSet;


public interface TicketDao {

    public void insert(Ticket ticket);

    Ticket getTicketById(int id);

    CustomList<Ticket> getAllTickets();

    public Ticket getTicket(ResultSet resultSet);

    CustomList<Ticket> getPendingTickets(int actNumber);
    CustomList<Ticket> getAllPendingTickets();

    CustomList<Ticket> getPastTickets(int actNumber);
    CustomList<Ticket> getAllPastTickets();

    CustomList<Ticket> getAllTicketsEmployee(int employeeId);

    void acceptTicket(Ticket ticket, String decision);

    public void initTables();
    public void fillTables();
}

