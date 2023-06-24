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
        if (longitud == -1) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(longitud);
            char c;
            char lastChar = '\u0000'; //lastChar valor null
            for (int i = 0; i < sb.capacity(); ++i) {
                boolean repeat;
                int count;
                do {
                    repeat = false;
                    count = 0;
                    c = (char) ThreadLocalRandom.current().nextInt(33, 126 + 1);
                    for (int j = 0; j < i; j++) {
                        if (sb.charAt(j) == c) {
                            count++;
                        }
                        if (count >= 2) {
                            repeat = true;
                            break;
                        }
                    }
                    if (c == lastChar) {
                        repeat = true;
                    } else if (Character.isLetter(c) && Character.isLetter(lastChar)) {
                        if (Character.isUpperCase(c) == Character.isUpperCase(lastChar)) {
                            repeat = true;
                        }
                    } else if (Character.isDigit(c) && Character.isDigit(lastChar)) {
                        repeat = true;
                    }
                } while (repeat);
                sb.insert(i, c);
                lastChar = c;
            }
            return sb.toString();
        }
    }

}