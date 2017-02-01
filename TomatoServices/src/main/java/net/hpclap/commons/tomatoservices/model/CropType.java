package net.hpclap.commons.tomatoservices.model;

public enum CropType {

    greenHouse("Invernadero"), openField("Campo Abierto");
    private final String label;

    private CropType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
