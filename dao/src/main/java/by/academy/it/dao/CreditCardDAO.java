package by.academy.it.dao;

import by.academy.it.entity.CreditCard;

/**
 * Created by Sam on 03.05.2015.
 */
public interface CreditCardDAO {
    int create(CreditCard creditCard);
    void delete(int creditCardID);
    int getCreditCardByBankAccountID(int bankAccountID);
}
