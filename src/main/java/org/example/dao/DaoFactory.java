package org.example.dao;

public class DaoFactory {
    private static TicketDao ticketDao;

    private DaoFactory() {

    }

    public static TicketDao getTicketDao() {
        if (ticketDao == null) {
            ticketDao = new TicketDaoImpl();
        }
        return ticketDao;
    }

}
