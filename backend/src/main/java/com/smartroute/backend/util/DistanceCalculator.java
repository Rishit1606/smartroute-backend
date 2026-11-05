package com.smartroute.backend.util;

public class DistanceCalculator {
    private static final double EARTH_RADIUS_KM = 6371.0;

    private DistanceCalculator() {

    }

    public static double haversineKm(double lat1, double lon1, double lat2, double lon2) {
        // Converting degrees to radians and find the differences
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        // Applying the Haversine equation
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Multiplying by Earth's radius
        return EARTH_RADIUS_KM * c;

    }

}
