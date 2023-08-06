package com.example.task.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.task.Model.userModel;
import com.example.task.service.userService;

@RestController
@RequestMapping(path="/user")
public class userController {

    @Autowired
    userService service;

    

    @PostMapping(path= "/signUp")
    public String addUser(@RequestParam String email, @RequestParam String password, @RequestParam String confPassword){
        return service.addUser(email, password, confPassword);

    }

    @DeleteMapping(path="/deleteUser")
    public String deleteUser(String userName){
        return service.deleteUser(userName);
    }

    

    @PostMapping(path= "/login")
    public String login(String email, String password){
        return service.authenticateUser(email,password);
    }

    @GetMapping("/getUsers")
    public List<userModel> getUsers(){
        return service.getUsers();

    }

    @PutMapping("/update/{id}")
    public String updateUserName(@PathVariable(value = "id") int id,  userModel userModel){
        return service.updateUser(userModel);
    }
    
}
