package com.example.task.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.Model.userModel;

@Repository
public interface userRepository extends JpaRepository<userModel, Integer>{
    Optional<userModel> findByUserName(String userName);

    Optional<userModel> findByEmail(String email);

    
    
}
