package com.example.demo.Elastic.repository;

import com.example.demo.Elastic.model.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UsersRepository extends ElasticsearchRepository<Users,Long> {
     List<Users> findByName(String text);
     List<Users> findBySalary(Long salary);
}
