package net.hpclab.commons.tomatoservices.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.hpclab.commons.tomatoservices.model.Location;
import net.hpclab.commons.tomatoservices.model.Simulation;
import net.hpclab.commons.tomatoservices.services.SimulationService;
import net.hpclab.commons.tomatoservices.services.Util;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@Named
@SessionScoped
public class simulacionBean implements Serializable {

    public static final long serialVersionUID = 1L;
    private List<Location> locations;
    private Simulation simulation;
    private long simDays;
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

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

    public void calcDays() {
        if (simulation != null) {
            if (simulation.getInitDate() != null && !simulation.getInitDate().isEmpty()) {
                if (simulation.getFinalDate() != null && !simulation.getFinalDate().isEmpty()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        simDays = (dateFormat.parse(simulation.getFinalDate()).getTime() - dateFormat.parse(simulation.getInitDate()).getTime()) / (1000 * 60 * 60 * 24);
                        showMessage("Numero de días simulados = " + simDays, null, FacesMessage.SEVERITY_INFO);
                    } catch (ParseException e) {
                        showMessage("Error", "Ingresa fechas válidas!!!", FacesMessage.SEVERITY_ERROR);
                    }
                }
            }
        }
    }

    public void launchSimulation() {
        try {
            SimulationService service = new SimulationService(simulation, eventBus);
            service.start();
            showMessage("Simulando", "", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            showMessage("Error simulando.", "Hubo un error simulando: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void calcRecommendation() {
        simulation.setRecNitrogen(300.0);
        simulation.setRecPhosphorus(200.0);
        simulation.setRecPotasium(600.0);
        simulation.setRecWater(5.0);
        simulation.setDmLeaf(2.0);
        simulation.setTotLeafArea(2.0);
        simulation.setDmStem(2.0);
        simulation.setDmFruit(2.0);
    }

    public void restart() {
        simulation = new Simulation();
        simDays = 0;
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

}
