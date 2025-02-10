package com.kpu.stocktrading.oose.stock_management.service;

import com.kpu.stocktrading.oose.stock_management.model.Role;
import com.kpu.stocktrading.oose.stock_management.model.UserModel;
import com.kpu.stocktrading.oose.stock_management.repository.RoleRepository;
import com.kpu.stocktrading.oose.stock_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

/*
Spring service class.
Handles interactions between database app.
 */

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //Utilizes username provided, to check if user exists, and sends user to authorization system.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var selectedUser = user.get();
            return User.builder()
                    .username(selectedUser.getUsername())
                    .password(selectedUser.getPasswordHash())
                    .build();
        }
        else {
            throw new UsernameNotFoundException(username);
        }

    }

    //Adds given user to database.
    public UserModel createUser(@RequestBody UserModel user) {
        Role privaterole = roleRepository.findByName(
                user.getRole().getName()
        );
        if (privaterole == null) {
            throw new RuntimeException("No roles are created. Please create a role first");
        }
        user.setRole(privaterole);
        UserModel savedUser = userRepository.save(user);

        return savedUser;
    }

    //Gets a user from the database by username.
    public Optional<UserModel> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}






















//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public String authenticateUser(String username, String password) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(password, user.getPasswordHash())) {
//                return user.getRole().getName();  // Return role (e.g., "admin" or "trader")
//            }
//        }
//
//        return null; // Authentication failed
//    }

