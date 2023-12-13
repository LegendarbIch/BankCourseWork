package dto;

public class BankAccountDTO{

    public BankAccountDTO(String name, int phoneNumber, int cartNumber, int balance) {
        this.name = name;
        this.cartNumber = cartNumber;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    private final String name;

    private final int cartNumber;

    private final int phoneNumber;

    private int balance;

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

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
