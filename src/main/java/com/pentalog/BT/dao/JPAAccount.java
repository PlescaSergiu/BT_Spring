package com.pentalog.BT.dao;


import com.pentalog.BT.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JPAAccount extends JpaRepository<Account,Long> {
    List<Account> findByUser(long userId);
    List<Account> findByUserId(long userId);
    List<Account> findAllByUserId(long userId);
    Account findByAccountNumber(String accountNumber);
}
