package com.pentalog.BT.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number",length = 50)
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "account_type")
    private TypeAccount accountType;

    @Column(name = "created_account_time")
    private Timestamp createTime;

    @Column(name = "updated_account_time")
    private Timestamp updateTime;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    public Account(String accountNumber, BigDecimal balance, TypeAccount accountType, Timestamp createTime, Timestamp updateTime, User user) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public TypeAccount getAccountType() {
        return accountType;
    }

    public void setAccountType(TypeAccount accountType) {
        this.accountType = accountType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

