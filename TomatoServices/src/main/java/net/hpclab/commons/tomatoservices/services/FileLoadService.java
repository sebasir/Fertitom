package net.hpclab.commons.tomatoservices.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContext;
import net.hpclab.commons.tomatoservices.model.Location;

public class FileLoadService implements Serializable {

    public static final long serialVersionUID = 1L;
    private final List<Location> locations;
    private final ServletContext servletContext;

    public FileLoadService(List<Location> locations, ServletContext servletContext) {
        this.locations = locations;
        this.servletContext = servletContext;
    }

    public void loadFiles() throws Exception {
        InputStream in;
        BufferedReader reader;
        String line;
            String initDate = null;
            String finalDate = null;
            int i = 0;
        for (Location location : locations) {
            System.out.println("Cargando -> " + Util.Constant.BASE_FILE + location.getFileweather());
            in = servletContext.getResourceAsStream(Util.Constant.BASE_FILE + location.getFileweather());
            reader = new BufferedReader(new InputStreamReader(in));
            initDate = null;
            finalDate = null;
            i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (i == 1) {
                    continue;
                }
                if (i == 2) {
                    initDate = line.split(",")[0];
                }
                finalDate = line.split(",")[0];
            }
            location.setInitDate(initDate);
            location.setFinalDate(finalDate);
            reader.close();
        }
    }
}
