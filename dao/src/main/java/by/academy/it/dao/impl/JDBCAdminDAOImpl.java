package by.academy.it.dao.impl;

import by.academy.it.dao.AdminDAO;

/**
 * Created by Sam on 11.05.2015.
 */
public class JDBCAdminDAOImpl implements AdminDAO {
    @Override
    public boolean loginUser(String login, String password) {
        return false;
    }

    @Override
    public boolean logoutUser() {
        return false;
    }

//    @Override
//    public void blockCreditCard(int bankAccountID, boolean isBlock) {
//
//    }
}
