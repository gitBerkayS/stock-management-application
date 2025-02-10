package com.kpu.stocktrading.oose.stock_management.repository;

import com.kpu.stocktrading.oose.stock_management.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
This repository is an access point to the database.
It's used to handle queries for the User Entity.
*/
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
