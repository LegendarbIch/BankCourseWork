import service.BankAccountService;
import service.OperationService;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Scanner;

public class StarterBoot {

    private final BankAccountService bankService;
    private final OperationService operationService;

    private int methodCount = 0;

    public StarterBoot(BankAccountService bankService, OperationService operationService) {
        this.bankService = bankService;
        this.operationService = operationService;

    }


    public void drawMenu() throws IOException {
        System.out.println("----------------------------БАНК--------------------------");
        System.out.println("--------------------1.Войти в систему---------------------");
        System.out.println("---------------2.Зарегистрировать новый счёт--------------");
        System.out.println("----------------------------------------------------------");
        methodCount++;
        choice();
    }

    private void choice() throws IOException {
        synchronized (this) {
            Scanner scanner = new Scanner(System.in);
            int num = 0;
            try {
                num = scanner.nextInt();
            } catch (Exception e) {
                switch (methodCount) {
                    case (1) -> drawMenu();
                    case (2) -> drawUserAccountMenu();
                }
            }
            switch (num) {
                case (0) -> drawMenu();
                case (1) -> {
                    System.out.println("------Введите номер карты-----");
                    int cardName = scanner.nextInt();
                    System.out.println("------  Теперь пин-код  -----");
                    int pinCode = scanner.nextInt();
                    boolean auth = bankService.auth(cardName, pinCode);
                    if (auth) {
                        drawUserAccountMenu();
                    }
                }
                case (2) -> {
                    System.out.println("------      Введите ФИО    -----");
                    String name = scanner.nextLine();
                    scanner.next();
                    System.out.println("------Введите номер телефона-----");
                    int phone = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("------  Придумайте пин-код -----");
                    int pinCode = scanner.nextInt();
                    bankService.createAccount(name, phone, pinCode);
                    drawMenu();
                }
            }
        }
    }

    private void drawUserAccountMenu() {
    }


}
