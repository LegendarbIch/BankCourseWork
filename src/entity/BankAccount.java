package entity;

public class BankAccount {
    public BankAccount(String name, Long phoneNumber, long cartNumber, int pincode, int balance) {
        this.name = name;
        this.cartNumber = cartNumber;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        this.balance = balance;
    }

    private final String name;

    private final Long cartNumber;

    private final Long phoneNumber;

    private int balance;

    private int pincode;

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public int getPincode() {
        return pincode;
    }

    public String getName() {
        return name;
    }

    public Long getCartNumber() {
        return cartNumber;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int total) {
        this.balance = total;
    }

    @Override
    public String toString() {
        return  "name         " + name + '\n' +
                "cartNumber   " + cartNumber + '\n' +
                "phoneNumber  " + phoneNumber + '\n' +
                "balance      " + balance + '\n';

    }
}
