package by.academy.it.dao;

import  by.academy.it.entity.*;

import java.util.List;

public interface ClientDAO extends UserDAO{
    int create(Client client);
    Client read(String login);
    Client getClientByBankAccountID(int bankAccountID);
    void update(Client client);
    void delete(String login);
    boolean payOrder(int orderID);

    List<Client> getAll();
}
