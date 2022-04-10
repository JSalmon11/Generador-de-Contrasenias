package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ComprobarActualizaciones {

    /**
     * Comprueba si hay una nueva versión disponible de la aplicación mediante
     * GitHub.
     * 
     * @author <a href="https://github.com/JSalmon11">Jorge Salmón</a>
     * @param version Versión actual de la aplicación.
     * @return
     *         <ul>
     *         <li>String de la nueva versión de la aplicación.</li>
     *         <li>String "-1" si no hay nueva versión disponible.</li>
     *         </ul>
     */
    public static String checkUpdate(String version) {
        String tagVersion = "-1";
        try {
            URL url = new URL("https://github.com/JSalmon11/Generador-de-Contrasenias/tags");
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String tags;
            boolean found = false;
            while ((tags = bufferedReader.readLine()) != null && !found) {
                if(tags.contains("<a href=\"/JSalmon11/Generador-de-Contrasenias/releases/tag/")){
                    tagVersion = tags.replaceAll("<a href=\"/JSalmon11/Generador-de-Contrasenias/releases/tag/", "").replace(">", "").replace("\"","").trim();
                    found = true;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Integer.parseInt(version.replace(".","")) >= Integer.parseInt(tagVersion.replace(".",""))){
            tagVersion = "-1";
        }
        return tagVersion;
    }

}