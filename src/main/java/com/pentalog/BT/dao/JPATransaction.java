package com.pentalog.BT.dao;

import com.pentalog.BT.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPATransaction extends JpaRepository<Transaction,Long> {
}
