package net.hpclap.commons.tomatoservices.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        String command = "ping www.google.com";
        eventBus.publish(Util.Constant.CHANNEL, "CMD: " + command);
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    eventBus.publish(Util.Constant.CHANNEL, line);
                }
                proc.waitFor();
            }
        } catch (Exception e) {
            eventBus.publish(Util.Constant.CHANNEL, "Error: " + e.getMessage());
        }
    }
}
