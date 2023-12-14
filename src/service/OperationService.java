package service;

import auth.AuthContext;
import db.PostgresDBConnector;
import entity.BankAccount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;

public class OperationService {
    private final Bank bank;
    private final Connection conn = PostgresDBConnector.getConnection();

    public OperationService(Bank bank) {
        this.bank = bank;
    }

    public void remittance(BankAccount toFrom, int amount) {
        try {
            assert conn != null;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement statementWho =
                    conn.prepareStatement("UPDATE bank.bank_account SET balance = " + bank.operation(AuthContext.getBankAccount(), -amount).getBalance()
                                            + " WHERE cart_number = " + AuthContext.getBankAccount().getCartNumber());
            PreparedStatement statementToFrom =
                    conn.prepareStatement("UPDATE bank.bank_account SET balance = " + bank.operation(toFrom, amount).getBalance()
                                            + " WHERE cart_number = " + toFrom.getCartNumber());
            PreparedStatement statementOperation =
                    conn.prepareStatement("INSERT INTO bank.account_operation VALUES "
                            + "(" + AuthContext.getBankAccount().getCartNumber() + "," + toFrom.getCartNumber() + "," + amount + "," + Date.valueOf(LocalDate.now()) + ")");
            statementWho.executeUpdate();
            statementToFrom.executeUpdate();
            statementOperation.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdraw(int amount) {
        try {
            assert conn != null;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement statement = conn.prepareStatement("UPDATE bank.bank_account SET balance-" + amount + " WHERE cart_number=" + AuthContext.getBankAccount().getCartNumber());
            PreparedStatement statementOperation =
                    conn.prepareStatement("INSERT INTO bank.account_operation VALUES "
                            + AuthContext.getBankAccount().getCartNumber() + "," + AuthContext.getBankAccount().getCartNumber() + "," + amount + "," + Date.valueOf(LocalDate.now()));
            statement.executeUpdate();
            statementOperation.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void replenishment(int amount) {
        try {
            assert conn != null;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement statement = conn.prepareStatement("UPDATE bank.bank_account SET balance=balance+" + amount + " WHERE cart_number=" + AuthContext.getBankAccount().getCartNumber());
            PreparedStatement statementOperation =
                    conn.prepareStatement("INSERT INTO bank.account_operation VALUES "
                            + "(" +AuthContext.getBankAccount().getCartNumber() + "," + AuthContext.getBankAccount().getCartNumber() + "," + amount + "," + Date.valueOf(LocalDate.now()) + ")");
            statement.executeUpdate();
            statementOperation.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
