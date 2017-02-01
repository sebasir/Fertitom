package net.hpclap.commons.tomatoservices.model;

import java.io.Serializable;
import java.util.Date;

public class Simulation implements Serializable {
    public static final long serialVersionUID = 1L;

    private Location locationWeather;
    private Location locationSoil;
    private String fileWeatherName;
    private String fileSoilName;
    private CropType cropType;
    private Date initDate;
    private int daysNumber;
    private double trgh;
    private double plm2;
    private double dmLeaf;
    private double totLeafArea;
    private double dmStem;
    private double dmFruit;
    private double recNitrogen;
    private double recPhosphorus;
    private double recPotasium;
    private double recWater;
    private String result;

    public Location getLocationWeather() {
        return locationWeather;
    }

    public void setLocationWeather(Location locationWeather) {
        this.locationWeather = locationWeather;
    }

    public Location getLocationSoil() {
        return locationSoil;
    }

    public void setLocationSoil(Location locationSoil) {
        this.locationSoil = locationSoil;
    }

    public String getFileWeatherName() {
        return fileWeatherName;
    }

    public void setFileWeatherName(String fileWeatherName) {
        this.fileWeatherName = fileWeatherName;
    }

    public String getFileSoilName() {
        return fileSoilName;
    }

    public void setFileSoilName(String fileSoilName) {
        this.fileSoilName = fileSoilName;
    }

    public CropType getCropType() {
        return cropType;
    }

    public void setCropType(CropType cropType) {
        this.cropType = cropType;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
    }

    public double getTrgh() {
        return trgh;
    }

    public void setTrgh(double trgh) {
        this.trgh = trgh;
    }

    public double getPlm2() {
        return plm2;
    }

    public void setPlm2(double plm2) {
        this.plm2 = plm2;
    }

    public double getDmLeaf() {
        return dmLeaf;
    }

    public void setDmLeaf(double dmLeaf) {
        this.dmLeaf = dmLeaf;
    }

    public double getTotLeafArea() {
        return totLeafArea;
    }

    public void setTotLeafArea(double totLeafArea) {
        this.totLeafArea = totLeafArea;
    }

    public double getDmStem() {
        return dmStem;
    }

    public void setDmStem(double dmStem) {
        this.dmStem = dmStem;
    }

    public double getDmFruit() {
        return dmFruit;
    }

    public void setDmFruit(double dmFruit) {
        this.dmFruit = dmFruit;
    }

    public double getRecNitrogen() {
        return recNitrogen;
    }

    public void setRecNitrogen(double recNitrogen) {
        this.recNitrogen = recNitrogen;
    }

    public double getRecPhosphorus() {
        return recPhosphorus;
    }

    public void setRecPhosphorus(double recPhosphorus) {
        this.recPhosphorus = recPhosphorus;
    }

    public double getRecPotasium() {
        return recPotasium;
    }

    public void setRecPotasium(double recPotasium) {
        this.recPotasium = recPotasium;
    }

    public double getRecWater() {
        return recWater;
    }

    public void setRecWater(double recWater) {
        this.recWater = recWater;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
