package by.academy.it.dao.impl;

import by.academy.it.dao.CreditCardDAO;
import by.academy.it.entity.CreditCard;
import by.academy.it.pool.SimpleBasicDataSource;
import by.academy.it.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.academy.it.resources.constant.Constants.*;

public class JDBCCreditCardDAOImpl implements CreditCardDAO {

    private SimpleBasicDataSource dataSource;
    private Logger logger = Logger.getLogger(JDBCCreditCardDAOImpl.class);

    public JDBCCreditCardDAOImpl() {
        this.dataSource = SimpleBasicDataSource.getInstance();
        logger.info("->Constructor JDBCCreditCardDAOImpl(this.dataSource = " + this.dataSource + ")");
    }

    @Override
    public int create(CreditCard creditCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        logger.info("->create CreditCard(creditCard = " + creditCard.getId() + ")");
        int creditCardID = INVALID_PARAM;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_CREDIT_CARD, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.executeUpdate();
            logger.info("--getCreditCardByBankAccountID executeUpdate()");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                creditCardID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("--create.Exception = " + e.getErrorCode());
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-create CreditCard = " + creditCardID);
        return creditCardID;
    }

    @Override
    public int getCreditCardByBankAccountID(int bankAccountID) {
        logger.info("->getCreditCardByBankAccountID(bankAccountID = " + bankAccountID + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_GET_CREDIT_CARD);

            preparedStatement.setString(1, Integer.toString(bankAccountID));

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("--getCreditCardByBankAccountID executeQuery()");
            if (resultSet.next()){
                logger.info("<-getCreditCardByBankAccountID = " + resultSet.getInt(1));
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("--getCreditCardByBankAccountID.Exception = " + e.getErrorCode());
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement, connection);
        }

        logger.info("<-getCreditCardByBankAccountID = " + INVALID_PARAM);
        return INVALID_PARAM;
    }

    @Override
    public void delete(int creditCardID) {
        logger.info("->delete(creditCardID = " + creditCardID + ")");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_CREDIT_CARD);

            preparedStatement.setString(1, Integer.toString(creditCardID));

            preparedStatement.executeUpdate();
            logger.info("--delete executeUpdate()");
        } catch (SQLException e) {
            logger.error("--delete delete.Exception = " + e.getErrorCode());
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement, connection);
        }
        logger.info("<-delete(creditCardID = " + creditCardID + ")");
    }
}
