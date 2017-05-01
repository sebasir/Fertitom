package net.hpclab.ucentral.fertitom.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.hpclab.ucentral.fertitom.services.Util;

@WebServlet(name = "mediaServlet", urlPatterns = "/mediaServlet")
public class MediaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext cntx = getServletContext();
        File archivo = null;
        String fileName;
        String mime = null;
        String simulationId = request.getParameter(Util.Constant.SIMUL_R);
        if (simulationId == null) {
            simulationId = "";
        }
        String assetId = request.getParameter("ASSET");
        if (assetId == null) {
            assetId = "";
        }

        String tab = request.getParameter("TAB");
        if (tab == null) {
            tab = "";
        }

        if (!simulationId.isEmpty() && !assetId.isEmpty() && !tab.isEmpty() && (Util.Constant.SOIL_RESULT_FILE.equals(tab) || Util.Constant.PLANT_RESULT_FILE.equals(tab))) {
            fileName = Util.pathOutput + File.separator + simulationId + File.separator + tab + assetId;
            fileName += ("0".equals(assetId) ? ".csv" : ".png");
            archivo = new File(fileName);
            mime = cntx.getMimeType(fileName);
            System.out.println(fileName);
        }

        if ((!simulationId.isEmpty() && !assetId.isEmpty()) && !assetId.isEmpty() && archivo != null) {
            response.setContentType(mime);
            if (archivo.exists()) {
                response.setContentLength((int) archivo.length());
                try (FileInputStream in = new FileInputStream(archivo); OutputStream out = response.getOutputStream()) {
                    byte[] buf = new byte[1024];
                    int count;
                    while ((count = in.read(buf)) >= 0) {
                        out.write(buf, 0, count);
                    }
                }
            }
            System.out.println("No pudo leer el archivo"+archivo.getName());
        }
        else{
            System.out.println("Algo salió mal en el servlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
