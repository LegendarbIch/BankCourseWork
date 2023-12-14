import auth.AuthContext;
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
    private final Scanner scanner = new Scanner(System.in);
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
            int num = getNum();
            switch (num) {
                case (0) -> drawMenu();
                case (1) -> {
                    System.out.println("------Введите номер карты-----");
                    Long cardName = scanner.nextLong();
                    System.out.println("------  Теперь пин-код  -----");
                    int pinCode = scanner.nextInt();
                    boolean auth = bankService.auth(cardName, pinCode);
                    if (auth) {
                        drawUserAccountMenu();
                    }
                }
                case (2) -> {
                    System.out.println("------      Введите ФИО    -----");
                    String name = scanner.next();
                    System.out.println("------Введите номер телефона-----");
                    long phone = scanner.nextLong();
                    System.out.println("------  Придумайте пин-код -----");
                    int pinCode = scanner.nextInt();
                    bankService.createAccount(name, phone, pinCode);
                    drawMenu();
                }

            }
        }
    }

    private void drawUserAccountMenu() throws IOException {
        System.out.println("-----------------------------БАНК----------------------------------");
        System.out.println("--------------------1.Положить деньги на счёт----------------------");
        System.out.println("---------------------2.Снять деньги со счёта-----------------------");
        System.out.println("-----------------3.Перевести деньги на чужой счёт------------------");
        System.out.println("-------------------------------------------------------------------");
        choice2();
    }

    private void choice2() throws IOException {
        synchronized (this) {
            int num = getNum();
            switch (num) {
                case (0) -> drawMenu();
                case (1) -> {
                    System.out.println("-----------Какую сумму вы положите?-----------");
                    int sum = scanner.nextInt();
                    operationService.replenishment(sum);
                    AuthContext.getBankAccount().toString();
                }
                case (2) -> {

                }
                case (3) -> {

                }

            }
        }
    }

    private int getNum() throws IOException {
        int num = 0;
        try {
            num = scanner.nextInt();
        } catch (Exception e) {
            switch (methodCount) {
                case (1) -> drawMenu();
                case (2) -> drawUserAccountMenu();
            }
        }
        return num;
    }

}
