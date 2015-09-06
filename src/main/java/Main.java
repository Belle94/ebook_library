import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Francesco
 */
public class Main extends Application {
    Library library;

    @Override
    public void start(Stage stage) throws Exception {
        library = new Library();
        Gui gui = new Gui(library);
        stage.setTitle("Ebook Library");
        stage.setScene(new Scene(gui.getRootElement()));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
