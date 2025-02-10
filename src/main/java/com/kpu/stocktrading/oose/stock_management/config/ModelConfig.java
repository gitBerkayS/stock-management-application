package com.kpu.stocktrading.oose.stock_management.config;

import com.kpu.stocktrading.oose.stock_management.model.Role;
import com.kpu.stocktrading.oose.stock_management.model.UserModel;
import com.kpu.stocktrading.oose.stock_management.repository.RoleRepository;
import com.kpu.stocktrading.oose.stock_management.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
Initializing UserModel and RoleModels to exist on startup.
*/
@Slf4j
@Component
public class ModelConfig implements CommandLineRunner {

    //instances of classes used for access points.
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    BCryptPasswordEncoder encoder;

    //constructor
    public ModelConfig(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        long rolesRepoTables= roleRepository.count();

        //If the roles database is empty (No tables exist)
        if (rolesRepoTables == 0) {

            // Create an "admin role: (permission 0)" & "trader role: (permission 1)"
            Role adminRole = new Role("admin", 0);
            Role traderRole = new Role("trader", 1);

            // Create hardcoded admin login
            UserModel user = new UserModel("admin", adminRole, "admin", encoder, "admin", "admin", "admin", "admin", "admin");

            //save to the database
            roleRepository.save(adminRole);
            roleRepository.save(traderRole);
            userRepository.save(user);

            //Log result success
            log.info("Roles initialized: admin and trader created.");
            log.info("Users initialized: admin");
        }

        //Debugging
        if (rolesRepoTables > 0){
            log.warn("Number of roles initialized: {}", rolesRepoTables);
        }
    }
}