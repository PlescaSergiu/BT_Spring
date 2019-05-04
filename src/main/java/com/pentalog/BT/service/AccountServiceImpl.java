package com.pentalog.BT.service;

import com.pentalog.BT.dao.JPAAccount;
import com.pentalog.BT.dao.JPAUser;
import com.pentalog.BT.entity.Account;
import com.pentalog.BT.entity.Context;
import com.pentalog.BT.entity.TypeAccount;
import com.pentalog.BT.entity.User;
import com.pentalog.BT.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);
    private ValidateService validateService = new ValidateService();


    @Autowired
    JPAUser jpaUser;

    @Autowired
    JPAAccount jpaAccount;

    public void addAccount(Account account) {
        Account acc = account;
        jpaAccount.save(acc);
        LOGGER.debug("account saved" + account);
        System.out.println("Account saved");
    }

    public List<Account> findAccountsByUserName(String userName) {
        User user = jpaUser.findByUserName(userName);
        List<Account> accountList = jpaAccount.findByUser(user.getId());
        return accountList;
    }

    @Override
    public List<Account> listAccounts(Long userId) {
        jpaAccount.findAllByUserId(userId).forEach(
                account -> System.out.println(account.getAccountType()+ " "
                        + account.getAccountNumber() + " " + account.getBalance()));
        return jpaAccount.findByUserId(userId);
    }

    public List<Account> getDestinationAccount(Account account) {
        List<Account> compatibleAccount = new ArrayList<>();
        for (Account a : jpaAccount.findAllByUserId(Context.getUser().getId())) {
            if (a.getAccountType().equals(account.getAccountType())
                    && !a.getAccountNumber().equals(account.getAccountNumber())) {
                compatibleAccount.add(a);
            }

        }
        return compatibleAccount;

    }

    public Account readAccount(Scanner scanner) {
        String currency = readCurrency(scanner);
        String accountNumber = readAccountNumber(scanner, currency);
        BigDecimal balance = readAmount(scanner);
        Account account = new Account();
        account.setUser(Context.getUser());
        account.setAccountType(TypeAccount.valueOf(currency));
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);

        return account;

    }



    private String readCurrency(Scanner scanner) {
        String currency;
        while (true) {
            System.out.println("Enter type currency EUR/RON");
            currency = scanner.nextLine();
            try {
                validateService.validateCurrency(currency);
                break;
            } catch (ValidationException ve) {
                System.out.println(ve.getMessage());
            }
        }
        return currency;

    }

    private BigDecimal readAmount(Scanner scanner) {
        String amount;
        while (true) {
            System.out.println("Enter amount");
            amount = scanner.nextLine();
            try {
                validateService.validateAmount(amount);
                break;
            } catch (ValidationException ve) {
                System.out.println(ve.getMessage());
            }
        }
        return new BigDecimal(amount);
    }

    private String readAccountNumber(Scanner scanner, String accountType) {
        String accountNumber;
        while (true) {
            System.out.println("Enter account number");
            accountNumber = scanner.nextLine();
            try {
                validateService.validateAccountNumber(accountNumber, accountType);
                break;
            } catch (ValidationException ve) {
                System.out.println(ve.getMessage());
            }

        }
        return accountNumber;

    }

}
