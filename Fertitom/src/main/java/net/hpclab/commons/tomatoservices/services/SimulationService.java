package net.hpclab.commons.tomatoservices.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import net.hpclab.commons.tomatoservices.model.Simulation;
import org.primefaces.json.JSONObject;
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
        JSONObject json = new JSONObject();
        try {
            //simulation.setId(Util.Constant.SIMUL_R + "_0123456789"); // System.currentTimeMillis();
            simulation.setId(Util.Constant.SIMUL_R + System.currentTimeMillis()); // System.currentTimeMillis();

            //final String resultSimulation = Util.pathOutput + File.separator + simulation.getId();
            //Util.createSimulationFolder(resultSimulation); //Now R does this work
            String command = Util.Constant.R_COMMAND + Util.Constant.SPACE + Util.Constant.SCRIPT + Util.Constant.SPACE
                    + simulation.getLocation().getPrefix() + Util.Constant.SPACE + simulation.getPlm2() + Util.Constant.SPACE + simulation.getId();
            System.out.println("CMD: " + command);
            eventBus.publish(Util.Constant.CHANNEL, "CMD: " + command);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    json.put("status", "exe");
                    eventBus.publish(Util.Constant.CHANNEL, json);
                }
                proc.waitFor();
            }
            json.put("status", "ok");
            json.put("simulId", simulation.getId());
            eventBus.publish(Util.Constant.CHANNEL, json);
        } catch (IOException | InterruptedException e) {
            json.put("status", "error");
            json.put("message", "Error: " + e.getMessage());
            eventBus.publish(Util.Constant.CHANNEL, json);
            System.out.println("Error -> " + e.getMessage());
        }
    }
}
