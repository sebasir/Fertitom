package net.hpclab.ucentral.fertitom.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.hpclab.ucentral.fertitom.model.Location;
import net.hpclab.ucentral.fertitom.model.Params;
import net.hpclab.ucentral.fertitom.model.Property;
import net.hpclab.ucentral.fertitom.model.Simulation;
import net.hpclab.ucentral.fertitom.services.SimulationService;
import net.hpclab.ucentral.fertitom.services.Util;
import org.primefaces.json.JSONObject;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@Named
@SessionScoped
public class SimulationBean implements Serializable {

    public static final long serialVersionUID = 1L;
    private List<Location> locations;
    private List<Property> parameters;
    private Simulation simulation;
    private long simDays;
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    public SimulationBean() {
    }

    @PostConstruct
    public void init() {
        locations = Util.locations;
        parameters = Util.parameters;
        simulation = new Simulation();
    }

    public void showMessage(String title, String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
    }

    public void setMapCenter() {
        JSONObject json = new JSONObject();
        json.put("latitude", simulation.getLocation().getLatitude());
        json.put("longitude", simulation.getLocation().getLongitude());
        json.put("nombre", simulation.getLocation().getMunicipality());
        json.put("zoom", 9);
        JSONObject jsonCommand = new JSONObject();
        jsonCommand.put("status", "centerMap");
        jsonCommand.put("centerMap", json);
        eventBus.publish(Util.Constant.CHANNEL, jsonCommand);
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
            if (!simulation.isSimulated()) {
                SimulationService service = new SimulationService(simulation, eventBus);
                service.start();
                showMessage("Simulando", "", FacesMessage.SEVERITY_INFO);
                simulation.setSimulated(true);
            } else {
                showMessage("Ya se había ejecutado la simulación!", "", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception e) {
            showMessage("Error simulando.", "Hubo un error simulando: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void calcRecommendation(Params param, double value) {
        switch (param) {
            case NITROGEN:

                simulation.setRecNitrogen(value);
                break;
            case P2O5:
                simulation.setRecPhosphorus(value);
                break;
            case K2O:
                simulation.setRecPotasium(value);
                break;
        }
        showMessage("Cambio de Valor", "El valor nuevo es " + value, FacesMessage.SEVERITY_INFO);
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

    public List<Property> getParameters() {
        return parameters;
    }

    public void setParameters(List<Property> parameters) {
        this.parameters = parameters;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}