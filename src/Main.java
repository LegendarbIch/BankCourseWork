import db.repository.BankAccountRepository;
import db.repository.OperationRepository;
import service.Bank;
import service.BankAccountService;
import service.OperationService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BankStarterBoot starterBoot = new BankStarterBoot(new BankAccountService(new BankAccountRepository()), new OperationService(new OperationRepository(new Bank())));
        starterBoot.drawMenu();
    }
}