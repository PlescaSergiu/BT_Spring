package com.pentalog.BT.service;

import com.pentalog.BT.dao.JPAUser;
import com.pentalog.BT.entity.Context;
import com.pentalog.BT.entity.User;
import com.pentalog.BT.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private JPAUser jpaUser;

    @Autowired
    private ValidateService validateService;

    public void login(String userName) {
        User user = jpaUser.findByUserName(userName);
        Context.setUser(user);
        LOGGER.debug("login with " + userName);
        System.out.println("Welcome " + userName);

    }

    public String readUserNamePassword(Scanner scanner) {
        String userName;
        while (true) {
            System.out.println("Enter username");
            userName = scanner.nextLine();
            System.out.println("Enter password");
            String password = scanner.nextLine();
            try {
                validateService.validateUserNamePassword(userName, password);
                break;
            } catch (ValidationException ve) {
                LOGGER.info(ve);
            }
        }
        return userName;
    }


}
