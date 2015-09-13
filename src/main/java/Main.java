import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Main class
 * @author Francesco
 */
public class Main extends Application {
    /** the standard method called by main in javafx applications
     * @param stage the Stage of application
     */
    @Override
    public void start(Stage stage) throws Exception {
        Gui gui = new Gui();
        stage.setTitle("Ebook Library");
        Scene scene = new Scene(gui.getRootElement());
        stage.setScene(scene);
        stage.show();
    }
/**
 * Main function
 * @param args the command line arguments
 */
    public static void main(String[] args) {
        launch(args);
    }
}
