package Utils;

import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Main.App;
import i18n.Idioma;

public class FormActualizar {
    private static final Idioma idioma = App.idioma;
    /**
     * Abre un nuevo formulario para elegir si actualizar o no a la nueva verión
     * disponible.
     * 
     * @author <a href="https://github.com/JSalmon11">Jorge Salmón</a>
     * @param nuevaVersion String que lleva la URL de la nueva verión disponible.
     */
    public static void actualizar(String nuevaVersion, Stage stage) {
        if (nuevaVersion.equals("-1")) {
            stage.setTitle(App.idioma.getProperty("actualizarTitulo"));
            stage.initStyle(StageStyle.UTILITY);

            Text textActualizar = new Text(idioma.getProperty("textActualizar"));
            textActualizar.setFont(new Font("Roboto", 14));
            textActualizar.setLayoutX(14);
            textActualizar.setLayoutY(5);

            Scene scene = new Scene(new StackPane(textActualizar), 300, 50);
            if (Windows.isWindowsDarkMode()) {
                scene.getStylesheets().add("visual/dark-mode.css");
            }

            stage.setScene(scene);
            stage.show();
        } else {
            stage.setTitle("Actualizar");
            stage.initStyle(StageStyle.UTILITY);

            Button actualizar = new Button(idioma.getProperty("buttonActualizar"));
            actualizar.setLayoutX(20);
            actualizar.setLayoutY(20);

            Button cancelar = new Button(idioma.getProperty("buttonCancelar"));
            cancelar.setLayoutX(215);
            cancelar.setLayoutY(20);
            Pane datos = new Pane();
            datos.getChildren().addAll(actualizar, cancelar);
            Scene scene = new Scene(new StackPane(datos), 300, 50);
            if (Windows.isWindowsDarkMode()) {
                scene.getStylesheets().add("visual/dark-mode.css");
            }

            stage.setScene(scene);
            stage.show();

            actualizar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.browse(new URI(nuevaVersion));
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            runtime.exec("xdg-open " + nuevaVersion);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            cancelar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    stage.close();
                }
            });
        }
    }

}