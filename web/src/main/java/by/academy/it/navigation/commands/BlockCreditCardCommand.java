package by.academy.it.navigation.commands;

import by.academy.it.entity.Client;
import by.academy.it.service.BankAccountService;
import by.academy.it.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.it.resources.constant.Constants.*;


public class BlockCreditCardCommand implements Command {
    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        if (session != null && session.getAttribute(PARAM_SESSION_USER_LAST_NAME) != null) {
//            String login = (String)session.getAttribute(PARAM_SESSION_USER_LOGIN);
//            ClientService clientService = new ClientService();
//            Client client = clientService.getClient(login);
            BankAccountService bankAccountService = new BankAccountService();
            int bankAccountID = Integer.parseInt(request.getParameter(PARAM_CLIENT_BANK_ACCOUNT_ID));
            boolean blockCreditCard = Boolean.parseBoolean(request.getParameter(PARAM_BANK_ACCOUNT_BLOCKED));


            ClientService clientService = new ClientService();
            Client client = clientService.getClient(bankAccountID);
        bankAccountService.blockBankAccount(client.getBankAccountID(), blockCreditCard);

            request.setAttribute(PARAM_CLIENT_ITEM, client);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_CLIENT_INFO);
            requestDispatcher.forward(request, response);

//        }
        return PAGE_CLIENT_INFO;
    }
}
