package db.repository;

import db.PostgresDBConnector;
import dto.BankAccountDTO;
import entity.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRepository {
    private final Connection conn = PostgresDBConnector.getConnection();

    public BankAccountDTO findAccountByCardNumber(Long cardNumber) {
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bank.bank_account WHERE bank.bank_account.cart_number=" + cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            BankAccountDTO accountDTO = getDto(cardNumber, resultSet);
            if (accountDTO != null) return accountDTO;
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
        return null;
    }
    public void saveAccount(BankAccount account) {
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("INSERT INTO bank.bank_account VALUES (?,?,?,?,?)");
            statement.setString(1, account.getName());
            statement.setLong(2, account.getPhoneNumber());
            statement.setInt(3, account.getBalance());
            statement.setInt(4, account.getPincode());
            statement.setLong(5, account.getCartNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public BankAccountDTO findAccountByPinCodeAndCardNumber(Long cardNumber, int pincode) {
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bank.bank_account WHERE" +
                    " bank.bank_account.cart_number=" + cardNumber + " AND bank.bank_account.pin_code=" + pincode);
            ResultSet resultSet = preparedStatement.executeQuery();
            BankAccountDTO accountDTO = getDto(cardNumber, resultSet);
            if (accountDTO != null) return accountDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private BankAccountDTO getDto(Long cardNumber, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            Long phoneNumber = resultSet.getLong("phone_number");
            int balance = resultSet.getInt("balance");
            return new BankAccountDTO(name, phoneNumber, cardNumber, balance);
        }
        return null;
    }
}
