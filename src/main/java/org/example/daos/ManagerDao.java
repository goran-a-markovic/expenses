package org.example.daos;

import org.example.entities.Manager;

public interface ManagerDao {
    Manager getManagerByUsername(String username);
    public void initTables();
    public void fillTables();
}
