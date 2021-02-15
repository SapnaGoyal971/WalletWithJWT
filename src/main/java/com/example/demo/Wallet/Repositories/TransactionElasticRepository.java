package com.example.demo.Wallet.Repositories;

import com.example.demo.Wallet.Classes.TransactionElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TransactionElasticRepository extends ElasticsearchRepository<TransactionElastic,Long> {
}
