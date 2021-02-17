package com.example.demo.Wallet.Repositories.WalletRepositories;

import com.example.demo.Wallet.Classes.WalletClasses.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet,Long> {

    public Wallet findByPhoneNumber(Long phn);

    public Wallet findByUserId(Long id);
}
