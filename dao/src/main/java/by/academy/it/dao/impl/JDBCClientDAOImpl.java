package by.academy.it.dao.impl;

import by.academy.it.dao.ClientDAO;
import by.academy.it.entity.Client;
import by.academy.it.entity.UserRole;
import by.academy.it.pool.SimpleBasicDataSource;
import by.academy.it.utils.DBUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;


public class JDBCClientDAOImpl implements ClientDAO {

    private SimpleBasicDataSource dataSource;
    private Logger logger = Logger.getLogger(JDBCCreditCardDAOImpl.class);
    private String logPropFile = "log4j.properties";

    public JDBCClientDAOImpl() {
        this.dataSource = SimpleBasicDataSource.getInstance();
        PropertyConfigurator.configure(logPropFile);
        logger.info("->JDBCClientDAOImpl.Constructor JDBCClientDAOImpl(this.dataSource = " + this.dataSource + ")");
    }

    @Override
    public int create(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        logger.info("->JDBCClientDAOImpl.int create(client = " + client + ")");
        int clientID = INVALID_PARAM;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getFirstName());
            preparedStatement.setString(4, client.getLastName());
            preparedStatement.setInt(5, client.getBankAccountID());
            preparedStatement.setInt(6, client.getRole() + 1);

            preparedStatement.executeUpdate();
            logger.info("--JDBCClientDAOImpl.create executeUpdate()");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                 clientID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-iJDBCClientDAOImpl.nt create(client = " + client + ") = " + clientID);
        return clientID;
    }

    @Override
    public Client read(String login) {
        logger.info("->JDBCClientDAOImpl.Client read(login = " + login + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = new Client();

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_CLIENT_ITEM);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            logger.info("--JDBCClientDAOImpl.read executeUpdate()");
            if (resultSet.next()) {
                client.setLogin(resultSet.getString(1));
                client.setPassword(resultSet.getString(2));
                client.setFirstName(resultSet.getString(3));
                client.setLastName(resultSet.getString(4));
                client.setBankAccountID(resultSet.getInt(5));
                client.setRole(UserRole.values()[resultSet.getInt(6)]);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCClientDAOImpl.Client read(login = " + login + ") = " + client);
        return client;
    }

    @Override
    public Client getClientByBankAccountID(int bankAccountID) {
        logger.info("->JDBCClientDAOImpl.Client getClientByBankAccountID(bankAccountID = " + bankAccountID + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = new Client();

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_CLIENT_ITEM_BY_BANK_ACCOUNT);
            preparedStatement.setInt(1, bankAccountID);
            resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCClientDAOImpl.getClientByBankAccountID executeQuery()");

            if (resultSet.next()) {
                client.setLogin(resultSet.getString(1));
                client.setPassword(resultSet.getString(2));
                client.setFirstName(resultSet.getString(3));
                client.setLastName(resultSet.getString(4));
                client.setBankAccountID(resultSet.getInt(5));
                client.setRole(UserRole.values()[resultSet.getInt(6) - 1]);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCClientDAOImpl.Client getClientByBankAccountID(bankAccountID = " + bankAccountID + ") = " + client);
        return client;    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(String login) {
        logger.info("->JDBCClientDAOImpl.void delete(login = " + login + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = new Client();

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_CLIENT_ACCOUNT);
            preparedStatement.setString(1, login);
            preparedStatement.executeQuery();
            logger.info("--JDBCClientDAOImpl.delete executeUpdate()");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCClientDAOImpl.void delete(login = " + login + ")");
    }

    @Override
    public boolean payOrder(int orderID) {
        return false;
    }

    @Override
    public List<Client> getAll() {
        logger.info("->JDBCClientDAOImpl.List<Client> getAll");
        List<Client> clientList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_CLIENTS);
            logger.info("--JDBCClientDAOImpl.getAll executeQuery()");
            clientList = initClients(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(statement, resultSet, connection);
        }
        logger.info("<-JDBCClientDAOImpl.List<Client> getAll = " + clientList);
        return clientList;
    }

    private List<Client> initClients(ResultSet resultSet) throws SQLException {
        List<Client> clientList = new ArrayList<Client>();
        while (resultSet.next()) {
            Client client = new Client();
            client.setLogin(resultSet.getString(1));
            client.setFirstName(resultSet.getString(3));
            client.setLastName(resultSet.getString(4));
            client.setBankAccountID(Integer.parseInt(resultSet.getString(5)));
            client.setRole(UserRole.values()[Integer.parseInt(resultSet.getString(6))]);
            clientList.add(client);
        }
        return clientList;
    }

    @Override
    public boolean loginUser(String login, String password) {
        logger.info("->JDBCClientDAOImpl.boolean loginUser(login = " + login + ", password = " + password + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = new Client();

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_CLIENT_ITEM);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCClientDAOImpl.loginUser executeQuery()");

            if (resultSet.next()) {
                logger.info("<-JDBCClientDAOImpl.boolean loginUser(login = " + login + ", password = " + password + ") = " + resultSet.getString(2).equals(password));
                return (resultSet.getString(2).equals(password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCClientDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCClientDAOImpl.boolean loginUser(login = " + login + ", password = " + password + ") = false");
        return false;
    }

    @Override
    public boolean logoutUser() {
        return false;
    }

}
