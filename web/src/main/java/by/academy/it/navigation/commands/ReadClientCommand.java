package by.academy.it.navigation.commands;

import by.academy.it.entity.Client;
import by.academy.it.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.academy.it.resources.constant.Constants.*;

/**
 * Created by Sam on 04.05.2015.
 */
public class ReadClientCommand implements Command {
    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(PARAM_CLIENT_ID);
        ClientService clientService = new ClientService();
        Client client = clientService.getClient(login);

        request.setAttribute(PARAM_CLIENT_ITEM, client);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_CLIENT_INFO);
        requestDispatcher.forward(request, response);

        return PAGE_CLIENT_INFO;
    }
}
