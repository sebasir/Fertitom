package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration implements Serializable {
    public static final long serialVersionUID = 1L;

    private String pathNix;
    private String pathWin;
    private String pathInput;
    private String pathOutput;
    private String fileParam;
    private List<Location> location;
    
    public Configuration() {
        super();
    }

    @XmlElement
    public String getPathNix() {
        return pathNix;
    }

    public void setPathNix(String pathNix) {
        this.pathNix = pathNix;
    }

    @XmlElement
    public String getPathWin() {
        return pathWin;
    }

    public void setPathWin(String pathWin) {
        this.pathWin = pathWin;
    }

    @XmlElement
    public String getPathInput() {
        return pathInput;
    }

    public void setPathInput(String pathInput) {
        this.pathInput = pathInput;
    }
    
    @XmlElement
    public String getPathOutput() {
        return pathOutput;
    }

    public void setPathOutput(String pathOutput) {
        this.pathOutput = pathOutput;
    }

    @XmlElement
    public String getFileParam() {
        return fileParam;
    }

    public void setFileParam(String fileParam) {
        this.fileParam = fileParam;
    }

    @XmlElement
    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
