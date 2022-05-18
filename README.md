# EXPENSES REIMBURSEMENT SYSTEM

## Project Description

REST API for the Expenses Reimbursement System

## Technologies Used

Java\
JDBC\
PostgreSQL\
Tomcat Server 9.0\
Maven\
Servlets\
JUnit\

## Features

Register new employees
Login of existing employees and managers
Employees can view all their tickets, all their pending tickets or all their past tickets
They can also submit a new ticket
Managers can view all of the pending tickets or all of the past tickets
And they can accept or reject a ticket that is pending

To-do list:
* Restrict the access to employees/managers
* Improve test coverage

## Getting Started

Both Windows and Unix, follow these steps:

git clone https://github.com/goran-a-markovic/expenses
Create local database and change resources/dbConfig.properties accordingly
Open and make sure your Tomcat Server installed and running.


## Usage

Go to http://localhost:8080/expenses or use Postman testing tool for APIs
List of the API endpoints you can hit:
/employees (POST - register a new employee)
/employee/login (GET - login an existing employee)
/tickets (POST - submit an expense ticket)
/tickets/{id} (GET - get all the tickets for the employee with that id ordered by date)
/tickets/{id}/past (GET - get all the past tickets for the employee with that id)
/tickets/{id}/pending (GET - get all the pending tickets for the employee with that id)

/manager/login (GET - login as a manager)
/manager/tickets?status=pending (GET - get all the pending tickets)
/manager/tickets/past (GET - get all the past tickets)
/manager/tickets (PUT - accept or reject a ticket)

## Contributors

Kyle Weiding, Goran Markovic
