package com.smartroute.backend.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DistanceCalculatorTest {

    @Test
    void samePointIsZero() {
        double km = DistanceCalculator.haversineKm(33.4255, -111.94, 33.4255, -111.94);
        assertEquals(0.0, km, 0.001);
    }
}
