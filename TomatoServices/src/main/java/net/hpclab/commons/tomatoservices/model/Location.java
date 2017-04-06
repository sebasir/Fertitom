package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;

public class Location implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private double altitude;
    private String initDate;
    private String finalDate;
    private String department;
    private String municipality;
    private int type;
    private double trgh;
    private String fileweather;
    private String filesoil;

    public Location() {

    }

    public Location(int id, String name, double latitude, double longitude, double altitude, String initDate, String finalDate, String department, String municipality, int type, double trgh, String fileweather, String filesoil) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.department = department;
        this.municipality = municipality;
        this.type = type;
        this.trgh = trgh;
        this.fileweather = fileweather;
        this.filesoil = filesoil;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getTrgh() {
        return trgh;
    }

    public void setTrgh(double trgh) {
        this.trgh = trgh;
    }

    public String getFileweather() {
        return fileweather;
    }

    public void setFileweather(String fileweather) {
        this.fileweather = fileweather;
    }

    public String getFilesoil() {
        return filesoil;
    }

    public void setFilesoil(String filesoil) {
        this.filesoil = filesoil;
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

    @Override
    public String toString() {
        return "ID: " + this.id;
    }
}
