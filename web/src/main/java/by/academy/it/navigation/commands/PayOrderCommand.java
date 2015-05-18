package by.academy.it.navigation.commands;

import by.academy.it.entity.BankAccount;
import by.academy.it.entity.Client;
import by.academy.it.entity.Order;
import by.academy.it.service.BankAccountService;
import by.academy.it.service.ClientService;
import by.academy.it.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;

public class PayOrderCommand implements Command {
    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute(PARAM_SESSION_USER_LOGIN) != null) {
            String login = (String) session.getAttribute(PARAM_SESSION_USER_LOGIN);
            ClientService clientService = new ClientService();
            Client client = clientService.getClient(login);
            int bankAccountID = Integer.parseInt(request.getParameter(PARAM_ORDER_BANK_ACCOUNT_ID));
            int orderID = Integer.parseInt(request.getParameter(PARAM_ORDER_ID));

            OrderService orderService = new OrderService();
            Order order = orderService.getOrder(orderID);

            BankAccountService bankAccountService = new BankAccountService();
            BankAccount bankAccount = bankAccountService.getBankAccount(client.getBankAccountID());

            if ((order.getId() == orderID) && (!order.isPaid())) {
                if (bankAccountService.payOrder(order)) {
                    orderService.setOrderPay(orderID, true);
                    bankAccount = bankAccountService.getBankAccount(client.getBankAccountID());
                }
            }

            List<Order> orderList = orderService.getOrderListByBankAccount(client.getBankAccountID());

            List<Integer> bankAccountIDList = bankAccountService.getBankAccountIDList();
            request.setAttribute(PARAM_BANK_ACCOUNT_TRANSFER_ID_LIST, bankAccountIDList);
            request.setAttribute(PARAM_CLIENT_ITEM, client);
            request.setAttribute(PARAM_BANK_ACCOUNT_ITEM, bankAccount);
            request.setAttribute(PARAM_ORDER_LIST, orderList);


            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_PROFILE_CLIENT);
            requestDispatcher.forward(request, response);

            return PAGE_PROFILE_CLIENT;
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_MAIN);
            requestDispatcher.forward(request, response);
        }

        return null;
    }
}
