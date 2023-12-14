package auth;

import dto.BankAccountDTO;
import entity.BankAccount;

public class AuthContext {
    private static final UserAuth authContext = new UserAuth();
    private static BankAccount bankAccount;

    public static void setAuthContext(BankAccount bankAccount) {
        AuthContext.bankAccount = bankAccount;
    }

    public static UserAuth getAuthContext() {
        return authContext;
    }

    public static BankAccount getBankAccount() {
        return bankAccount;
    }
}
