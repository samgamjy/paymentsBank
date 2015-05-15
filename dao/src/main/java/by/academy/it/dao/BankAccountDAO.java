package by.academy.it.dao;

import by.academy.it.entity.BankAccount;

import java.util.List;

public interface BankAccountDAO {
    int create(BankAccount bankAccount);
    BankAccount read(int bankAccountID);
    void update(BankAccount bankAccount);
    void delete(int bankAccountID);
    void blockCreditCard(int bankAccountID, boolean isBlock);
    boolean transferMoney(int dstBankAccountID, int srcBankAccountID, double transferSum);
    List<Integer> getBankAccountIDList();
    List<BankAccount> getAll();
}





