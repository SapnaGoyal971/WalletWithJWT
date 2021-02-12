package com.example.demo.Elastic.resource;

import com.example.demo.Elastic.load.Loaders;
import com.example.demo.Elastic.model.Users;
import com.example.demo.Elastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text){
        return usersRepository.findByName(text);
    }
    @GetMapping(value = "/name/{salary}")
    public List<Users> searchSalary(@PathVariable final Long salary){
        return usersRepository.findBySalary(salary);
    }
    @GetMapping(value = "/all")
    public List<Users> searchAll(){
       Iterable<Users> users= usersRepository.findAll();
       List<Users> usersList=new ArrayList<>();
       users.forEach(
               usrs->{
                usersList.add(usrs);
               }
       );
       return usersList;
    }


}
