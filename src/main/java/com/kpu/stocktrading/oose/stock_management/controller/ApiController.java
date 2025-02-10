package com.kpu.stocktrading.oose.stock_management.controller;

import com.kpu.stocktrading.oose.stock_management.model.UserModel;
import com.kpu.stocktrading.oose.stock_management.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/*
A basic login Controller.
Used to redirect paths visited, and information handling from login & signup
*/

@Controller
public class ApiController {

    private final UserService userService;


    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @GetMapping("/authenticate/login/redirect")
    public RedirectView loginRedirect(HttpSession session) {
        return getRedirect();
    }
    @GetMapping("/")
    public RedirectView home() {
        return getRedirect();
    }

    public RedirectView getRedirect() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        String redirectUrl = "/login";

        Optional<UserModel> userOpt = userService.findByUsername((username));
        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            if (user.getRole().getName().equalsIgnoreCase("admin")) {
                redirectUrl = "/admin_dashboard";
            } else {
                redirectUrl = "/trader_dashboard";
            }
        }
        return new RedirectView(redirectUrl);
    }
}
