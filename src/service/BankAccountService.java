package service;

import auth.AuthContext;
import auth.UserAuth;
import com.sun.security.auth.UserPrincipal;
import db.PostgresDBConnector;
import dto.BankAccountDTO;
import entity.BankAccount;
import repository.BankAccountRepository;

import javax.security.auth.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class BankAccountService {

    private final Connection conn = PostgresDBConnector.getConnection();
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(String fullName, long phoneNumber, int pincode) {
        try {
            Random random = new Random();

            long cartNumber = (random.nextLong(10000000));
            if (repository.findAccountByCardNumber(cartNumber) == null) {
                BankAccount bankAccount = new BankAccount(fullName, phoneNumber, cartNumber, pincode, 0);
                assert conn != null;
                PreparedStatement statement = conn.prepareStatement("INSERT INTO bank.bank_account VALUES (?,?,?,?,?)");
                statement.setString(1, fullName);
                statement.setLong(2, phoneNumber);
                statement.setInt(3, bankAccount.getBalance());
                statement.setInt(4, pincode);
                statement.setLong(5, cartNumber);
                statement.executeUpdate();
                return bankAccount;
            } else {
                createAccount(fullName, phoneNumber, pincode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean auth(Long cartNumber, int pincode) {
        assert conn != null;
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
