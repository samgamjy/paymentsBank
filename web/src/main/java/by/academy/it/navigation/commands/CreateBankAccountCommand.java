package by.academy.it.navigation.commands;

import by.academy.it.service.BankAccountService;
import by.academy.it.service.CreditCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.academy.it.resources.constant.Constants.*;

/**
 * Created by Sam on 06.05.2015.
 */
public class CreateBankAccountCommand implements Command {
    @Override
    public String execCommand(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CreditCardService creditCardService = new CreditCardService();
        int creditCardID = creditCardService.create();

//        int creditCardID = Integer.parseInt(request.getParameter(PARAM_BANK_ACCOUNT_CREDIT_CARD_ID));

        if (creditCardID != INVALID_PARAM) {

            BankAccountService bankAccountService = new BankAccountService();
            int bankAccountID = bankAccountService.create(true, false, 0, creditCardID);
            if (bankAccountID == INVALID_PARAM) {
                creditCardService.delete(creditCardID);
            }
        }
        response.sendRedirect(PAGE_LIST_CLIENTS);
        return PAGE_MAIN;
    }
}
