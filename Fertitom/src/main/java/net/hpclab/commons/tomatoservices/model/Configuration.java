package net.hpclab.commons.tomatoservices.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration implements Serializable {
    public static final long serialVersionUID = 1L;

    private String pathNixInput;
    private String pathWinInput;
    private String pathNixOutput;
    private String pathWinOutput;
    private String pathInput;
    private String pathOutput;
    private String fileParam;
    private String scriptName;
    private List<Location> location;
    
    public Configuration() {
        super();
    }

    @XmlElement
    public String getPathNixInput() {
        return pathNixInput;
    }

    public void setPathNixInput(String pathNixInput) {
        this.pathNixInput = pathNixInput;
    }

    @XmlElement
    public String getPathWinInput() {
        return pathWinInput;
    }

    public void setPathWinInput(String pathWinInput) {
        this.pathWinInput = pathWinInput;
    }
    
    @XmlElement
    public String getPathNixOutput() {
        return pathNixOutput;
    }

    public void setPathNixOutput(String pathNixOutput) {
        this.pathNixOutput = pathNixOutput;
    }

    @XmlElement
    public String getPathWinOutput() {
        return pathWinOutput;
    }

    public void setPathWinOutput(String pathWinOutput) {
        this.pathWinOutput = pathWinOutput;
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

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @XmlElement
    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
