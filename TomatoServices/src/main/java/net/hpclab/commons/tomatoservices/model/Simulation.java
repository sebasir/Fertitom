package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;

public class Simulation implements Serializable {

    public static final long serialVersionUID = 1L;

    private Location location;
    private String initDate;
    private String finalDate;
    private Double trgh;
    private Double plm2;
    private Double dmLeaf;
    private Double totLeafArea;
    private Double dmStem;
    private Double dmFruit;
    private Double recNitrogen;
    private Double recPhosphorus;
    private Double recPotasium;
    private Double recWater;
    private String result;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Double getTrgh() {
        return trgh;
    }

    public void setTrgh(Double trgh) {
        this.trgh = trgh;
    }

    public Double getPlm2() {
        return plm2;
    }

    public void setPlm2(Double plm2) {
        this.plm2 = plm2;
    }

    public Double getDmLeaf() {
        return dmLeaf;
    }

    public void setDmLeaf(Double dmLeaf) {
        this.dmLeaf = dmLeaf;
    }

    public Double getTotLeafArea() {
        return totLeafArea;
    }

    public void setTotLeafArea(Double totLeafArea) {
        this.totLeafArea = totLeafArea;
    }

    public Double getDmStem() {
        return dmStem;
    }

    public void setDmStem(Double dmStem) {
        this.dmStem = dmStem;
    }

    public Double getDmFruit() {
        return dmFruit;
    }

    public void setDmFruit(Double dmFruit) {
        this.dmFruit = dmFruit;
    }

    public Double getRecNitrogen() {
        return recNitrogen;
    }

    public void setRecNitrogen(Double recNitrogen) {
        this.recNitrogen = recNitrogen;
    }

    public Double getRecPhosphorus() {
        return recPhosphorus;
    }

    public void setRecPhosphorus(Double recPhosphorus) {
        this.recPhosphorus = recPhosphorus;
    }

    public Double getRecPotasium() {
        return recPotasium;
    }

    public void setRecPotasium(Double recPotasium) {
        this.recPotasium = recPotasium;
    }

    public Double getRecWater() {
        return recWater;
    }

    public void setRecWater(Double recWater) {
        this.recWater = recWater;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
