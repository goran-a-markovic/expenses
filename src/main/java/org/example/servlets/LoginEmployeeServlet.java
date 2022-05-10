package org.example.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Employee;
import org.example.services.EmployeeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginEmployeeServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String nameEntered = req.getParameter("username");
        System.out.println(nameEntered);
        String passwordEntered = req.getParameter("password");
        Employee goal = EmployeeService.getEmployeeByName(nameEntered);
        System.out.println(goal);
        if (goal.getPassword() != null) {
            if (goal.getPassword().equals(passwordEntered)) {
                System.out.println("Logged in successfully");
                String json = mapper.writeValueAsString(goal);
                res.setStatus(200);
                res.getWriter().print(json);
                res.getWriter().print("\nLogged in successfully");
            } else {
                System.out.println("Your password is wrong, sir");
                res.setStatus(500);
                res.getWriter().print("Wrong password");
            }
        } else {
            System.out.println("No employees with that name");
            res.setStatus(500);
            res.getWriter().print("No employees with that name");
        }

    }
}
