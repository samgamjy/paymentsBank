package by.academy.it.entity;

/**
 * Created by vasilevich on 25.04.2015.
 */
public class BankAccount {
    private int id;
    private double sum;
    private boolean valid;
    private boolean blocked;
    private int creditCardID;

    public BankAccount() {
    }

    public BankAccount(double sum, boolean valid, boolean blocked, int creditCardID) {
        this.sum = sum;
        this.valid = valid;
        this.blocked = blocked;
        this.creditCardID = creditCardID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(int creditCardID) {
        this.creditCardID = creditCardID;
    }
}
