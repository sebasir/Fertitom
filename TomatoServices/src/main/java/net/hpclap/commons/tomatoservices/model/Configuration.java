package net.hpclap.commons.tomatoservices.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration implements Serializable {
    public static final long serialVersionUID = 1L;

    private List<Location> location;

    public Configuration() {
        super();
    }

    @XmlElement
    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
