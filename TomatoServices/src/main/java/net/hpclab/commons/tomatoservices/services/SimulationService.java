package net.hpclab.commons.tomatoservices.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import net.hpclab.commons.tomatoservices.model.Simulation;
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
            simulation.setId(Util.Constant.SIMUL_R + "_0123456789"); // System.currentTimeMillis();
            final String resultSimulation = Util.pathOutput + File.separator + simulation.getId();
            Util.createSimulationFolder(resultSimulation);
            String command = Util.Constant.R_COMMAND + Util.Constant.SPACE + simulation.getLocation().getPrefix() + Util.Constant.SPACE + simulation.getPlm2();
            System.out.println("CMD: " + command);
            command = "ping echo";
            eventBus.publish(Util.Constant.CHANNEL, "CMD: " + command);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    eventBus.publish(Util.Constant.CHANNEL, "exe");
                }
                proc.waitFor();
            }
            eventBus.publish(Util.Constant.CHANNEL, "simulationId=" + simulation.getId());
        } catch (Exception e) {
            eventBus.publish(Util.Constant.CHANNEL, "Error: " + e.getMessage());
            System.out.println("Error -> " + e.getMessage());
        }
    }
}
