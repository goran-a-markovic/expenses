package org.example.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customClasses.CustomList;
import org.example.entities.Ticket;
import org.example.services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class TicketServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int idToGet;
        try {
            idToGet = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            CustomList<Ticket> tickets = TicketService.getAllTickets();
            System.out.println("All Tickets:");
            String json = mapper.writeValueAsString(tickets);
            System.out.println(json);
            res.setStatus(200);
            out.print(json);
            return;
        }

        Ticket ticket = TicketService.getTicketById(idToGet);
        out.write(ticket.toString());
        System.out.println(ticket);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Ticket payload = mapper.readValue(req.getReader(), Ticket.class);
            payload.setStatus("pending");
            Ticket ticket = TicketService.add(payload);
            resp.setStatus(203);
            resp.getWriter().print("Ticket submitted successfully!\n");
        } catch (IOException e) {
            resp.setStatus(500);
            resp.getWriter().print("Something went wrong when adding the ticket.");
            System.out.println(e.getLocalizedMessage());
        }
    }


    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(req.getReader(), Ticket.class);
        String decision = ticket.getStatus();
        System.out.println(decision);
        Ticket result = TicketService.acceptTicket(ticket, decision);
        System.out.println("Status jebeni");
        System.out.println(result.getStatus());
        PrintWriter out = res.getWriter();
        out.write("Ticket edited!");
    }
//
//    @Override
//    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        // get the id from the request parameter:
//        int idToDelete = Integer.parseInt(req.getParameter("id"));
//        // call the service:
//        boolean success = BookService2.delete(idToDelete);
//        // check if deletion was successful and send the appropriate response:
//        if(success) {
//            res.setStatus(200);
//        }
//        else {
//            res.sendError(500, "Deletion failed!");
//        }
//    }


}