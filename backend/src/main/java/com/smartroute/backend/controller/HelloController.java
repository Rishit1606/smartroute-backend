package com.smartroute.backend.controller;

import com.smartroute.backend.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This java class will handle http requests Spring, please treat this class as
                // a web/API controller.
public class HelloController {

    private final RouteService routeService; // means the controller needs a Routeservice

    // spring provides Routeservice
    public HelloController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/") // WHen a GET request is sent
    public String hello() {
        return "SmartRoute Backend is running!";
    }

    @GetMapping("/api/routes")
    public String getRoutes() {
        return routeService.getRouteMessage();
    }
}