import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Francesco
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Gui gui = new Gui();
        stage.setTitle("Ebook Library");
        Scene scene = new Scene(gui.getRootElement());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
