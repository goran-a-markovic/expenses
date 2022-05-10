package org.example.entities;

import java.util.Date;

public class Ticket {
    private int id;
    private double price;
    private String description;
    private String status;
    private Date createdAt;
    private int employeeId;

    public Ticket() {

    }

    public Ticket(double price, String description, String status) {
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Ticket(double price, String description, int employeeId) {
        this.price = price;
        this.description = description;
        this.employeeId = employeeId;
    }


    public Ticket(int id, double price, String description, String status, Date createdAt, int employeeId) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
