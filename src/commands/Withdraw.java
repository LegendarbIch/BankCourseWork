package commands;

import entity.BankAccount;

public class Withdraw implements Command {

    private final BankAccount bankAccount;

    private final int amount;

    public Withdraw(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }
}
