package com.pentalog.BT.service;


import com.pentalog.BT.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {


    List<Account> listAccounts(Long userId);
}

