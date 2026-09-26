package com.smartroute.backend.service;

import org.springframework.stereotype.Service;

@Service // class contains application/business logic, and Spring should manage it.
public class RouteService {

    public String getRouteMessage() {
        return "Route service is working!";
    }
}
