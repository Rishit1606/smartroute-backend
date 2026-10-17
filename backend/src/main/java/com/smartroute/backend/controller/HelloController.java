package com.smartroute.backend.controller;

import com.smartroute.backend.service.DatabaseService;
import com.smartroute.backend.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartroute.backend.model.Location;
import com.smartroute.backend.service.LocationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@RestController // This java class will handle http requests Spring, please treat this class as
                // a web/API controller.
public class HelloController {

    private final RouteService routeService; // means the controller needs a Routeservice
    private final DatabaseService databaseService;
    private final LocationService locationService;

    // spring provides Routeservice
    public HelloController(RouteService routeService, DatabaseService databaseService,
            LocationService locationService) {
        this.routeService = routeService;
        this.databaseService = databaseService;
        this.locationService = locationService;
    }

    @GetMapping("/") // WHen a GET request is sent
    public String hello() {
        return "SmartRoute Backend is running!";
    }

    @GetMapping("/api/routes")
    public String getRoutes() {
        return routeService.getRouteMessage();
    }

    @GetMapping("/api/database")
    public String getDatabaseName() {
        return databaseService.getDatabaseName();
    }

    @GetMapping("/api/locations")
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/api/locations/{id}")
    public Location getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);

        if (location == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Location not found");
        }

        return location;
    }
}