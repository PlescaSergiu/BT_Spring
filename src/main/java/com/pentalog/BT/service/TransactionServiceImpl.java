package com.pentalog.BT.service;

import com.pentalog.BT.dao.JPAAccount;
import com.pentalog.BT.entity.Account;
import com.pentalog.BT.entity.Context;
import com.pentalog.BT.entity.Transaction;
import com.pentalog.BT.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);

    @Autowired
   private JPAAccount jpaAccount;

    @Autowired
    private AccountServiceImpl accountService;


    public void transfer(Transaction transaction) throws ValidationException {
        Account source = jpaAccount.findByAccountNumber(transaction.getFromAccount());
        Account destination = jpaAccount.findByAccountNumber(transaction.getToAccount());
        BigDecimal amounts = transaction.getBalance();
        LOGGER.warn("Try to tranfer" + source.getAccountNumber());
        if (source.getAccountNumber().equals(destination.getAccountNumber())) {
            throw new ValidationException("Same account");
        }

        if (source.getAccountType() != destination.getAccountType()) {
            throw new ValidationException("Invalid type account");

        }
        LOGGER.debug("try to transfer" + source.getBalance());
        if (source.getBalance().compareTo(amounts) < 0) {
            throw new ValidationException("Insufficient founds");
        }
        source.setBalance(source.getBalance().subtract(amounts));
        destination.setBalance(destination.getBalance().add(amounts));
        LOGGER.debug("tranfer" + destination.getAccountNumber());
        jpaAccount.save(source);
        jpaAccount.save(destination);
        System.out.println("Transfer was made successful");
    }



    public Transaction readTransaction(Scanner scanner) throws ValidationException {
        int index = 1;
        if (jpaAccount.findAllByUserId(Context.getUser().getId()).isEmpty()) {
            LOGGER.warn("No accounts");
            throw new ValidationException("No accounts");
        } else {
            System.out.println("Select source account");
            for (Account a : jpaAccount.findAllByUserId(Context.getUser().getId())) {
                System.out.println(index + "." + a.getAccountNumber() + " " + a.getAccountType() + " " + a.getBalance());
                index++;
            }

        }

        int sourceNumber = Integer.valueOf(scanner.nextLine());
        Account source = jpaAccount.findAllByUserId(Context.getUser().getId()).get(sourceNumber - 1);


        List<Account> destinationAccount = accountService.getDestinationAccount(source);
        System.out.println("Select destination account");

        index = 1;
        if (destinationAccount.size() == 0) {
            LOGGER.warn("Dont have account to transfer");
            throw new ValidationException("No account");
        } else {
            for (Account a : destinationAccount) {
                System.out.println(index + "." + a.getAccountNumber() + " " + a.getAccountType() + " " + a.getBalance());
                index++;
            }

        }
        int destinationNumber = Integer.valueOf(scanner.nextLine());

        System.out.println("Insert amount");
        String amount = scanner.nextLine();
        LOGGER.debug("transfer" + amount);
        return new Transaction(source.getAccountNumber(), destinationAccount.get(destinationNumber - 1).getAccountNumber(),
                new BigDecimal(amount));


    }
}
