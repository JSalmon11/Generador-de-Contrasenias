package Utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Genera una contraseña segura.
 * 
 * @author <a href="https://github.com/JSalmon11">Jorge Salmón</a>
 */
public class GeneratePass {
    /**
     * Genera una contraseña con las especificaciones deseadas por el usuario.
     * 
     * @author <a href="https://github.com/JSalmon11">Jorge Salmón</a>
     * @param longitud longitud de la contraseña.
     * @return String de una contraseña segura.
     */
    public static String generarContraseñaSegura(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        char c;
        for (int i = 0; i < sb.capacity(); ++i) {
            c = (char) ThreadLocalRandom.current().nextInt(33, 126 + 1);
            sb.insert(i, c);
        }
        return sb.toString();
    }

}