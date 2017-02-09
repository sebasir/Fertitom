package net.hpclap.commons.tomatoservices.services;

import java.io.Serializable;
import net.hpclap.commons.tomatoservices.model.Simulation;

public class SimulationService implements Serializable {
    public static final long serialVersionUID = 1L;
    private Simulation simulation;

    public SimulationService(Simulation simulation) {
        this.simulation = simulation;
    }
    
    public void launchSimulation() throws Exception {
        System.out.println(" ---> Lanzando Simulación: " + simulation);
        throw new Exception("SE SUPONE QUE VAMOS A SIMULAR!!!");
    }
    
    class IOStreamer {
        
    }
}
