package com.kpu.stocktrading.oose.stock_management.repository;

import com.kpu.stocktrading.oose.stock_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
This repository is an access point to the database.
It's used to handle queries for the Role Entity.
*/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
