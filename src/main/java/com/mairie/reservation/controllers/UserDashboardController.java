package com.mairie.reservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserDashboardController {

    @GetMapping("/userIndex")
    public String showUserDashboard() {
        return "agent/user-dashboard";
    }
}
