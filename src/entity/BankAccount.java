package entity;

public class BankAccount {
    public BankAccount(String name, Long phoneNumber, long cartNumber, int pincode) {
        this.name = name;
        this.cartNumber = cartNumber;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        balance = 0;
    }

    private final String name;

    private final Long cartNumber;

    private final Long phoneNumber;

    private int balance;

    private int pincode;

    public void setPincode(int pincode) {
        this.pincode = pincode;
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
}
