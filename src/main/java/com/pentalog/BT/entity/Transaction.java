package com.pentalog.BT.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "from_account",length = 50)
    private String fromAccount;

    @Column(name = "to_account",length = 50)
    private String toAccount;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "details")
    private String details;

    @Column(name = "created_transactions_time")
    private Timestamp createTime;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account accountId;

    public Transaction() {
    }


    public Transaction(String fromAccount, String toAccount, BigDecimal balance) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.balance = balance;
    }

    public Transaction(String fromAccount, String toAccount, BigDecimal balance, String details, Timestamp createTime, Account accountId) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.balance = balance;
        this.details = details;
        this.createTime = createTime;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
}
