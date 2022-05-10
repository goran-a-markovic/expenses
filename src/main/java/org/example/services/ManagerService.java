package org.example.services;

import org.example.daos.DaoFactory;
import org.example.daos.ManagerDao;
import org.example.entities.Manager;

public class ManagerService {

    private ManagerDao managerDao = DaoFactory.getManagerDao();


    public static Manager getManagerByName(String name) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        Manager manager = managerDao.getManagerByUsername(name);
        return manager;
    }
}

