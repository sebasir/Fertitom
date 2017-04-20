package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;

public class Location implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String department;
    private String municipality;
    private int type;
    private double latitude;
    private double longitude;
    private double altitude;
    private double trgh;
    private String initDate;
    private String finalDate;
    private String fileweather;
    private String filesoil;
    private String fileubic;

    public Location() {

    }

    public Location(int id, String department, String municipality, int type, double latitude, double longitude, double altitude, double trgh, String initDate, String finalDate, String fileweather, String filesoil, String fileubic) {
        this.id = id;
        this.department = department;
        this.municipality = municipality;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.trgh = trgh;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.fileweather = fileweather;
        this.filesoil = filesoil;
        this.fileubic = fileubic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTrgh() {
        return trgh;
    }

    public void setTrgh(double trgh) {
        this.trgh = trgh;
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

    public String getFileubic() {
        return fileubic;
    }

    public void setFileubic(String fileubic) {
        this.fileubic = fileubic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + '}';
    }

}
