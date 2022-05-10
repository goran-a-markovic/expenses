package org.example.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customClasses.CustomList;
import org.example.entities.Employee;
import org.example.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomList<Employee> employees = EmployeeService.getAllEmployees();
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String json = mapper.writeValueAsString(employees);
            resp.setStatus(200);
            resp.getWriter().print(json);
        } catch (IOException e) {
            resp.setStatus(500);
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Employee payload = mapper.readValue(req.getInputStream(), Employee.class);
            EmployeeService.register(payload);
            resp.setStatus(203);
            resp.getWriter().print("Employee added!");
        } catch (IOException e) {
            resp.setStatus(500);
            resp.getWriter().print("Something went wrong when adding the employee.");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
