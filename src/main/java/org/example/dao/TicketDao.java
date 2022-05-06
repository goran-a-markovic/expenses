package org.example.dao;

import org.example.customClasses.CustomList;
import org.example.entities.Ticket;

import java.sql.ResultSet;


public interface TicketDao {

    public void insert(Ticket ticket);

    //
    Ticket getTicketById(int id);

    CustomList<Ticket> getAllTickets();

    public Ticket getTicket(ResultSet resultSet);

    CustomList<Ticket> getPendingTickets(int actNumber);

    CustomList<Ticket> getPastTickets(int actNumber);

    void acceptTicket(int id, String decision);

}

