package com.example.demo.Wallet.Classes.WalletClasses;

import javax.persistence.*;

@Entity
@Table(name = "Wallet", uniqueConstraints={@UniqueConstraint(columnNames = "phoneNumber")})
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private long phoneNumber;
    private long balance;

    public Wallet(){}
    public Wallet(Long phoneNumber) {
        this.phoneNumber=phoneNumber;
        this.balance=0;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

}
