package com.pentalog.BT.service;

import com.pentalog.BT.entity.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Operations {
    private static final Logger LOGGER = LogManager.getLogger(Operations.class);


    public void logout() {
        Context.setUser(null);
        System.out.println("Logout successful");
    }


}
