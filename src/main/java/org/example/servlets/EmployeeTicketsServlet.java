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

public class EmployeeTicketsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String status = req.getParameter("status");
        int empId = Integer.parseInt(req.getParameter("employee_id"));
        if (status == null) {
            CustomList<Ticket> tickets = TicketService.getAllTicketsEmployee(empId);
            System.out.println("All Tickets for this Employee:");
            String json = mapper.writeValueAsString(tickets);
            res.setStatus(200);
            out.print(json);
        }
        System.out.println(status);
        if (status.equals("pending")) {
            CustomList<Ticket> tickets = TicketService.getPendingTickets(empId);
            System.out.println("All Pending Tickets for this Employee:");
            String json = mapper.writeValueAsString(tickets);
            System.out.println(json);
            res.setStatus(200);
            out.print(json);
        } else if (status.equals("past")) {
            CustomList<Ticket> tickets = TicketService.getPastTickets(empId);
            System.out.println("All Past Tickets for this Employee:");
            String json = mapper.writeValueAsString(tickets);
            System.out.println(json);
            res.setStatus(200);
            out.print(json);
        }
    }


}
