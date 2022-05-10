package org.example.services;

import junit.framework.TestCase;
import org.example.customClasses.CustomList;
import org.example.daos.DaoFactory;
import org.example.daos.EmployeeDao;
import org.example.daos.TicketDao;
import org.example.entities.Ticket;

public class TicketServiceTest extends TestCase {

    public void setUp() throws Exception {
        TicketDao ticketDao = DaoFactory.getTicketDao();
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.initTables();
        employeeDao.fillTables();
        ticketDao.initTables();
        ticketDao.fillTables();
    }

    public void testGetAllTickets() {
        CustomList<Ticket> ticketList = TicketService.getAllTickets();
        assertEquals("Ticket2", ticketList.get(1).getDescription());
    }

    public void testGetTicketById() {
        Ticket t = TicketService.getTicketById(1);
        assertEquals("Ticket1", t.getDescription());
    }

    public void testGetPendingTickets() {
        CustomList<Ticket> ticketList = TicketService.getPendingTickets(1);
        assertEquals("Ticket1", ticketList.get(0).getDescription());
    }

    public void testGetPastTickets() {
        CustomList<Ticket> ticketList = TicketService.getPastTickets(1);
        assertEquals("Ticket2", ticketList.get(0).getDescription());
    }

    public void testGetAllTicketsEmployee() {
        CustomList<Ticket> ticketList = TicketService.getAllTicketsEmployee(1);
        assertEquals("Ticket1", ticketList.get(0).getDescription());
    }

    public void testGetAllPendingTickets() {
        CustomList<Ticket> ticketList = TicketService.getAllPendingTickets();
        assertEquals("Ticket1", ticketList.get(0).getDescription());
    }

    public void testGetAllPastTickets() {
        CustomList<Ticket> ticketList = TicketService.getAllPastTickets();
        assertEquals("Ticket2", ticketList.get(0).getDescription());
    }

    public void testAdd() {
        Ticket t = new Ticket(12.5, "Added1", 1);
        TicketService.add(t);
        System.out.println(t);
        assertEquals(3, t.getId());
    }

//    public void testAcceptTicket() {
//        Ticket t = TicketService.getTicketById(1);
//        System.out.println(t);
//        CustomList<Ticket> ticketList = TicketService.getAllTickets();
//
//        ticketList.print();
//        Ticket y = TicketService.acceptTicket(t, "accepted");
//        System.out.println(t);
//        Ticket x = TicketService.getTicketById(1);
//        assertEquals("accepted", y.getStatus());
//    }
}