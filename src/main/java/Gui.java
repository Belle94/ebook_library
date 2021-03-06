import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Classe principale per la grafica
 * @author Francesco
 * Created on 05/09/2015.
 */
public class Gui {
    private BorderPane borderPane;
    private VBox vBox;
    private FlowPane flowPane;
    private Label labelSearch, labelFunctions, labelTitle, labelFooter;
    private TextField textField;
    private ScrollPane scrollPane;
    private double prefWidth = 580.0;
    private double prefHeight = 420.0;
    private Library library;
    private Book prcBook= null;
    private boolean infoBookWindow = false, viewerWindow = false;

    /**
     * Costruttore, crea una nuova libreria, richiamando i metodi per la
     * configurazione dell' interfaccia.
     */
    public Gui() {
        library = new Library();
        settingLabels();
        settingVbox();
        settingFlow();
        settingScrollPane();
        settingRootElement();
    }

    /**
     * @return Pannello principale
     */
    public Pane getRootElement() {
        return borderPane;
    }

    /**
     * imposta e configura il pannello di sinistra con i bottoni,label e textfield
     */
    public void settingVbox() {
        vBox = new VBox();
        vBox.setPrefSize(prefWidth * 0.26, prefHeight * 0.84);
        vBox.setStyle("-fx-background-color: #272527; -fx-spacing: 10px; -fx-border-width: 1px; -fx-border-color: #505150; -fx-padding: 5px;");
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().add(labelSearch);
        settingTextField();
        vBox.getChildren().add(textField);
        vBox.getChildren().add(labelFunctions);
        vBox.getChildren().addAll(settingButton());
        vBox.setOnMouseClicked(event -> updateBookSelected(null));
    }

    /**
     * imposta lo scroll pane per il pannello(FlowPane) principale dei libri
     */
    public void settingScrollPane() {
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(prefWidth * 0.74, prefHeight * 0.84);
        scrollPane.setStyle("-fx-padding: 0; -fx-border-color: #515051; -fx-background-insets: 0; -fx-background: #000000; -fx-hbar-policy: never;");
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(flowPane);
    }

    /**
     * configura i colori e la grafica della barra di ricerca
     */
    public void settingTextField() {
        textField = new TextField();
        textField.setPromptText("Title or Author");
        textField.setFont(Font.font("Arial", 12));
        textField.setStyle("-fx-background-color: #212021; -fx-border-color: #515051; -fx-border-radius: 3px; -fx-border-width: 2px; -fx-text-fill: #ffffff");
        textField.setOnKeyReleased(this::settingSearchText);
    }

    /**
     * configura la grafica del pannello che contiene i libri
     */
    public void settingFlow() {
        flowPane = new FlowPane();
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setNodeOrientation(NodeOrientation.INHERIT);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setColumnHalignment(HPos.CENTER);
        flowPane.setRowValignment(VPos.CENTER);
        flowPane.setVgap(20.0);
        flowPane.setHgap(20.0);
        flowPane.setStyle("-fx-background-color: #000000; -fx-padding: 15px;");
    }

    /**
     * visualizza la finestra di Informazione e Modifiche dei libri
     */
    public void showInfoBookWindow(){
        if (prcBook != null && !infoBookWindow) {
            infoBookWindow = true;
            new EditGui(prcBook,this).getStage().showAndWait();
            infoBookWindow = false;
        }
    }

    /**
     * aggiunge un libro all' interfaccia grafica
     * @param book libro
     */
    public void addBookToGui(Book book) {
        book.setPane(CreatePane(book));
        flowPane.getChildren().add(book.getPane());
    }

    /**
     * Rimuove un libro all' interfaccia grafica
     * @param book libro
     */
    public void removeBookToGui(Book book){
        if(flowPane.getChildren().contains(book.getPane()))
            flowPane.getChildren().remove(book.getPane());
    }

    /**
     * Aggiunge un vettore di libri all' interfaccia grafica
     * @param vectorBook vettore di libri
     */
    public void addBooksToGui(Vector<Book> vectorBook) {
        vectorBook.forEach(this::addBookToGui);
    }

    /**
     * pulisce l' interfaccia grafica svuotandola dai libri
     */
    public void clearGui() {
        flowPane.getChildren().clear();
    }

    /**
     * Crea il pannello per la rappresentazione del libro il proprio titolo, autore
     * e immagine
     * @param book the book will have its own Pan
     * @return the Pane witch its propriety
     */
    public Pane CreatePane(Book book) {
        double transx = 5;
        double height = book.getIcon().getHeight();
        double width = book.getIcon().getWidth() + transx;
        VBox pane = new VBox();
        ImageView imageView = new ImageView();
        Label labelTitleBook = new Label(book.getTitle());
        Label labelAuthorBook = new Label(book.getAuthor());
        labelTitleBook.setPrefSize(width, height * 0.10);
        labelTitleBook.setTextFill(Color.web("#ffffff"));
        labelTitleBook.setTranslateX(transx);
        labelTitleBook.setMaxWidth(width - transx);
        labelAuthorBook.setPrefSize(width, height * 0.10);
        labelAuthorBook.setTextFill(Color.web("#414041"));
        labelAuthorBook.setTranslateX(transx);
        labelAuthorBook.setMaxWidth(width - transx);
        imageView.setImage(book.getIcon());
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        pane.setPrefSize(width, height + height * 0.20);
        pane.getChildren().add(imageView);
        pane.getChildren().add(labelTitleBook);
        pane.getChildren().add(labelAuthorBook);
        pane.setOnMouseClicked(event -> updateBookSelected(book));
        return pane;
    }

    /**
     * Aggiorna il libro selezionato
     * @param slcBook libro selezionato
     */
    public void updateBookSelected(Book slcBook) {
        if (prcBook != null && slcBook != null && prcBook != slcBook) {
            prcBook.getPane().setStyle("-fx-background-color: transparent;");
            slcBook.getPane().setStyle("-fx-background-color: rgba(75, 74, 75, 0.3);");
            prcBook = slcBook;
        } else if (slcBook != null && prcBook == slcBook) {
            prcBook.getPane().setStyle("-fx-background-color: transparent;");
            prcBook = null;
        } else if (slcBook == null && prcBook != null) {
            prcBook.getPane().setStyle("-fx-background-color: transparent;");
            prcBook = null;
        }else  if (slcBook == null){
            prcBook = null;
        } else {
            slcBook.getPane().setStyle("-fx-background-color: rgba(75, 74, 75, 0.3);");
            prcBook = slcBook;
        }
    }

    /**
     * configura il pannello generale
     */
    public void settingRootElement() {
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #000000;");
        BorderPane.setAlignment(labelTitle, Pos.CENTER);
        BorderPane.setAlignment(labelFooter, Pos.CENTER);
        borderPane.setTop(labelTitle);
        borderPane.setBottom(labelFooter);
        borderPane.setLeft(vBox);
        borderPane.setCenter(scrollPane);
        borderPane.setOnKeyReleased(event -> { if (event.getCode() == KeyCode.ENTER)showInfoBookWindow();});
    }

    /**
     * configura le labels generali dell' interfaccia
     */
    public void settingLabels() {
        labelTitle = new Label("Ebook Library");
        labelTitle.setTextFill(Color.web("#ffffff"));
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setPrefHeight(prefHeight * 0.13);
        labelTitle.setOnMouseClicked(event -> updateBookSelected(null));
        labelFooter = new Label("Powered by: Belle94");
        labelFooter.setFont(Font.font("System", 12));
        labelFooter.setTextFill(Color.web("#414041"));
        labelFooter.setPrefHeight(prefHeight * 0.03);
        labelFooter.setAlignment(Pos.CENTER);
        labelFooter.setOnMouseClicked(event -> updateBookSelected(null));
        labelSearch = new Label("Search");
        labelSearch.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        labelSearch.setTextFill(Color.web("#ebc31c"));
        labelSearch.setTextAlignment(TextAlignment.LEFT);
        labelSearch.setAlignment(Pos.BOTTOM_LEFT);
        labelSearch.setPrefSize(prefWidth * 0.26, 30.0);
        labelSearch.setOnMouseClicked(event -> updateBookSelected(null));
        labelFunctions = new Label("Functions");
        labelFunctions.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        labelFunctions.setTextFill(Color.web("#ebc31c"));
        labelFunctions.setTextAlignment(TextAlignment.LEFT);
        labelFunctions.setAlignment(Pos.BOTTOM_LEFT);
        labelFunctions.setPrefSize(prefWidth * 0.26, 30.0);
        labelFunctions.setOnMouseClicked(event -> updateBookSelected(null));
    }

    /**
     * configura i bottoni
     * @return vettore di bottoni del pannello di sinistra dell' interfaccia grafica
     */
    public ArrayList<Button> settingButton() {
        Button buttonAdd, buttonEdit, buttonRemove, buttonSave, buttonLoad, buttonPrint, buttonRead, buttonExternalRead;
        buttonAdd = new Button("Add");
        buttonEdit = new Button("Edit");
        buttonRemove = new Button("Remove");
        buttonSave = new Button("Save");
        buttonLoad = new Button("Load");
        buttonPrint = new Button("Print");
        buttonRead = new Button("Read");
        buttonExternalRead = new Button("Ext. Read");
        buttonAdd.setOnAction(event -> settingAddButton());
        buttonEdit.setOnAction(event -> showInfoBookWindow());
        buttonRemove.setOnAction(event -> settingRemoveBook());
        buttonSave.setOnAction(event -> settingSaveButton());
        buttonLoad.setOnAction(event -> settingLoadButton());
        buttonPrint.setOnAction(event -> library.print());
        buttonRead.setOnAction(event -> settingReadButton());
        buttonExternalRead.setOnAction(event1 -> settingExternalReadButton());
        ArrayList<Button> buttonsVbox = new ArrayList<>();
        buttonsVbox.add(buttonAdd);
        buttonsVbox.add(buttonEdit);
        buttonsVbox.add(buttonRemove);
        buttonsVbox.add(buttonSave);
        buttonsVbox.add(buttonLoad);
        buttonsVbox.add(buttonRead);
        buttonsVbox.add(buttonExternalRead);
        buttonsVbox.add(buttonPrint);
        for (Button b : buttonsVbox) {
            b.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            b.setAlignment(Pos.TOP_LEFT);
            b.setTextAlignment(TextAlignment.LEFT);
            b.setTextFill(Color.web("#ffffff"));
            b.setStyle("-fx-background-color: #414041; -fx-border-color: #525052; -fx-border-width: 2px; -fx-border-radius: 2px;");
            b.setPrefSize(prefWidth * 0.24, 28.0);
            b.setNodeOrientation(NodeOrientation.INHERIT);
            b.setOnMouseEntered(event -> b.setStyle("-fx-background-color: #525052; -fx-border-color: #414041; -fx-border-width: 2px; -fx-border-radius: 2px;"));
            b.setOnMouseExited(event -> b.setStyle("-fx-background-color: #414041; -fx-border-color: #525052; -fx-border-width: 2px; -fx-border-radius: 2px;"));
        }
        return buttonsVbox;
    }

    /**
     * imposta la funzione del bottone Ext Read
     */
    public void settingExternalReadButton(){
        if (prcBook == null)
            return;
        FileChooser fs = new FileChooser();
        fs.setTitle("Set external reader");
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters().add(new FileChooser.ExtensionFilter("Exe Files", "*.exe"));
        fs.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file =fs.showOpenDialog(new Stage());
        if (file != null){
            try{
                Runtime.getRuntime().exec(file.getAbsolutePath() +" " +prcBook.getFilePath());
            }catch (IOException e){
                System.err.println("[Error] External Program didn't run");
            }
        }
    }

    /**
     * imposta la funzione del bottone Read
     */
    public void settingReadButton() {
        if (!viewerWindow) {
            if (prcBook == null)
                return;
            if (prcBook instanceof Pdf) {
                viewerWindow = true;
                ((Pdf) prcBook).showPdf((Pdf)prcBook);
                viewerWindow = false;
            } else if (prcBook instanceof EPub) {
                ((EPub) prcBook).showReader();
            }
        }
    }

    /**
     * imposta la funzione del bottone Remove
     */
    public void settingRemoveBook(){
        if (prcBook != null){
            library.removeBook(prcBook);
            removeBookToGui(prcBook);
        }
    }
    /**
     * imposta la funzione del bottone Save
     */
    public void settingSaveButton() {
        FileChooser fs = new FileChooser();
        fs.setTitle("Save library");
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters().add(new FileChooser.ExtensionFilter("Library Files", "*.db"));
        File file = fs.showSaveDialog(new Stage());
        if (file != null) {
            try {
                library.saveLibrary(file);
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Lirary not saved", ButtonType.OK).showAndWait();
            }
        }
    }

    /**
     * imposta la funzione del bottone Load
     */
    public void settingLoadButton() {
        FileChooser fs = new FileChooser();
        fs.setTitle("Load library");
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters().add(new FileChooser.ExtensionFilter("Library Files", "*.db"));
        File file = fs.showOpenDialog(new Stage());
        if (file != null){
            try {
                library.loadLibrary(file);
                clearGui();
                addBooksToGui(library.getCollection());
            }catch (SQLException | ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR, "Lirary not loaded",ButtonType.OK).showAndWait();
            }
        }
    }

    /**
     * imposta la funzione del bottone Add
     */
    public void settingAddButton() {
        FileChooser fs = new FileChooser();
        fs.setTitle("Choose one or more Books");
        fs.setInitialDirectory(new File(System.getProperty("user.home")));
        fs.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("EPub Files", "*.epub"),
                new FileChooser.ExtensionFilter("Html Files", "*.html"),
                new FileChooser.ExtensionFilter("Mobi Files", "*.mobi"),
                new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"));
        List<File> listFiles = fs.showOpenMultipleDialog(new Stage());
        if (listFiles != null)
            listFiles.stream().filter(f -> f != null).forEach(f -> {
                Book book = Book.getExtension(f);
                library.addBook(book);
                addBookToGui(book);
            });
    }

    /**
     * imposta la funzione della barra di ricerca
     */
    public void settingSearchText(KeyEvent event) {
        if (!library.getCollection().isEmpty()) {
            if (!textField.getText().equals("")) {
                updateBookSelected(null);
                clearGui();
                boolean firstBookFound = true;
                for (Book book : library.getCollection()) {
                    if (book.getAuthor().toLowerCase().contains(textField.getText().toLowerCase())
                            || book.getTitle().toLowerCase().contains(textField.getText().toLowerCase())) {
                        addBookToGui(book);
                        if (firstBookFound) {
                            updateBookSelected(book);
                            firstBookFound = false;
                        }
                    }
                }
                if (KeyCode.ENTER == event.getCode())
                    showInfoBookWindow();
            } else {
                clearGui();
                addBooksToGui(library.getCollection());
            }
        }
    }
}