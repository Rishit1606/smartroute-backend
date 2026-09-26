package com.smartroute.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This java class will handle http requests
public class HelloController {

    @GetMapping("/") // WHen a GET request is sent
    public String hello() {
        return "SmartRoute Backend is running!";
    }
}