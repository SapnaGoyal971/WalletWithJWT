package com.example.demo.Wallet.Repositories.WalletRepositories;

import com.example.demo.Wallet.Classes.WalletClasses.TransactionElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionElasticRepository extends ElasticsearchRepository<TransactionElastic,Long> {
}
