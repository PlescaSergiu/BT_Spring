package com.pentalog.BT.service;

import com.pentalog.BT.dao.JPAUser;
import com.pentalog.BT.entity.User;
import com.pentalog.BT.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidateService {

    @Autowired
    private JPAUser jpaUser;


    public void validateUserNamePassword(String userName, String password) throws ValidationException {
        User user = jpaUser.findByUserName(userName);
        if (user == null || !user.getPassword().equals(password)) {
            throw new ValidationException("Invalid user/password");
        }

    }

    public void validateCurrency(String currency) throws ValidationException {
        if (!currency.equals("EUR") && !currency.equals("RON")) {
            throw new ValidationException("Invalid Option");
        }
    }

    public void validateAmount(String amount) throws ValidationException {
        try {
            BigDecimal value = new BigDecimal(amount);
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                throw new ValidationException("Amount cannot be negative");
            }
        } catch (NumberFormatException nfe) {
            throw new ValidationException("Invalid amount");
        }
    }

    public void validateAccountNumber(String accountNumber, String accountType) throws ValidationException {
        if (accountNumber.length() < 24) {
            throw new ValidationException("Invalid account number");
        }
        if (!accountNumber.startsWith(accountType)) {
            throw new ValidationException("Invalid Option");
        }

    }


}
