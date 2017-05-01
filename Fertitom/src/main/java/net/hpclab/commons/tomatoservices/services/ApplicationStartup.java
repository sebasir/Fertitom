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
                Util.pathNixInput = config.getPathNixInput();
                Util.pathWinInput = config.getPathWinInput();
                Util.pathNixOutput = config.getPathNixOutput();
                Util.pathWinOutput = config.getPathWinOutput();
                Util.pathFilesInput = Util.isWindows()? Util.pathWinInput: Util.pathNixInput;
                Util.pathFilesOutput = Util.isWindows()? Util.pathWinOutput: Util.pathNixOutput;
                Util.pathInput = Util.pathFilesInput + File.separator + config.getPathInput();
                Util.pathOutput = Util.pathFilesOutput + File.separator + config.getPathOutput();
                Util.fileParam = Util.pathInput + File.separator + config.getFileParam();
                Util.scriptName = Util.pathFilesInput + File.separator + config.getScriptName();
            }
            
            System.out.println("Ubicaciones = " + Util.locations);
            System.out.println("PathFilesInput = " + Util.pathFilesInput + " (r = " + Util.isReadable(Util.pathFilesInput) + ", w = " + Util.isWritable(Util.pathFilesInput) + ")");
            System.out.println("PathFilesOutput = " + Util.pathFilesOutput + " (r = " + Util.isReadable(Util.pathFilesOutput) + ", w = " + Util.isWritable(Util.pathFilesOutput) + ")");
            System.out.println("PathInput = " + Util.pathInput + " (r = " + Util.isReadable(Util.pathInput) + ", w = " + Util.isWritable(Util.pathInput) + ")");
            System.out.println("PathOutput = " + Util.pathOutput + " (r = " + Util.isReadable(Util.pathOutput) + ", w = " + Util.isWritable(Util.pathOutput) + ")");
            System.out.println("FileParam = " + Util.fileParam + " (r = " + Util.isReadable(Util.fileParam) + ", w = " + Util.isWritable(Util.fileParam) + ")");
            System.out.println("ScriptName = " + Util.scriptName + " (r = " + Util.isReadable(Util.scriptName) + ", w = " + Util.isWritable(Util.scriptName) + ")");
            
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
