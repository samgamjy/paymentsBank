package by.academy.it.service;

import by.academy.it.dao.ClientDAO;
import by.academy.it.dao.impl.JDBCClientDAOImpl;
import by.academy.it.entity.Client;
import by.academy.it.entity.UserRole;

import java.util.List;

public class ClientService {

    private ClientDAO clientDAO = new JDBCClientDAOImpl();

    public int create(String login, String password, String firstName, String lastName, int bankAccountID) {
        return clientDAO.create(new Client(login, password, firstName, lastName, UserRole.CLIENT, bankAccountID));
    }

    public Client getClient(String login) {
        return clientDAO.read(login);
    }

    public Client getClient(int bankAccountID){
        return clientDAO.getClientByBankAccountID(bankAccountID);
    }

    public boolean login(String login, String password){
        return clientDAO.loginUser(login, password);
    }


    public void deleteClient(String login){
        clientDAO.delete(login);
    }

    public List<Client> getAll() {
        return clientDAO.getAll();
    }

}
