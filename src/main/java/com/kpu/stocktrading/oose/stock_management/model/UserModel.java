package com.kpu.stocktrading.oose.stock_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
This class represents all users.
*/
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //unique and required property variable.
    @Column(nullable = false,unique = true)
    private String username;

    //Many users can have one role + ForeignKey named as role_id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    //Name of user
    @Column
    private String name;

    //Email of user
    @Column
    private String email;

    //Security question selected for user
    @Column
    private String securityQuestion;

    //Security answer for user
    @Column
    private String securityAnswer;

    //phone of user
    @Column
    private String phoneNumber;

    //hashed version of users password
    @Column(nullable = false)
    private String passwordHash;

    //default constructor
    public UserModel() {}

    //constructor to create a new user.
    public UserModel(String username, Role role, String password, BCryptPasswordEncoder encoder,  String name, String email, String phoneNumber, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.role = role;
        this.passwordHash = encoder.encode(password);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    //password encryption
    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.passwordHash = encoder.encode(password);
    }
}
