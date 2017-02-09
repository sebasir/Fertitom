package net.hpclap.commons.tomatoservices.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.hpclap.commons.tomatoservices.model.CropType;
import net.hpclap.commons.tomatoservices.model.Location;
import net.hpclap.commons.tomatoservices.model.Simulation;
import net.hpclap.commons.tomatoservices.services.SimulationService;
import net.hpclap.commons.tomatoservices.services.Util;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class simulacionBean implements Serializable {

    public static final long serialVersionUID = 1L;
    private List<Location> locations;
    private Simulation simulation;
    private UploadedFile fileWeather;
    private UploadedFile fileSoil;
    private long simDays;
    
    public simulacionBean() {
    }

    @PostConstruct
    public void init() {
        locations = Util.locations;
        simulation = new Simulation();
    }
    
    public void showMessage(String title, String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
    }

    public void handleFileUpload(FileUploadEvent event) {
        simulation.setFileWeatherName(event.getFile().getFileName());
        showMessage("Archivo cargado!", "El archivo " + simulation.getFileWeatherName() + " ha sido cargado satisfactoriamente", FacesMessage.SEVERITY_INFO);
    }
    
    public void calcDays() {
        if (simulation != null) {
            if (simulation.getInitDate() != null && !simulation.getInitDate().isEmpty()) {
                if (simulation.getFinalDate() != null && !simulation.getFinalDate().isEmpty()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        simDays = (dateFormat.parse(simulation.getFinalDate()).getTime() - dateFormat.parse(simulation.getInitDate()).getTime()) / (1000 * 60 * 60 * 24);
                        showMessage("Numero de días simulados = " + simDays, null, FacesMessage.SEVERITY_INFO);
                    } catch (Exception e) {
                        showMessage("Error", "Ingresa fechas válidas!!!", FacesMessage.SEVERITY_ERROR);
                    }
                }
            }
        }
    }
    
    public void launchSimulation() {
        try {
            SimulationService service = new SimulationService(simulation);
            service.launchSimulation();
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error simulando.", "Hubo un error simulando: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public UploadedFile getFileWeather() {
        return fileWeather;
    }

    public void setFileWeather(UploadedFile fileWeather) {
        this.fileWeather = fileWeather;
    }

    public UploadedFile getFileSoil() {
        return fileSoil;
    }

    public void setFileSoil(UploadedFile fileSoil) {
        this.fileSoil = fileSoil;
    }

    public CropType[] getCropTypes() {
        return CropType.values();
    }
}
