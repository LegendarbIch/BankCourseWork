package dto;

public class BankAccountDTO{

    public BankAccountDTO(String name, Long phoneNumber, Long cartNumber, int balance) {
        this.name = name;
        this.cartNumber = cartNumber;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    private final String name;

    private final Long cartNumber;

    private final Long phoneNumber;

    private int balance;

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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return
                "Имя:           '" + name + '\n' +
                "Номер карты:    " + cartNumber + '\n' +
                "Номер телефона: " + phoneNumber + '\n' +
                "Баланс:         " + balance;
    }
}
