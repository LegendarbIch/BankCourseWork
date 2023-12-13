package entity;

import java.util.Date;

public class AccountOperation {
    private final int cartNumber;

    private final int cartNumberRecipient;

    private final int amount;

    private final java.sql.Date date;

    public AccountOperation(int cartNumber, int cartNumberRecipient, int amount, java.sql.Date date) {
        this.cartNumber = cartNumber;
        this.cartNumberRecipient = cartNumberRecipient;
        this.amount = amount;
        this.date = date;
    }

    public int getCartNumber() {
        return cartNumber;
    }

    public int getCartNumberRecipient() {
        return cartNumberRecipient;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
