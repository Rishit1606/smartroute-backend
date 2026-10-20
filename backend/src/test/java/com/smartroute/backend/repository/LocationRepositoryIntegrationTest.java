
package com.smartroute.backend.repository;

import com.smartroute.backend.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationRepositoryIntegrationTest {

        @Autowired
        private LocationRepository locationRepository;

        @Test
        void findAllReturnsInsertedLocation() {
                String name = "Integration Test Station";

                int id = locationRepository.insert(name, 33.42, -111.94);

                List<Location> locations = locationRepository.findAll();

                assertTrue(locations.stream()
                                .anyMatch(location -> location.getId() == id
                                                && location.getName().equals(name)));
        }

        @Test
        void findByIdReturnsCorrectLocation() {
                int id = locationRepository.insert(
                                "Find Test Station", 33.43, -111.95);

                Location result = locationRepository.findById(id);

                assertNotNull(result);
                assertEquals("Find Test Station", result.getName());
                assertEquals(33.43, result.getLatitude(), 0.000001);
                assertEquals(-111.95, result.getLongitude(), 0.000001);
        }

        @Test
        void insertCreatesLocationWithGeneratedId() {
                int id = locationRepository.insert(
                                "Insert Test Station", 33.44, -111.96);

                assertTrue(id > 0);

                Location result = locationRepository.findById(id);

                assertNotNull(result);
                assertEquals("Insert Test Station", result.getName());
        }

        @Test
        void updateChangesExistingLocation() {
                int id = locationRepository.insert(
                                "Before Update", 33.42, -111.94);

                int rowsUpdated = locationRepository.update(
                                id, "After Update", 33.45, -111.97);

                Location result = locationRepository.findById(id);

                assertEquals(1, rowsUpdated);
                assertNotNull(result);
                assertEquals("After Update", result.getName());
                assertEquals(33.45, result.getLatitude(), 0.000001);
                assertEquals(-111.97, result.getLongitude(), 0.000001);
        }

        @Test
        void deleteRemovesInsertedLocation() {
                int id = locationRepository.insert(
                                "Delete Test Station", 33.42, -111.94);

                int rowsDeleted = locationRepository.deleteById(id);

                assertEquals(1, rowsDeleted);
                assertNull(locationRepository.findById(id));
        }
}
