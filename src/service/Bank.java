package service;

import commands.Command;
import commands.Replenishment;
import commands.Withdraw;
import entity.BankAccount;

public class Bank {
    public BankAccount operation(BankAccount bankAccount, int amount) {
        Command command;
        if (amount < 0) {
            command = new Withdraw(bankAccount, Math.abs(amount));
        } else {
            command = new Replenishment(bankAccount, amount);
        }
        command.execute();
        return bankAccount;
    }


}
