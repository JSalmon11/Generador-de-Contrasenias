package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Clase para diferentes utilidades en Windows.
 */
public class Windows {
    private static final String REGQUERY_UTIL = "reg query ";
    private static final String REGDWORD_TOKEN = "REG_DWORD";
    private static final String DARK_THEME_CMD = REGQUERY_UTIL
            + "\"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize\""
            + " /v AppsUseLightTheme";

    /**
     * Método para comprobar el tema activo en Windows.
     * @author <a href="https://gist.github.com/HanSolo/7cf10b86efff8ca2845bf5ec2dd0fe1d">Gerrit Grunwald</a>
     * @return
     *         <ul>
     *         <li>True si está activado el modo oscuro de Windows.</li>
     *         <li>False si está desactivado el modo oscuro de Windows.</li>
     *         </ul>
     */
    public static boolean isWindowsDarkMode() {
        try {
            Process process = Runtime.getRuntime().exec(DARK_THEME_CMD);
            StreamReader reader = new StreamReader(process.getInputStream());

            reader.start();
            process.waitFor();
            reader.join();

            String result = reader.getResult();
            int p = result.indexOf(REGDWORD_TOKEN);

            if (p == -1) {
                return false;
            }

            // 1 == Light Mode, 0 == Dark Mode
            String temp = result.substring(p + REGDWORD_TOKEN.length()).trim();
            return ((Integer.parseInt(temp.substring("0x".length()), 16))) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw;

        StreamReader(InputStream is) {
            this.is = is;
            sw = new StringWriter();
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            } catch (IOException e) {
            }
        }

        String getResult() {
            return sw.toString();
        }
    }

}
