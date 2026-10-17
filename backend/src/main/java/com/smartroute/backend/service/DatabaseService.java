
package com.smartroute.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getDatabaseName() {
        return jdbcTemplate.queryForObject(
                "SELECT current_database()",
                String.class);
    }
}
