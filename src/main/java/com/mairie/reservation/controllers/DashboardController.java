package com.mairie.reservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class DashboardController {
    
    @GetMapping("/index")
    public String showDashboard() {
        return "index/dashboard";
    }

}
