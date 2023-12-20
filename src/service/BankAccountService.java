package service;

import auth.AuthContext;
import dto.BankAccountDTO;
import entity.BankAccount;
import db.repository.BankAccountRepository;

import java.util.Random;

public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(String fullName, long phoneNumber, int pincode) {
        Random random = new Random();
        long cartNumber = (random.nextLong(100000000));
        if (repository.findAccountByCardNumber(cartNumber) == null) {
            BankAccount bankAccount = new BankAccount(fullName, phoneNumber, cartNumber, pincode, 0);
            repository.saveAccount(bankAccount);
            return bankAccount;
        } else {
            createAccount(fullName, phoneNumber, pincode);
        }
        return null;
    }
    public boolean auth(Long cartNumber, int pincode) {
        BankAccountDTO account = repository.findAccountByPinCodeAndCardNumber(cartNumber, pincode);
        if (account != null) {
            AuthContext.setAuthContext(new BankAccount(account.getName(), account.getPhoneNumber(), account.getCartNumber(), pincode, account.getBalance()));
            AuthContext.getAuthContext().auth();
            return true;
        }
        return false;
    }
    public BankAccount getAccountByCartNumber(Long cartNumber) {
        BankAccountDTO dto = repository.findAccountByCardNumber(cartNumber);
        return new BankAccount(dto.getName(), dto.getPhoneNumber(), dto.getCartNumber(), 0, dto.getBalance());
    }
    public void exit() {
        AuthContext.getAuthContext().logOut();
    }
}
