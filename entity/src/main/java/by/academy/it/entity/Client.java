package by.academy.it.entity;

/**
 * Created by vasilevich on 25.04.2015.
 */
public class Client extends User {

    private int bankAccountID;

    public Client() {
    }

    public Client(String login, String password, String firstName, String lastName, UserRole role, int bankAccountID) {
        super(login, password, firstName, lastName, role);
        this.bankAccountID = bankAccountID;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    @Override
    public String toString() {
        return "Client{" +
                "bankAccountID=" + bankAccountID +
                '}';
    }
}
