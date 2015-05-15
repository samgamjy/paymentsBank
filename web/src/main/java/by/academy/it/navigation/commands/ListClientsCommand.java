package by.academy.it.navigation.commands;

import by.academy.it.entity.Client;
import by.academy.it.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static by.academy.it.resources.constant.Constants.*;

public class ListClientsCommand implements Command {

    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientService clientService = new ClientService();
        List<Client> clients = clientService.getAll();
        request.setAttribute(PARAM_CLIENTS, clients);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_LIST_CLIENTS);//PAGE_CLIENT_INFO);
        requestDispatcher.forward(request, response);
        return PAGE_LIST_CLIENTS;
    }
}
