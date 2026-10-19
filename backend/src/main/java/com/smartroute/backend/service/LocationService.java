package com.smartroute.backend.service;

import com.smartroute.backend.model.Location;
import com.smartroute.backend.repository.LocationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(int id) {
        return locationRepository.findById(id);
    }

    public int createLocation(String name, double latitude, double longitude) {
        return locationRepository.insert(name, latitude, longitude);
    }

    public boolean updateLocation(
            int id, String name, double latitude, double longitude) {

        int rowsUpdated = locationRepository.update(
                id, name, latitude, longitude);

        return rowsUpdated > 0;
    }

    public boolean deleteLocation(int id) {
        int rowsDeleted = locationRepository.deleteById(id);
        return rowsDeleted > 0;
    }

}
