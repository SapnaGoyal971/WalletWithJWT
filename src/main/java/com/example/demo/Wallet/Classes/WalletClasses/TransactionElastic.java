package com.example.demo.Wallet.Classes.WalletClasses;

import org.springframework.data.elasticsearch.annotations.Document;

//import javax.persistence.*;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Document(indexName = "db_example", indexStoreType= "Transaction", shards = 1)
public class TransactionElastic {
    @Id
    private String txnID;

    private Timestamp timestamp;
    private long amount;
    private long payerPhoneNumber;
    private long payeePhoneNumber;
    private Status status;
    public enum Status{
        SUCCESS,FAILURE
    }
    public TransactionElastic(){}
    public TransactionElastic(Long payeePhoneNumber, Long payerPhoneNumber, Long amount) {
        this.payeePhoneNumber=payeePhoneNumber;
        this.payerPhoneNumber=payerPhoneNumber;
        this.amount=amount;
        this.timestamp= new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxnID() {
        return txnID;
    }

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPayerPhoneNumber() {
        return payerPhoneNumber;
    }

    public void setPayerPhoneNumber(long payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public long getPayeePhoneNumber() {
        return payeePhoneNumber;
    }

    public void setPayeePhoneNumber(long payeePhoneNumber) {
        this.payeePhoneNumber = payeePhoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
