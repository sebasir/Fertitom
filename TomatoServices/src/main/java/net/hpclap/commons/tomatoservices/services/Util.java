package net.hpclap.commons.tomatoservices.services;

import java.io.Serializable;
import java.util.List;
import net.hpclap.commons.tomatoservices.model.Location;

public class Util implements Serializable {

    public static final long serialVersionUID = 1L;

    public static List<Location> locations;
    public static String fileServer;
    public static String OS = System.getProperty("os.name").toLowerCase();

    public class Constant {

        public static final String CHANNEL = "/results";
        public static final String CONFIG_FILE = "/WEB-INF/classes/configuration/configuration.xml";
    }
    
    public static boolean isWindows() {
        return OS.contains("win");
    }
}
