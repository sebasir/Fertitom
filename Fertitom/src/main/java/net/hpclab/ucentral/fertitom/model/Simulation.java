package net.hpclab.ucentral.fertitom.model;

import java.io.Serializable;

public class Simulation implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;
    private Location location;
    private String initDate;
    private String finalDate;
    private Double plm2;
    private Double recNitrogen;
    private Double recPhosphorus;
    private Double recPotasium;
    private Double recWater;
    private boolean simulated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
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

    public Double getPlm2() {
        return plm2;
    }

    public void setPlm2(Double plm2) {
        this.plm2 = plm2;
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

    public boolean isSimulated() {
        return simulated;
    }

    public void setSimulated(boolean simulated) {
        this.simulated = simulated;
    }
}
