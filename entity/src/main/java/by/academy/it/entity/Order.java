package by.academy.it.entity;

/**
 * Created by Sam on 03.05.2015.
 */
public class Order {
    private int id;
    private int bankAccountID;
    private double sum;
    private boolean paid;

    public Order() {
    }

    public Order(int bankAccountID, double sum, boolean paid) {
        this.bankAccountID = bankAccountID;
        this.sum = sum;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bankAccountID=" + bankAccountID +
                ", sum=" + sum +
                ", paid=" + paid +
                '}';
    }
}
