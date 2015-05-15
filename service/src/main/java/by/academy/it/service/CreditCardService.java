package by.academy.it.service;

import by.academy.it.dao.CreditCardDAO;
import by.academy.it.dao.impl.JDBCCreditCardDAOImpl;
import by.academy.it.entity.CreditCard;

/**
 * Created by Sam on 06.05.2015.
 */
public class CreditCardService {

    private CreditCardDAO creditCardDAO = new JDBCCreditCardDAOImpl();

    public int create() {
        return creditCardDAO.create(new CreditCard());
    }

    public void delete(int creditCardID){
        creditCardDAO.delete(creditCardID);
    }
}
