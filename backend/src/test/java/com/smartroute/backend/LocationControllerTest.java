
package com.smartroute.backend;

import com.smartroute.backend.controller.HelloController;
import com.smartroute.backend.model.Location;
import com.smartroute.backend.service.DatabaseService;
import com.smartroute.backend.service.LocationService;
import com.smartroute.backend.service.RouteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(HelloController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LocationService locationService;

    @MockitoBean
    private RouteService routeService;

    @MockitoBean
    private DatabaseService databaseService;

    @Test
    void getLocationsReturnsOk() throws Exception {
        when(locationService.getAllLocations()).thenReturn(
                java.util.List.of(
                        new Location(1, "Station A", 33.42, -111.94)));

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"name\":\"Station A\",\"latitude\":33.42,\"longitude\":-111.94}]"));
    }

    @Test
    void getLocationByIdReturnsLocation() throws Exception {
        when(locationService.getLocationById(1)).thenReturn(
                new Location(1, "Station A", 33.42, -111.94));

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\":1,\"name\":\"Station A\",\"latitude\":33.42,\"longitude\":-111.94}"));
    }

    @Test
    void getMissingLocationReturnsNotFound() throws Exception {
        when(locationService.getLocationById(999)).thenReturn(null);

        mockMvc.perform(get("/api/locations/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createLocationReturnsCreated() throws Exception {
        when(locationService.createLocation("Station B", 33.43, -111.95))
                .thenReturn(2);

        mockMvc.perform(post("/api/locations")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Station B",
                            "latitude": 33.43,
                            "longitude": -111.95
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("2"));
    }

    @Test
    void updateLocationReturnsOk() throws Exception {
        when(locationService.updateLocation(1, "Station Updated", 33.44, -111.96))
                .thenReturn(true);

        mockMvc.perform(put("/api/locations/1")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Station Updated",
                            "latitude": 33.44,
                            "longitude": -111.96
                        }
                        """))
                .andExpect(status().isOk());
    }

}
