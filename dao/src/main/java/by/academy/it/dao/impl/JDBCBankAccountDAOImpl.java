package by.academy.it.dao.impl;

import by.academy.it.dao.BankAccountDAO;
import by.academy.it.entity.BankAccount;
import by.academy.it.entity.Order;
import by.academy.it.pool.SimpleBasicDataSource;
import by.academy.it.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.academy.it.resources.constant.Constants.*;

/**
 * Created by Sam on 06.05.2015.
 */
public class JDBCBankAccountDAOImpl implements BankAccountDAO {

    private SimpleBasicDataSource dataSource;
    private Logger logger = Logger.getLogger(JDBCCreditCardDAOImpl.class);

    public JDBCBankAccountDAOImpl() {
        this.dataSource = SimpleBasicDataSource.getInstance();
    }

    @Override
    public int create(BankAccount bankAccount) {
        logger.info("->JDBCBankAccountDAOImpl.int create(bankAccount = " + bankAccount + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int bankAccountID = INVALID_PARAM;
        int numero;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_BANK_ACCOUNT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setBoolean(1, bankAccount.isValid());
            preparedStatement.setBoolean(2, bankAccount.isBlocked());
            preparedStatement.setInt(3, bankAccount.getCreditCardID());
            preparedStatement.setDouble(4, bankAccount.getSum());

            numero = preparedStatement.executeUpdate();
            logger.info("--JDBCBankAccountDAOImpl.create executeUpdate()");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                bankAccountID = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCBankAccountDAOImpl.int create(bankAccount = " + bankAccount + ") = " + bankAccountID);
        return bankAccountID;

    }


    @Override
    public BankAccount read(int bankAccountID) {
        logger.info("->JDBCBankAccountDAOImpl.BankAccount read(bankAccountID = " + bankAccountID + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        BankAccount bankAccount = new BankAccount();
//        int bankAccountID = INVALID_PARAM;
//        int numero;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_BANK_ACCOUNT_ITEM);

            preparedStatement.setInt(1, bankAccountID);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCBankAccountDAOImpl.read executeQuery()");
            if (resultSet.next()) {
                bankAccount.setId(resultSet.getInt(1));
                bankAccount.setValid(resultSet.getBoolean(2));
                bankAccount.setBlocked(resultSet.getBoolean(3));
                bankAccount.setCreditCardID(resultSet.getInt(4));
                bankAccount.setSum(resultSet.getDouble(5));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCBankAccountDAOImpl.read.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCBankAccountDAOImpl.BankAccount read(bankAccountID = " + bankAccountID + ") = " + bankAccount);
        return bankAccount;
    }

    @Override
    public void update(BankAccount bankAccount) {

    }

    @Override
    public void delete(int bankAccountID) {
        logger.info("->JDBCBankAccountDAOImpl.void delete(bankAccountID = " + bankAccountID + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_BANK_ACCOUNT);
            preparedStatement.setInt(1, bankAccountID);

            preparedStatement.executeUpdate();
            logger.info("--JDBCBankAccountDAOImpl.delete executeUpdate()");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCBankAccountDAOImpl.delete.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCBankAccountDAOImpl.void delete(bankAccountID = " + bankAccountID + ")");
    }

    @Override
    public void blockCreditCard(int bankAccountID, boolean isBlock) {
        logger.info("->JDBCBankAccountDAOImpl.void blockCreditCard(bankAccountID = " + bankAccountID + ", isBlock = " + isBlock + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
//        Client client = new Client();

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_BLOCK_BANK_ACCOUNT);
//            byte block = (byte) ((isBlock)? 1:0);
            preparedStatement.setBoolean(1, isBlock);
            preparedStatement.setInt(2, bankAccountID);
            preparedStatement.executeUpdate();
            logger.info("--JDBCBankAccountDAOImpl.blockCreditCard executeUpdate()");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCBankAccountDAOImpl.blockCreditCard.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-JDBCBankAccountDAOImpl.void blockCreditCard(bankAccountID = " + bankAccountID + ", isBlock = " + isBlock + ")");
    }

    @Override
    public boolean transferMoney(int dstBankAccountID, int srcBankAccountID, double transferSum) {
        logger.info("->JDBCBankAccountDAOImpl.boolean transferMoney(dstBankAccountID = " + dstBankAccountID + ", srcBankAccountID = " + srcBankAccountID + ", transferSum = " + transferSum + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BankAccount srcBankAccount = read(srcBankAccountID);
        if (srcBankAccount != null) {
            if (srcBankAccount.getSum() >= transferSum) {
                if (!srcBankAccount.isBlocked() && srcBankAccount.isValid()) {
                    BankAccount dstBankAccount = read(dstBankAccountID);
                    if (dstBankAccount != null) {
                        try {
                            connection = dataSource.getConection();
                            preparedStatement = connection.prepareStatement(SQL_BANK_ACCOUNT_TRANSFER_MONEY);
                            preparedStatement.setDouble(1, srcBankAccount.getSum() - transferSum);
                            preparedStatement.setInt(2, srcBankAccount.getId());
                            preparedStatement.executeUpdate();
                            logger.info("--JDBCBankAccountDAOImpl.transferMoney executeUpdate(SQL_BANK_ACCOUNT_TRANSFER_MONEY)");

                        } catch (SQLException e) {
                            e.printStackTrace();
                            logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());
                        }

                        try {
                            connection = dataSource.getConection();
                            preparedStatement = connection.prepareStatement(SQL_BANK_ACCOUNT_TRANSFER_MONEY);
                            preparedStatement.setDouble(1, dstBankAccount.getSum() + transferSum);
                            preparedStatement.setInt(2, dstBankAccount.getId());
                            preparedStatement.executeUpdate();
                            logger.info("--JDBCBankAccountDAOImpl.transferMoney executeUpdate(SQL_BANK_ACCOUNT_TRANSFER_MONEY)");
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());

                            try {
                                connection = dataSource.getConection();
                                preparedStatement = connection.prepareStatement(SQL_BANK_ACCOUNT_TRANSFER_MONEY);
                                preparedStatement.setDouble(1, srcBankAccount.getSum() + transferSum);
                                preparedStatement.setInt(2, srcBankAccount.getId());
                                preparedStatement.executeUpdate();
                                logger.info("--JDBCBankAccountDAOImpl.transferMoney executeUpdate(SQL_BANK_ACCOUNT_TRANSFER_MONEY)");
                            } catch (SQLException exceptionReturnMoney) {
                                e.printStackTrace();
                                logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());
                            }
                        }
                    }
                }
            }
        }
        logger.info("->JDBCBankAccountDAOImpl.boolean transferMoney(dstBankAccountID = " + dstBankAccountID + ", srcBankAccountID = " + srcBankAccountID + ", transferSum = " + transferSum + ") = false");
        return false;
    }

    @Override
    public List<Integer> getBankAccountIDList() {
        logger.info("->List<Integer> getBankAccountIDList()");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Integer> bankAccountIDList = null;
        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_BANK_ACCOUNT_ID_LIST);

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--JDBCBankAccountDAOImpl.getBankAccountIDList executeQuery(SQL_BANK_ACCOUNT_TRANSFER_MONEY)");
            bankAccountIDList = initBankAccountIDs(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-List<Integer> getBankAccountIDList() = " + bankAccountIDList);
        return bankAccountIDList;
    }

    private List<Integer> initBankAccountIDs(ResultSet resultSet) throws SQLException {
        List<Integer> bankAccountIDList = new ArrayList<Integer>();
        while (resultSet.next()) {
            bankAccountIDList.add(resultSet.getInt(1));
        }
        return bankAccountIDList;
    }

    @Override
    public List<BankAccount> getAll() {
        return null;
    }


    @Override
    public boolean payOrder(Order order) {
        logger.info("->JDBCOrderDAOImpl.boolean payOrder(order = " + order + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (order != null) {
            int bankAccountID = order.getBankAccountID();
            BankAccount bankAccount = read(bankAccountID);
            double sum = order.getSum();

            try {
                connection = dataSource.getConection();
                preparedStatement = connection.prepareStatement(SQL_BANK_ACCOUNT_TRANSFER_MONEY);
                preparedStatement.setDouble(1, bankAccount.getSum() - order.getSum());
                preparedStatement.setInt(2, bankAccountID);
                preparedStatement.executeUpdate();
                logger.info("--JDBCBankAccountDAOImpl.boolean payOrder(SQL_BANK_ACCOUNT_TRANSFER_MONEY)");

            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("--JDBCBankAccountDAOImpl.create.Exception = " + e.getErrorCode());
                return false;
            } finally {
                DBUtils.close(preparedStatement, connection);

            }


        }
        logger.info("<-boolean payOrder(order = " + order + ") = true");
        return true;
    }
}
