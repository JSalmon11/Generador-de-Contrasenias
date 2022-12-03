package Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import Main.App;
import i18n.Idioma;

public class ComprobarActualizaciones {
    private static final Idioma idioma = App.idioma;
    static String error = null;

    public String getErrorMessage() {
        return error;
    }

    public static void setErrorMessage(String msg) {
        error = msg;
    }

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
     *         <li>String "-2" si ha surgido algún error.</li>
     *         </ul>
     */
    public static String checkUpdate(String version) {
        String tagVersion = getLastTag();

        if (!tagVersion.equals("-2")) {
            if (Integer.parseInt(version.replace(".", "")) >= Integer.parseInt(tagVersion.replace(".", ""))) {
                tagVersion = "-1";
            }
        }

        return tagVersion;
    }

    public static String getLastTag() {
        try {
            URL url = new URL("https://api.github.com/repos/JSalmon11/Generador-de-Contrasenias/tags");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                setErrorMessage(responseCode + idioma.getProperty("textErrorGitHub"));
                return "-2";
            } else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                return inline.substring(10, 15);
            }
        } catch (IOException e) {
            setErrorMessage(idioma.getProperty("textErrorInteno"));
        }
        return "-2";
    }

}