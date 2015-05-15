package by.academy.it.dao;

public interface UserDAO {

    boolean loginUser(String login, String password);
    boolean logoutUser();
//    void blockCreditCard(int bankAccountID, boolean isBlock);
}
