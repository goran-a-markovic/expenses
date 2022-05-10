package org.example.services;

import junit.framework.TestCase;
import org.example.daos.DaoFactory;
import org.example.daos.ManagerDao;
import org.example.entities.Manager;

public class ManagerServiceTest extends TestCase {

    public void setUp() throws Exception {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        managerDao.initTables();
        managerDao.fillTables();
    }

    public void testGetManagerByName() {
        Manager m = ManagerService.getManagerByName("Manager1");
        assertEquals(1, m.getId());
    }
}