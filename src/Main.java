import repository.BankAccountRepository;
import service.Bank;
import service.BankAccountService;
import service.OperationService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StarterBoot starterBoot = new StarterBoot(new BankAccountService(new BankAccountRepository()), new OperationService(new Bank()));
        starterBoot.drawMenu();
    }
}