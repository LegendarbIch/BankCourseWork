package repository;

import db.PostgresDBConnector;
import dto.BankAccountDTO;
import entity.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRepository {
    private final Connection conn = PostgresDBConnector.getConnection();

    public BankAccountDTO findAccountByCardNumber(int cardNumber) {
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bank.bank_account WHERE bank.bank_account.cart_number=" + cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            BankAccountDTO accountDTO = getDto(cardNumber, resultSet);
            if (accountDTO != null) return accountDTO;
        } catch (SQLException e) {
             e.getCause();
        }
        return null;
    }

    public BankAccountDTO findAccountByPinCodeAndCardNumber(int cardNumber, int pincode) {
        try {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bank.bank_account WHERE" +
                    " bank.bank_account.cart_number=" + cardNumber + " AND bank.bank_account.pin_code=" + pincode);
            ResultSet resultSet = preparedStatement.executeQuery();
            BankAccountDTO accountDTO = getDto(cardNumber, resultSet);
            if (accountDTO != null) return accountDTO;
        } catch (SQLException e) {
            e.getCause();
        }
        return null;
    }

    private static BankAccountDTO getDto(int cardNumber, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int phoneNumber = resultSet.getInt("phone_number");
            int balance = resultSet.getInt("balance");
            return new BankAccountDTO(name, phoneNumber, cardNumber, balance);
        }
        return null;
    }
}
