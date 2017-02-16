package net.hpclap.commons.tomatoservices.services;

import java.io.InputStream;
import java.io.Serializable;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import net.hpclap.commons.tomatoservices.model.Configuration;

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
            }
            System.out.println("Ubicaciones -> " + Util.locations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
