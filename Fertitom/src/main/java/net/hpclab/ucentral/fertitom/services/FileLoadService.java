package net.hpclab.ucentral.fertitom.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.hpclab.ucentral.fertitom.model.Location;
import net.hpclab.ucentral.fertitom.model.Params;
import net.hpclab.ucentral.fertitom.model.Property;

public class FileLoadService implements Serializable {

    public static final long serialVersionUID = 1L;
    private final List<Location> locations;

    public FileLoadService(List<Location> locations) {
        this.locations = locations;
    }

    public void loadFiles() throws Exception {
        loadLocationFiles();
        loadParamaterFile();
    }

    private void loadParamaterFile() throws Exception {
        InputStream in;
        BufferedReader reader;
        String line;
        String[] commaLine;
        int i = 0;
        in = new FileInputStream(Util.fileParam);
        reader = new BufferedReader(new InputStreamReader(in));
        Util.parameters = new ArrayList<>();
        Property property;
        Params param = Params.NITROGEN;
        while ((line = reader.readLine()) != null) {
            i++;
            if (i == 1) {
                continue;
            }
            if (!line.contains(Util.Constant.VALUE_SEPARATOR)) {
                continue;
            }
            switch (i) {
                case 2:
                    param = Params.NITROGEN;
                    break;
                case 3:
                    param = Params.P2O5;
                    break;
                case 4:
                    param = Params.K2O;
                    break;
            }

            property = new Property();
            commaLine = line.split(Util.Constant.VALUE_SEPARATOR);
            property.setParam(param);
            property.setUnity(commaLine[1]);
            property.setDefaultVal(Double.parseDouble(commaLine[2]));
            property.setMinimumVal(Double.parseDouble(commaLine[3]));
            property.setMaximumVal(Double.parseDouble(commaLine[4]));
            Util.parameters.add(property);
        }
    }

    private void loadLocationFiles() throws Exception {
        InputStream in;
        BufferedReader reader;
        String line;
        String initDate;
        String finalDate;
        String[] commaLine;
        int i;
        for (Location location : locations) {
            in = new FileInputStream(Util.pathInput + File.separator + location.getFileweather());
            reader = new BufferedReader(new InputStreamReader(in));
            initDate = null;
            finalDate = null;
            i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (i == 1) {
                    continue;
                }
                if (!line.contains(Util.Constant.VALUE_SEPARATOR)) {
                    continue;
                }
                if (i == 2) {
                    initDate = line.split(Util.Constant.VALUE_SEPARATOR)[0];
                }
                finalDate = line.split(Util.Constant.VALUE_SEPARATOR)[0];
            }
            location.setInitDate(initDate);
            location.setFinalDate(finalDate);
            reader.close();
            in = new FileInputStream(Util.pathInput + File.separator + location.getFileubic());
            reader = new BufferedReader(new InputStreamReader(in));
            i = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (i == 1) {
                    continue;
                }
                if (!line.contains(Util.Constant.VALUE_SEPARATOR)) {
                    continue;
                }
                commaLine = line.split(Util.Constant.VALUE_SEPARATOR);
                location.setDepartment(commaLine[0]);
                location.setMunicipality(commaLine[1]);
                location.setType(Integer.parseInt(commaLine[2]));
                location.setLatitude(Double.parseDouble(commaLine[3]));
                location.setLongitude(Double.parseDouble(commaLine[4]));
                location.setAltitude(Double.parseDouble(commaLine[5]));
                location.setTrgh(Double.parseDouble(commaLine[6]));
            }
            reader.close();
        }
    }
}
