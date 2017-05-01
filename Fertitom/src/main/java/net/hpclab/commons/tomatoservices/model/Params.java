package net.hpclab.commons.tomatoservices.model;

public enum Params {
    NITROGEN("Nitrógeno"), P2O5("Fósforo (P2O5)"), K2O("Potasio (K2O)");
    private final String label;
    
    private Params(String label) {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
