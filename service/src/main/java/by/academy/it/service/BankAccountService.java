package by.academy.it.service;

import by.academy.it.dao.BankAccountDAO;
import by.academy.it.dao.impl.JDBCBankAccountDAOImpl;
import by.academy.it.entity.BankAccount;
import by.academy.it.entity.Order;

import java.util.List;

/**
 * Created by Sam on 06.05.2015.
 */
public class BankAccountService {
    private BankAccountDAO bankAccountDAO = new JDBCBankAccountDAOImpl();

    public int create(boolean valid, boolean blocked, double sum, int creditCardID) {
        return bankAccountDAO.create(new BankAccount(sum, valid, blocked, creditCardID));
    }

    public BankAccount getBankAccount(int bankAccountID) {
        return bankAccountDAO.read(bankAccountID);
    }

    public void deleteBankAccount(int bankAccountID){
       bankAccountDAO.delete(bankAccountID);
    }

    public void blockBankAccount(int bankAccountID, boolean isBlock){
        bankAccountDAO.blockCreditCard(bankAccountID, isBlock);
    }

    public boolean transferMoney(int dstBankAccountID, int srcBankAccountID, double transferSum){
        return bankAccountDAO.transferMoney(dstBankAccountID, srcBankAccountID, transferSum);
    }

    public boolean payOrder(Order order){
        return bankAccountDAO.payOrder(order);
    }

    public List<Integer> getBankAccountIDList(){
        return bankAccountDAO.getBankAccountIDList();
    }

    public List<BankAccount> getAll() {
        return bankAccountDAO.getAll();
    }
}
