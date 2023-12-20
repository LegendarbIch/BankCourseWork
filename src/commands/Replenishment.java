package commands;

import entity.BankAccount;

public class Replenishment implements Command {

    private final BankAccount bankAccount;

    private final int amount;

    public Replenishment(BankAccount bankAccount, int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
