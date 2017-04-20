package net.hpclab.commons.tomatoservices.services;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import net.hpclab.commons.tomatoservices.model.Configuration;

@WebListener
public class ApplicationStartup implements ServletContextListener, Serializable {

    public static final long serialVersionUID = 1L;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Inicializando Ubicaciones...");
            InputStream is = sce.getServletContext().getResourceAsStream(Util.Constant.CONFIG_FILE);
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            if (is != null) {
                Configuration config = (Configuration) jaxbUnmarshaller.unmarshal(is);
                Util.locations = config.getLocation();
                Util.pathNix = config.getPathNix();
                Util.pathWin = config.getPathWin();
                Util.fileParam = config.getFileParam();
                Util.pathFiles = Util.isWindows()? Util.pathWin: Util.pathNix;
                Util.pathInput = Util.pathFiles + File.separator + config.getPathInput();
                Util.pathOutput = Util.pathFiles + File.separator + config.getPathOutput();
            }
            
            System.out.println("Ubicaciones = " + Util.locations);
            System.out.println("PathFiles = " + Util.pathFiles + " (r = " + Util.isReadable(Util.pathFiles) + ", w = " + Util.isWritable(Util.pathFiles) + ")");
            System.out.println("PathInput = " + Util.pathInput + " (r = " + Util.isReadable(Util.pathInput) + ", w = " + Util.isWritable(Util.pathInput) + ")");
            System.out.println("PathOutput = " + Util.pathOutput + " (r = " + Util.isReadable(Util.pathOutput) + ", w = " + Util.isWritable(Util.pathOutput) + ")");
            
            FileLoadService fls = new FileLoadService(Util.locations);
            fls.loadFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
