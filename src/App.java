import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.datatransfer.StringSelection;
import Utils.GeneratePass;
import Utils.Windows;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 * Crea y ejecuta una interfaz de usuario para generar contraseñas seguras.
 * @author  <a href="https://github.com/JSalmon11">Jorge Salmón</a>
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/img/icon.png")));
        stage.setResizable(false);
        stage.setTitle("Generador de contraseñas");
        Text textLongitud = new Text("Longitud:");
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

        Button mostrar = new Button("Mostrar");
        mostrar.setLayoutX(14);
        mostrar.setLayoutY(340);

        Button copiar = new Button("Copiar");
        copiar.setLayoutX(235);
        copiar.setLayoutY(340);

        Pane datos = new Pane();
        datos.getChildren().addAll(longitud, mostrar, copiar, textLongitud, contraseñas);

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

    }

}