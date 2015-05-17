package by.academy.it.dao.impl;

import by.academy.it.dao.OrderDAO;
import by.academy.it.entity.Order;
import by.academy.it.pool.SimpleBasicDataSource;
import by.academy.it.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;

/**
 * Created by Sam on 15.05.2015.
 */
public class JDBCOrderDAOImpl implements OrderDAO {
    private SimpleBasicDataSource dataSource;
    private Logger logger = Logger.getLogger(JDBCCreditCardDAOImpl.class);

    public JDBCOrderDAOImpl() {
        this.dataSource = SimpleBasicDataSource.getInstance();
        logger.info("->JDBCOrderDAOImpl.Constructor JDBCOrderDAOImpl(this.dataSource = " + this.dataSource + ")");
    }

    @Override
    public int createOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        logger.info("->JDBCOrderDAOImpl.int createOrder(order = " + order + ")");
        int orderID = INVALID_PARAM;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDERS, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, order.getBankAccountID());
            preparedStatement.setDouble(2, order.getSum());
            preparedStatement.setBoolean(3, false);
            preparedStatement.executeUpdate();
            logger.info("--JDBCOrderDAOImpl.createOrder executeUpdate()");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                orderID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.createOrder.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.int createOrder = " + orderID);
        return orderID;
    }

    @Override
    public Order getOrder(int orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Order order = new Order();
        logger.info("->JDBCOrderDAOImpl.Order getOrder(orderID = " + orderID + ")");

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_ITEM);

            preparedStatement.setInt(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCOrderDAOImpl.getOrder executeQuery()");
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setBankAccountID(resultSet.getInt(2));
                order.setSum(resultSet.getDouble(3));
                order.setPaid(resultSet.getBoolean(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.getOrder.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.Order getOrder(orderID = " + orderID + ") = " + order);
        return order;
    }


    @Override
    public void deleteOrder(int orderID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        logger.info("->JDBCOrderDAOImpl.void deleteOrder(orderID = " + orderID + ')');

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER);

            preparedStatement.setInt(1, orderID);
            preparedStatement.executeUpdate();
            logger.info("--JDBCOrderDAOImpl.deleteOrder executeUpdate()");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.deleteOrder.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.void deleteOrder(orderID = " + orderID + ')');
    }

    @Override
    public List<Order> getOrderList() {
        logger.info("->JDBCOrderDAOImpl.List<Order> getOrderList()");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Order> orderList = null;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_LIST);

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCOrderDAOImpl.getOrderList executeQuery(SQL_GET_ORDER_LIST)");
            orderList = initOrderList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.getOrderList.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.List<Order> getOrderList()");
        return orderList;
    }

    private List<Order> initOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<Order>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt(1));
            order.setBankAccountID(resultSet.getInt(2));
            order.setSum(resultSet.getDouble(3));
            order.setPaid(resultSet.getBoolean(4));
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public List<Order> getOrderListByBankAccount(int bankAccountID) {
        logger.info("->JDBCOrderDAOImpl.List<Order> getOrderListByBankAccount()");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Order> orderList = null;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_LIST_BY_BANK_ACCOUNT);
            preparedStatement.setInt(1, bankAccountID);

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCOrderDAOImpl.getOrderListByBankAccount executeQuery(SQL_GET_ORDER_LIST_BY_BANK_ACCOUNT)");
            orderList = initOrderList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.getOrderListByBankAccount.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.List<Order> getOrderListByBankAccount()");
        return orderList;
    }

    @Override
    public void setOrderPay(int orderID, boolean isPaid) {
        logger.info("->JDBCOrderDAOImpl.void setOrderPay(orderID = " + orderID + "isPaid" + isPaid + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_ORDER_PAID);
            preparedStatement.setBoolean(1, isPaid);
            preparedStatement.setInt(2, orderID);

            preparedStatement.executeUpdate();
            logger.info("--JDBCOrderDAOImpl.setOrderPay executeUpdate(SQL_ORDER_PAID)");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCOrderDAOImpl.getOrderListByBankAccount.Exception = " + e.getErrorCode());
            logger.info("<-JDBCOrderDAOImpl.void setOrderPay(orderID = " + orderID + "isPaid" + isPaid + ") = false");
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCOrderDAOImpl.void setOrderPay(orderID = " + orderID + "isPaid" + isPaid + ") = true");
    }
}
