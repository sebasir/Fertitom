package net.hpclab.commons.tomatoservices.services;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import net.hpclab.commons.tomatoservices.model.Location;
import net.hpclab.commons.tomatoservices.model.Property;

public class Util implements Serializable {

    public static final long serialVersionUID = 1L;

    public static List<Location> locations;
    public static List<Property> parameters;
    public static String pathFiles;
    public static String pathNix;
    public static String pathWin;
    public static String pathInput;
    public static String pathOutput;
    public static String fileParam;
    public static String OS = System.getProperty("os.name").toLowerCase();

    public class Constant {

        public static final String VALUE_SEPARATOR = ",";
        public static final String CHANNEL = "/WEB-INF/classes/results";
        public static final String CONFIG_FILE = "/WEB-INF/classes/configuration/configuration.xml";
        public static final double DM_LEAF = 1;
        public static final double DM_STEM = 1;
        public static final double DM_FRUIT = 1;
        public static final double TOTAL_LEAF_AREA = 1;
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean fileExist(String path) {
        return new File(path).exists();
    }

    public static boolean isWritable(String path) {
        return new File(path).canWrite();
    }

    public static boolean isReadable(String path) {
        return new File(path).canRead();
    }
}
