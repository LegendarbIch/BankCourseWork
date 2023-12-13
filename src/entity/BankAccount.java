package entity;

public class BankAccount {
    public BankAccount(String name, int phoneNumber, int cartNumber, int pincode) {
        this.name = name;
        this.cartNumber = cartNumber;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        balance = 0;
    }

    private final String name;

    private final int cartNumber;

    private final int phoneNumber;

    private int balance;

    private int pincode;

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public int getCartNumber() {
        return cartNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }
    public void setBalance(int total) {
        this.balance = total;
    }
}
