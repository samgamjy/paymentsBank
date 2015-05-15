package by.academy.it.navigation.commands;

import by.academy.it.entity.Order;
import by.academy.it.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;

public class ListOrderByBankAccountCommand implements Command {
    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bankAccountID = Integer.parseInt(request.getParameter(PARAM_ORDER_BANK_ACCOUNT_ID));
        double sum = Double.parseDouble(request.getParameter(PARAM_ORDER_SUM));

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrderListByBankAccount(bankAccountID);

        try {
            request.setAttribute(PARAM_ORDER_LIST, orderList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_LIST_ORDERS);
            requestDispatcher.forward(request, response);
//                response.sendRedirect(PAGE_LIST_CLIENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PAGE_MAIN;
    }
}
