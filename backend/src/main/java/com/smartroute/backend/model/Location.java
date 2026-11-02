package com.smartroute.backend.model;

public class Location {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private LocationType locationType;

    public Location(int id, String name, double latitude, double longitude, LocationType locationType) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationType = locationType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocationType getLocationType() {
        return locationType;
    }
}
