package com.example.task.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class userModel {


@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
    
@Column(name="username")
String userName;

@Column(name="password")
String password;

@Column(name="address")
String address;

@Column(name="phone")
String phone;

@Column(name="email")
String email;

@Column(name="state")
String state;


    
}
