package net.hpclap.commons.tomatoservices.model;

import java.io.Serializable;

public class Location implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public Location() {

    }

    public Location(int id, String name, double latitude, double longitude) {
        super();
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Location ? ((Location) o).getId() == this.id : false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }
}
