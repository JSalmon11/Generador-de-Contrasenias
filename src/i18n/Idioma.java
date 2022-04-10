package i18n;

import java.io.IOException;
import java.util.Properties;

/**
 * @author <a href=
 *         "https://www.discoduroderoer.es/como-hacer-una-aplicacion-multi-idioma-en-java/">Disco
 *         duro de roer</a>
 */
public class Idioma extends Properties {

    private static final long serialVersionUID = 1L;

    /**
     * Carga el idioma deseado y traduce la aplicación.
     * @author <a href=
     *         "https://www.discoduroderoer.es/como-hacer-una-aplicacion-multi-idioma-en-java/">Disco
     *         duro de roer</a>
     * @param idioma String que contiene el idioma del usuario.
     */
    public Idioma(String idioma) {
        switch (idioma) {
            case "es":
                getProperties("espanol.properties");
                break;
            case "en":
                getProperties("ingles.properties");
                break;
            default:
                getProperties("ingles.properties");
        }

    }

    private void getProperties(String idioma) {
        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (IOException ex) {

        }
    }
}