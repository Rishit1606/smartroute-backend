
package com.smartroute.backend.repository;

import com.smartroute.backend.model.Location;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // this class handles database access
public class LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    // executes sql commands
    public LocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Selects every row from public.locations
    public List<Location> findAll() {
        String sql = """
                SELECT id, name, latitude, longitude
                FROM public.locations
                ORDER BY id
                """;
        // converts each database row into java location
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Location(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude")));
    }
}
