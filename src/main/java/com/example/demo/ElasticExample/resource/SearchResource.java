package com.example.demo.ElasticExample.resource;

import com.example.demo.ElasticExample.model.Users;
import com.example.demo.ElasticExample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/saveUser")
    public int saveCust(@RequestBody Users u){
        usersRepository.save(u);
        return 1;
    }
    @GetMapping(value = "/a")
    public String Hello(){
        return "hello";
    }

    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text){
        return usersRepository.findByName(text);
    }
    @GetMapping(value = "/name/{salary}")
    public List<Users> searchSalary(@PathVariable final Long salary){
        return usersRepository.findBySalary(salary);
    }
    @GetMapping(value = "/alll")
    public Iterable<Users> find(){
        return usersRepository.findAll();
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
