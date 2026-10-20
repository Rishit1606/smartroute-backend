
package com.smartroute.backend;

import com.smartroute.backend.model.Location;
import com.smartroute.backend.repository.LocationRepository;
import com.smartroute.backend.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void getLocationByIdReturnsLocation() {
        Location sample = new Location(
                1, "Test Station", 33.42, -111.94);

        when(locationRepository.findById(1)).thenReturn(sample);

        Location result = locationService.getLocationById(1);

        assertEquals("Test Station", result.getName());
    }

    @Test
    void getAllLocationsReturnsLocations() {
        List<Location> sampleLocations = List.of(
                new Location(1, "Station A", 33.42, -111.94),
                new Location(2, "Station B", 33.43, -111.95));

        when(locationRepository.findAll()).thenReturn(sampleLocations);

        List<Location> result = locationService.getAllLocations();

        assertEquals(2, result.size());
        assertEquals("Station A", result.get(0).getName());
    }

    @Test
    void getLocationByIdReturnsNullWhenMissing() {
        when(locationRepository.findById(99)).thenReturn(null);

        Location result = locationService.getLocationById(99);

        assertNull(result);
    }

    @Test
    void deleteLocationReturnsTrueWhenLocationExists() {
        when(locationRepository.deleteById(5)).thenReturn(1);

        boolean result = locationService.deleteLocation(5);

        assertTrue(result);
        verify(locationRepository).deleteById(5);
    }
}
