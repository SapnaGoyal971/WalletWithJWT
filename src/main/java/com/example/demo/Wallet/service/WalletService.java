package com.example.demo.Wallet.service;

import com.example.demo.Wallet.Classes.WalletClasses.RequestClass;
import com.example.demo.Wallet.Classes.WalletClasses.Transaction;
import com.example.demo.Wallet.Classes.WalletClasses.TransactionElastic;
import com.example.demo.Wallet.Classes.WalletClasses.Wallet;
import com.example.demo.Wallet.Repositories.WalletRepositories.TransactionElasticRepository;
import com.example.demo.Wallet.Repositories.WalletRepositories.TransactionRepository;
import com.example.demo.Wallet.Repositories.WalletRepositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionElasticRepository transactionElasticRepository;


    public Wallet createWallet(Wallet wallet){

   try {
            walletRepository.save(wallet);
            return wallet;
        }
        catch (DataIntegrityViolationException d){
            return null;
          }
    }

    public String transferMoney(Long payeePhoneNumber, Long payerPhoneNumber, Long amount){

        if(amount<0)
            return "amount cannot be negative";
        try {

            Wallet walletOfPayer = walletRepository.findByPhoneNumber(payerPhoneNumber);
            Wallet walletOfPayee = walletRepository.findByPhoneNumber(payeePhoneNumber);
            Transaction transaction = new Transaction(payeePhoneNumber, payerPhoneNumber, amount);

            if (walletOfPayer.getBalance() >= amount) {

                walletOfPayer.setBalance(walletOfPayer.getBalance() - amount);
                walletOfPayee.setBalance(walletOfPayee.getBalance() + amount);

                walletRepository.save(walletOfPayer);
                walletRepository.save(walletOfPayee);

                transaction.setStatus(Transaction.Status.SUCCESS);
                transactionRepository.save(transaction);

                return "Transfer Successful with transaction Id: "+transaction.getTxnID() ;
            } else {
                transaction.setStatus(Transaction.Status.FAILURE);
                transactionRepository.save(transaction);
                return "Not Sufficient Balance in Payer's Wallet.";
            }

        }
        catch (NullPointerException n){
            return "wrong payer phone number or payee phone number.";
        }
    }

    public Transaction.Status getTransactionStatus(Long transactionId){
        try {
            Transaction transaction = transactionRepository.findByTxnID(transactionId);
            return transaction.getStatus();
        }
        catch (NullPointerException n){
            return null;
        }
    }

    public Page<Transaction> getTransactionSummary(Long userId,int pageNumber){
        try {
            Wallet wallet = walletRepository.findByUserId(userId);
            return transactionRepository.findByPayeePhoneNumberOrPayerPhoneNumberOrderByTimestamp(wallet.getPhoneNumber(), wallet.getPhoneNumber(),PageRequest.of(pageNumber,1));
        }
        catch (NullPointerException n){
            return null;
        }
    }


    public String transferMoneyThroughElastic(Long payeePhoneNumber, Long payerPhoneNumber, Long amount) {
        if (amount < 0)
            return "amount cannot be negative";
        try {
            Wallet walletOfPayer = walletRepository.findByPhoneNumber(payerPhoneNumber);
            Wallet walletOfPayee = walletRepository.findByPhoneNumber(payeePhoneNumber);
            TransactionElastic transactionElastic = new TransactionElastic(payeePhoneNumber, payerPhoneNumber, amount);

            if (walletOfPayer.getBalance() >= amount) {

                walletOfPayer.setBalance(walletOfPayer.getBalance() - amount);
                walletOfPayee.setBalance(walletOfPayee.getBalance() + amount);

                walletRepository.save(walletOfPayer);
                walletRepository.save(walletOfPayee);

                transactionElastic.setStatus(TransactionElastic.Status.SUCCESS);
                transactionElasticRepository.save(transactionElastic);

                return "Transfer Successful with transaction Id: " + transactionElastic.getTxnID();
            } else {
                transactionElastic.setStatus(TransactionElastic.Status.FAILURE);
                transactionElasticRepository.save(transactionElastic);
                return "Not Sufficient Balance in Payer's Wallet.";
            }
        }
        catch (NullPointerException n) {
            return "wrong payer phone number or payee phone number.";
        }
    }

    }
