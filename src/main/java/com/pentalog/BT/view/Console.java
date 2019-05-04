package com.pentalog.BT.view;




import com.pentalog.BT.entity.Account;
import com.pentalog.BT.entity.Context;
import com.pentalog.BT.entity.Transaction;
import com.pentalog.BT.exception.ValidationException;
import com.pentalog.BT.service.AccountServiceImpl;
import com.pentalog.BT.service.Operations;
import com.pentalog.BT.service.TransactionServiceImpl;
import com.pentalog.BT.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Console {

    @Autowired
    private  UserServiceImpl userService ;
    @Autowired
    private TransactionServiceImpl transactionService;
    @Autowired
    private AccountServiceImpl accountService ;
    @Autowired
    private Operations operations ;
    private static Scanner scanner = new Scanner(System.in);



    public void loginScreen() throws ValidationException {
        System.out.println("Welcome to BT:\n");

        while (true) {
            System.out.println("1.Login");
            System.out.println("2.Exit");

            String line = scanner.nextLine();
            if (line.equals("1")) {
                userService.login(userService.readUserNamePassword(scanner));
                mainScreen();

            } else if (line.equals("2")) {
                System.out.println("Good bye");
                return;
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    public void mainScreen() throws ValidationException {
        while (true) {
            System.out.println("1.Account");
            System.out.println("2.Payments");
            System.out.println("3.Logout");
            String line = scanner.nextLine();
            if (line.equals("1")) {
                operationsScreen();
            } else if (line.equals("2")) {
                try {
                    Transaction transfer = transactionService.readTransaction(scanner);

                    transactionService.transfer(transfer);

                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.equals("3")) {
                operations.logout();
                return;
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    public void operationsScreen() throws ValidationException {
        while (true) {
            System.out.println("1.Create account");
            System.out.println("2.List account");
            System.out.println("3.Back");

            String line = scanner.nextLine();
            if (line.equals("1")) {
                Account account = accountService.readAccount(scanner);
                accountService.addAccount(account);
            } else if (line.equals("2")) {
                accountService.listAccounts(Context.getUser().getId());
            } else if (line.equals("3")) {
                return;
            } else {

                System.out.println("Invalid option");
            }
        }
    }
}




