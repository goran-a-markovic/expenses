package org.example.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customClasses.CustomList;
import org.example.entities.Ticket;
import org.example.services.TicketService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerTicketsOptionsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String status = req.getParameter("status");
        System.out.println(status);
        if (status.equals("pending")) {
            CustomList<Ticket> tickets = TicketService.getAllPendingTickets();
            System.out.println("All pending tickets in the world:");
            String json = mapper.writeValueAsString(tickets);
            System.out.println(json);
            res.setStatus(200);
            out.print(json);
        } else {
            CustomList<Ticket> tickets = TicketService.getAllPastTickets();
            System.out.println("All past tickets:");
            String json = mapper.writeValueAsString(tickets);
            System.out.println(json);
            res.setStatus(200);
            out.print(json);
        }

    }
}
