package org.example.services;

import org.example.customClasses.CustomList;
import org.example.daos.DaoFactory;
import org.example.daos.TicketDao;
import org.example.entities.Ticket;

public class TicketService {

    private TicketDao ticketDao = DaoFactory.getTicketDao();

    public static CustomList<Ticket> getAllTickets() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getAllTickets();
    }

    public static CustomList<Ticket> getAllTicketsEmployee(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getAllTicketsEmployee(id);
    }

    public static Ticket getTicketById(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getTicketById(id);
    }

    public static CustomList<Ticket> getPendingTickets(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getPendingTickets(id);
    }

    public static CustomList<Ticket> getPastTickets(int id) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getPastTickets(id);
    }
    public static CustomList<Ticket> getAllPendingTickets() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getAllPendingTickets();
    }

    public static CustomList<Ticket> getAllPastTickets() {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        return ticketDao.getAllPastTickets();
    }

    public static Ticket add(Ticket ticket) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        ticketDao.insert(ticket);
        return ticket;
    }

    public static Ticket acceptTicket(Ticket ticket, String decision) {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        ticketDao.acceptTicket(ticket, decision);
        System.out.println("Service:");
        System.out.println(decision);
        return ticket;
    }
//
//    public static boolean delete(int id) {
//        TicketDao ticketDao = DaoFactory.getTicketDao();
//        return ticketDao.delete(id);
//    }
}