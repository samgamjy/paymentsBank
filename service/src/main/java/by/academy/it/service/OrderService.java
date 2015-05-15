package by.academy.it.service;

import by.academy.it.dao.OrderDAO;
import by.academy.it.dao.impl.JDBCOrderDAOImpl;
import by.academy.it.entity.Order;

import java.util.List;

/**
 * Created by Sam on 15.05.2015.
 */
public class OrderService {
    private OrderDAO orderDAO = new JDBCOrderDAOImpl();

    public int createOrder(int bankAccountID, double sum) {
        return orderDAO.createOrder(new Order(bankAccountID, sum, false));
    }

    public void deleteOrder(int orderID){
        orderDAO.deleteOrder(orderID);
    }

    public Order getOrder(int orderID){
        return orderDAO.getOrder(orderID);
    }

    public List<Order> getOrderList(){
        return orderDAO.getOrderList();
    }

    public List<Order> getOrderListByBankAccount(int bankAccountID){
        return orderDAO.getOrderListByBankAccount(bankAccountID);
    }

}
