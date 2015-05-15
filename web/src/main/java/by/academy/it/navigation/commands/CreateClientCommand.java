package by.academy.it.navigation.commands;

import by.academy.it.entity.Client;
import by.academy.it.service.BankAccountService;
import by.academy.it.service.ClientService;
import by.academy.it.service.CreditCardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;

public class CreateClientCommand implements Command {

    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String login = request.getParameter(PARAM_CLIENT_ID);
        String password = request.getParameter(PARAM_CLIENT_PASSWORD);
        String firstName = request.getParameter(PARAM_CLIENT_FIRST_NAME);
        String lastName = request.getParameter(PARAM_CLIENT_LAST_NAME);


        CreditCardService creditCardService = new CreditCardService();
        int creditCardID = creditCardService.create();

//        int creditCardID = Integer.parseInt(request.getParameter(PARAM_BANK_ACCOUNT_CREDIT_CARD_ID));

        if (creditCardID != INVALID_PARAM) {

            BankAccountService bankAccountService = new BankAccountService();
            int bankAccountID = bankAccountService.create(true, false, 0, creditCardID);
            if (bankAccountID != INVALID_PARAM) {
                ClientService clientService = new ClientService();
                int clientID = clientService.create(login, password, firstName, lastName, bankAccountID);

                if (clientID == INVALID_PARAM) {
                    bankAccountService.deleteBankAccount(bankAccountID);
                    creditCardService.delete(creditCardID);
                }

                List<Client> clients = clientService.getAll();
                request.setAttribute(PARAM_CLIENTS, clients);
            }else{
                creditCardService.delete(creditCardID);
            }
        }
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_LIST_CLIENTS);
            requestDispatcher.forward(request, response);
            response.sendRedirect(PAGE_LIST_CLIENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PAGE_MAIN;
    }

    private boolean isValidParams(String login, String pass, String firstName, String lastName) {
        return login != null && pass != null && firstName != null && lastName != null;
    }

}
