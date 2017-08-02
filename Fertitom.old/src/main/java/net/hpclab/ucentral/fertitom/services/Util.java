package net.hpclab.ucentral.fertitom.services;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import net.hpclab.ucentral.fertitom.model.Location;
import net.hpclab.ucentral.fertitom.model.Property;

public class Util implements Serializable {

    public static final long serialVersionUID = 1L;

    public static List<Location> locations;
    public static List<Property> parameters;
    public static String pathFilesInput;
    public static String pathNixInput;
    public static String pathWinInput;
    public static String pathFilesOutput;
    public static String pathNixOutput;
    public static String pathWinOutput;
    public static String pathInput;
    public static String pathOutput;
    public static String fileParam;
    public static String scriptName;
    public static String OS = System.getProperty("os.name").toLowerCase();

    public class Constant {

        public static final String VALUE_SEPARATOR = ",";
        public static final String CHANNEL = "/results";
        public static final String CONFIG_FILE = "/WEB-INF/classes/configuration/configuration.xml";
        public static final String R_COMMAND = "Rscript";
//        public static final String SCRIPT = "/usr/local/fertitom/script.R";
        public static final String SCRIPT = "/usr/local/fertitom/TomatoSSD.R";
        public static final String SIMUL_R = "SIMUL_R";
        public static final String SPACE = " ";
        public static final String SOIL_RESULT_FILE = "SOIL_";
        public static final String PLANT_RESULT_FILE = "PLANT_";
        public static final String CLIMATE_RESULT_FILE = "CLIMATE_";
        public static final String REPORT_RESULT_FILE = "REPORT_0.knit.html";
        public static final double DM_LEAF = 1;
        public static final double DM_STEM = 1;
        public static final double DM_FRUIT = 1;
        public static final double TOTAL_LEAF_AREA = 1;
    }

    public static boolean isWindows() {
        String username = System.getProperty("user.name");
        System.out.println("username = " + username);
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
    
    public static void createSimulationFolder(String simulationId) throws Exception {
        new File(simulationId).mkdir();
    }
}
