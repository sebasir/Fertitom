package net.hpclab.commons.tomatoservices.services;

import java.io.Serializable;
import java.util.List;
import net.hpclab.commons.tomatoservices.model.Location;

public class Util implements Serializable {

    public static final long serialVersionUID = 1L;

    public static List<Location> locations;
    public static String fileServer;
    public static String OS = System.getProperty("os.name").toLowerCase();

    public class Constant {

        public static final String CHANNEL = "/WEB-INF/classes/results";
        public static final String CONFIG_FILE = "/WEB-INF/classes/configuration/configuration.xml";
        public static final String BASE_FILE = "/WEB-INF/classes/archivos_base/";
    }
    
    public static boolean isWindows() {
        return OS.contains("win");
    }
}
