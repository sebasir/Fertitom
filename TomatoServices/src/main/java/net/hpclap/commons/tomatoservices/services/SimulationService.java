package net.hpclap.commons.tomatoservices.services;

import java.io.Serializable;
import java.util.Random;
import net.hpclap.commons.tomatoservices.model.Simulation;
import org.primefaces.push.EventBus;

public class SimulationService extends Thread implements Serializable {
    
    public static final long serialVersionUID = 1L;
    private final EventBus eventBus;
    private final Simulation simulation;
    
    public SimulationService(Simulation simulation, EventBus eventBus) {
        this.eventBus = eventBus;
        this.simulation = simulation;
    }
    
    @Override
    public void run() {
        try {
            int i = 0;
            Random r = new Random();
            while (i++ < 10) {
                Thread.sleep(500);
                eventBus.publish(Util.Constant.CHANNEL, "Probando: Ejecución # " + i + " => " + r.nextInt());
            }
        } catch (Exception e) {
            eventBus.publish(Util.Constant.CHANNEL, "Error: " + e.getMessage());
        }
    }
}
