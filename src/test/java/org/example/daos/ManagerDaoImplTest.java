package org.example.daos;

import junit.framework.TestCase;
import org.example.entities.Employee;
import org.example.entities.Manager;

public class ManagerDaoImplTest extends TestCase {

    private ManagerDao managerDao;

    public void setUp() throws Exception {
        super.setUp();
        managerDao = DaoFactory.getManagerDao();
        managerDao.initTables();
        managerDao.fillTables();
    }

    public void testGetManagerByUsername() {
        Manager manager = managerDao.getManagerByUsername("Manager1");
        assertEquals(1, manager.getId());
    }
}