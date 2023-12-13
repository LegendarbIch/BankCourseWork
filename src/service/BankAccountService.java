package service;

import auth.AuthContext;
import auth.UserAuth;
import com.sun.security.auth.UserPrincipal;
import db.PostgresDBConnector;
import entity.BankAccount;
import repository.BankAccountRepository;

import javax.security.auth.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountService {

    private final Connection conn = PostgresDBConnector.getConnection();
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(String fullName, int phoneNumber, int pincode) {
        try {
            int cartNumber = (int) (Math.random() + 100000000);
            if (repository.findAccountByCardNumber(cartNumber) == null) {
                BankAccount bankAccount = new BankAccount(fullName, phoneNumber, cartNumber, pincode);
                assert conn != null;
                PreparedStatement statement = conn.prepareStatement("INSERT INTO bank.bank_account VALUES (?,?,?,?,?)");
                statement.setString(1, fullName);
                statement.setInt(2, phoneNumber);
                statement.setInt(3, bankAccount.getBalance());
                statement.setInt(4, pincode);
                statement.setInt(5, cartNumber);
                statement.executeUpdate();
            } else {
                createAccount(fullName, phoneNumber, pincode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean auth(int cartNumber, int pincode) {
        assert conn != null;
        if (repository.findAccountByPinCodeAndCardNumber(cartNumber, pincode) != null) {
            AuthContext.getAuthContext().auth();
            return true;
        }
        return false;
    }

    public void exit() {
        AuthContext.getAuthContext().logOut();
    }
}
