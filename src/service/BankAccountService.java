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

public class BankAccountService {

    private final Connection conn = PostgresDBConnector.getConnection();
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(String fullName, long phoneNumber, int pincode) {
        try {
            long cartNumber = (long) (Math.random() + 100000000);
            if (repository.findAccountByCardNumber(cartNumber) == null) {
                BankAccount bankAccount = new BankAccount(fullName, phoneNumber, cartNumber, pincode);
                assert conn != null;
                PreparedStatement statement = conn.prepareStatement("INSERT INTO bank.bank_account VALUES (?,?,?,?,?)");
                statement.setString(1, fullName);
                statement.setLong(2, phoneNumber);
                statement.setInt(3, bankAccount.getBalance());
                statement.setInt(4, pincode);
                statement.setLong(5, cartNumber);
                statement.executeUpdate();
            } else {
                createAccount(fullName, phoneNumber, pincode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean auth(Long cartNumber, int pincode) {
        assert conn != null;
        BankAccountDTO account = repository.findAccountByPinCodeAndCardNumber(cartNumber, pincode);
        if (account != null) {
            AuthContext.setAuthContext(new BankAccount(account.getName(), account.getPhoneNumber(), account.getCartNumber(), pincode));
            AuthContext.getAuthContext().auth();
            return true;
        }
        return false;
    }

    public void exit() {
        AuthContext.getAuthContext().logOut();
    }
}
