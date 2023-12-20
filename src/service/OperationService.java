package service;

import auth.AuthContext;
import entity.BankAccount;
import db.repository.OperationRepository;

public class OperationService {
    private final OperationRepository repository;

    public OperationService(OperationRepository repository) {
        this.repository = repository;
    }

    public void remittance(BankAccount toFrom, int amount) {
       repository.changeUsersBalance(AuthContext.getBankAccount(),toFrom, amount);
    }

    public void withdraw(int amount) {
       repository.reduceUserBalance(AuthContext.getBankAccount(), amount);
    }
    public void replenishment(int amount) {
        repository.increaseUserBalance(AuthContext.getBankAccount(), amount);
    }
}
