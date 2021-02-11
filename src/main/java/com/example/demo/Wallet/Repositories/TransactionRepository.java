package com.example.demo.Wallet.Repositories;

import com.example.demo.Wallet.Classes.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    public Transaction findByTxnID(Long id);
    public Page<Transaction> findByPayeePhoneNumberOrPayerPhoneNumberOrderByTimestamp(Long phn, Long phn2, Pageable pageable);

}
