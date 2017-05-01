package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;

public class Property implements Serializable {
    public static final long serialVersionUID = 1L;
    
    private Params param;
    private String unity;
    private double defaultVal;
    private double minimumVal;
    private double maximumVal;

    public Property() {
        
    }
    
    public Params getParam() {
        return param;
    }

    public void setParam(Params param) {
        this.param = param;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public double getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(double defaultVal) {
        this.defaultVal = defaultVal;
    }

    public double getMinimumVal() {
        return minimumVal;
    }

    public void setMinimumVal(double minimumVal) {
        this.minimumVal = minimumVal;
    }

    public double getMaximumVal() {
        return maximumVal;
    }

    public void setMaximumVal(double maximumVal) {
        this.maximumVal = maximumVal;
    }
}
