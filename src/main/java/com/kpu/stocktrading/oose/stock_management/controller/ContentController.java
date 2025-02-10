package com.kpu.stocktrading.oose.stock_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
A Controller for Content Display
*/

@Controller
public class ContentController {


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }


    @GetMapping("/admin_dashboard")
    public String adminDashboard(){
        return "admin_dashboard";
    }
    @GetMapping("/trader_dashboard")
    public String traderDashboard(){
        return "trader_dashboard";
    }
}