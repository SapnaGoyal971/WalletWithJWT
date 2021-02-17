package com.example.demo.Wallet.Repositories.UserRepositories;

import com.example.demo.Wallet.Classes.UserClasses.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
        public List<User> findByMobileNumber(Long phn);
}
