package com.kpu.stocktrading.oose.stock_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*
This class represents the role of the user for permissions.
 */
@Getter
@Entity
@Setter
@Table(name = "roles")
public class Role {

    /*
    Getters and setters
    */
    //Primary Key + AutoIncrement
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //Each role name is unique, and required.
    @Column(nullable = false, unique = true)
    private String name;

    //Default permission is 0, and field is required.
    @Column(nullable = false)
    private int permissions = 0;

    //Default Constructor
    public Role() {
    }

    // Constructor for creating a new role
    public Role(String name, int permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
