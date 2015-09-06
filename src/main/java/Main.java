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
        Gui gui = new Gui();

        settingLibrary();
        gui.refreshLibrary(library.getCollection());
        library.stamp();
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

    public void settingLibrary(){
        library = new Library();
        library.addBook(new Pdf("O:\\Documents\\Books\\HTML And CSS - Design And Build Websites.pdf"));
        library.addBook(new Pdf("O:\\Documents\\Books\\Bruce Eckel Thinking In Java 4th Edition.pdf"));
    }

}
