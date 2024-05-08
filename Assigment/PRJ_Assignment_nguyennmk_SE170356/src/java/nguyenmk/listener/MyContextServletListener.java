/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenmk.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author nguyen
 */
public class MyContextServletListener implements ServletContextListener{
    
        @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1. Get Context Scope
        ServletContext context = sce.getServletContext();
        //2. Read file properities
        String siteMapsPath = context.getInitParameter("SITEMAPS_FILE_PATH");
        Properties siteMaps = null;
        InputStream is = null;
        
        try {
            siteMaps = new Properties();
            is = context.getResourceAsStream(siteMapsPath);
            siteMaps.load(is);
            //3. Caching on Contexr scope
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextServletListener _ IO" + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("MyContextServletListener _ IO" + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
