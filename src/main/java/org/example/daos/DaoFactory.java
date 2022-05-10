package org.example.daos;

public class DaoFactory {
    private static EmployeeDao employeeDao;
    private static TicketDao ticketDao;
    private static ManagerDao managerDao;

    private DaoFactory() {

    }

    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }

    public static ManagerDao getManagerDao() {
        if (managerDao == null) {
            managerDao = new ManagerDaoImpl();
        }
        return managerDao;
    }


    public static TicketDao getTicketDao() {
        if (ticketDao == null) {
            ticketDao = new TicketDaoImpl();
        }
        return ticketDao;
    }


}
