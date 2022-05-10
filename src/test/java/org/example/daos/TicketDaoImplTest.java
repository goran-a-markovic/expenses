package org.example.daos;

import junit.framework.TestCase;
import org.example.customClasses.CustomList;
import org.example.entities.Ticket;
import org.junit.jupiter.api.BeforeEach;

public class TicketDaoImplTest extends TestCase {


    private TicketDao ticketDao;
    private EmployeeDao employeeDao;


    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("before");
        // since we're testing with h2, create the tables every time:
        ticketDao = DaoFactory.getTicketDao();
        employeeDao = DaoFactory.getEmployeeDao();
        employeeDao.initTables();
        employeeDao.fillTables();
        ticketDao.initTables();
        ticketDao.fillTables();
    }

    public void testInsert() {
        System.out.println("entered add");
        Ticket ticket = new Ticket(111, "gas", 1);
        ticketDao.insert(ticket);
        assertEquals(3, ticket.getId());
    }

    public void testGetTicketById() {
        System.out.println("entered id");
        Ticket employee = ticketDao.getTicketById(1);
        assertEquals("Ticket1", employee.getDescription());
    }

    public void testGetAllTickets() {
        CustomList<Ticket> ticketList = ticketDao.getAllTickets();
        assertEquals(1, ticketList.get(0).getId());
    }

    public void testGetAllTicketsEmployee() {
        CustomList<Ticket> ticketList = ticketDao.getAllTicketsEmployee(1);
        assertEquals(1, ticketList.get(0).getId());
    }
//
//    public void testGetTicket() {
//    }
//
    public void testGetPendingTickets() {
        CustomList<Ticket> ticketList = ticketDao.getPendingTickets(1);
        assertEquals(1, ticketList.get(0).getId());
    }

    public void testGetAllPendingTickets() {
        CustomList<Ticket> ticketList = ticketDao.getAllPendingTickets();
        assertEquals(1, ticketList.get(0).getId());
    }

    public void testGetPastTickets() {
        CustomList<Ticket> ticketList = ticketDao.getPastTickets(1);
        assertEquals(2, ticketList.get(0).getId());
    }

    public void testGetAllPastTickets() {
        CustomList<Ticket> ticketList = ticketDao.getAllPastTickets();
        assertEquals(2, ticketList.get(0).getId());
    }

//    public void testAcceptTicket() {
//        Ticket ticket = ticketDao.getTicketById(1);
//        ticketDao.acceptTicket(ticket, "accepted");
//        assertEquals("accepted", ticket.getStatus() );
//    }
}