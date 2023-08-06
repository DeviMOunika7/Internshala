package com.example.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.task.Model.userModel;
import com.example.task.Repository.userRepository;

@Service
public class userService {

 @Autowired
 BCryptPasswordEncoder bCrypt;

@Autowired
userRepository repository;

//returns all the users from the repository
public List<userModel> getUsers(){
    return repository.findAll();
}

//checking whether the user is authenticated or not
public String authenticateUser(String email, String ippassword) {
    
    //we use optional class because it can even hold null objects
    //getting the user from the input and searching for a user with its username
    Optional<userModel> optionalUser  = repository.findByEmail(email);

    //if the user with such username exists 
    if(optionalUser.isPresent()){

        //create another user object for checking with the database
        userModel dbUser = optionalUser.get();

        //the password in the database is encrypted
        //the password given by the user is not encrypted
        //we user bCrypt.matches() to check the encrypted password matches with the user given password

        if(bCrypt.matches(ippassword,dbUser.getPassword())){
            //if matched, login
            return "Welcome "+ dbUser.getEmail();
        }

        //else state that the password is incorrect
        else{
             return "Incorrect Password";
        }
 }
    //if user with such user name is not found
    //throw an exception that the user is not found
    return "User not found";
    
} 


public String addUser(String email, String password, String confPassword){
    Optional<userModel> optionalUser  = repository.findByEmail(email);
    if(optionalUser.isPresent()){
        return "User already exists";
    }
    if(password.equals(confPassword)){
    userModel user= new userModel();
    user.setEmail(email);
    user.setPassword(confPassword);
    String ePass = bCrypt.encode(confPassword);
    user.setPassword(ePass);
    repository.save(user);
    return user.getEmail()+" is added succesfully. Please go back and update profile";
    }
    return "Passwords don't match";
    

}


public String updateUser(userModel user) {

    Optional<userModel> op = repository.findById(user.getId());
    if(op.isPresent()){
        userModel updatedUser = op.get();
        updatedUser.setUserName(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setState(user.getState());
        repository.save(updatedUser);
        return updatedUser.getUserName()+" is updated succesfully";
    }


    return "User not present";
}

public String deleteUser(String userName) {

    Optional<userModel> op = repository.findByUserName(userName);
    if(op.isPresent()){
        userModel deletedUser = op.get();
        repository.delete(deletedUser);
        return userName+" is deleted succesfully";
    }

    return "User does not exist";
}
    
}
