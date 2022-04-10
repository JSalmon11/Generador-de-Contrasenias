package Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.datatransfer.StringSelection;
import Utils.ComprobarActualizaciones;
import Utils.FormActualizar;
import Utils.GeneratePass;
import Utils.Windows;
import i18n.Idioma;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 * Crea y ejecuta una interfaz de usuario para generar contraseñas seguras.
 * 
 * @author <a href="https://github.com/JSalmon11">Jorge Salmón</a>
 */
public class App extends Application {
    private static String version = "1.1.0";
    public static final Idioma idioma = new Idioma(System.getProperty("user.language"));

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/img/icon.png")));
        stage.setResizable(false);
        stage.setTitle(idioma.getProperty("mainTitulo"));
        
        Text textLongitud = new Text(idioma.getProperty("textLongitud"));
        textLongitud.setFont(new Font("Roboto", 20));
        textLongitud.setLayoutX(14);
        textLongitud.setLayoutY(38);

        TextField longitud = new TextField("14");
        longitud.setMaxSize(50, 14);
        longitud.setFont(new Font("Roboto", 20));
        longitud.setLayoutX(110);
        longitud.setLayoutY(10);

        TextField contraseñas = new TextField();
        contraseñas.setMinSize(272, 20);
        contraseñas.setLayoutX(14);
        contraseñas.setLayoutY(290);

        Button mostrar = new Button(idioma.getProperty("buttonMostrar"));
        mostrar.setLayoutX(14);
        mostrar.setLayoutY(340);

        Button copiar = new Button(idioma.getProperty("buttonCopiar"));
        copiar.setLayoutX(235);
        copiar.setLayoutY(340);

        Button actualizar = new Button();
        actualizar.setLayoutX(270);
        actualizar.setLayoutY(5);
        actualizar.setPrefSize(10, 10);
        actualizar.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("/img/buttons/update.png"))));
        actualizar.setTooltip(new Tooltip(idioma.getProperty("hoverButtonActualizar")));

        Pane datos = new Pane();
        datos.getChildren().addAll(longitud, mostrar, copiar, actualizar, textLongitud, contraseñas);

        Scene scene = new Scene(new StackPane(datos), 300, 380);
        if (Windows.isWindowsDarkMode()) {
            scene.getStylesheets().add("visual/dark-mode.css");
        }

        stage.setScene(scene);
        stage.show();

        mostrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                contraseñas.setText((GeneratePass.generarContraseñaSegura(Integer.parseInt(longitud.getText()))));
            }
        });

        copiar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(contraseñas.getText()), null);
            }
        });

        actualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String versionUrl = ComprobarActualizaciones.checkUpdate(version);
                if (!versionUrl.equals("-1")) {
                    String nuevaVersion;
                    nuevaVersion = "https://github.com/JSalmon11/Generador-de-Contrasenias/releases/tag/" + versionUrl;
                    Stage updateStage = new Stage();
                    FormActualizar.actualizar(nuevaVersion, updateStage);
                }else{
                    Stage updateStage = new Stage();
                    FormActualizar.actualizar(versionUrl, updateStage);
                }
            }
        });

    }

}