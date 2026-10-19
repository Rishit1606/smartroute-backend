
package com.smartroute.backend.repository;

import com.smartroute.backend.model.Location;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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

    //
    public Location findById(int id) {
        String sql = """
                SELECT id, name, latitude, longitude
                FROM public.locations
                WHERE id = ?
                """;

        List<Location> results = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Location(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")),
                id); // suplies value in "?""

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public int insert(String name, double latitude, double longitude) {
        String sql = """
                INSERT INTO public.locations (name, latitude, longitude)
                VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    sql,
                    new String[] { "id" });

            statement.setString(1, name);
            statement.setDouble(2, latitude);
            statement.setDouble(3, longitude);

            return statement;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if (key == null) {
            throw new IllegalStateException("Database did not return a generated ID");
        }

        return key.intValue();

    }

}
