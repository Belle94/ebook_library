import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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

    public Gui(Library library){
        this.library = library;
        settingLabels();
        settingVbox();
        settingFlow();
        settingScrollPane();
        settingRootElement();
    }
    public Pane getRootElement(){
        return borderPane;
    }
    public void settingVbox(){
        vBox= new VBox();
        vBox.setPrefSize(prefWidth * 0.26, prefHeight * 0.84);
        vBox.setStyle("-fx-background-color: #272527; -fx-spacing: 10px; -fx-border-width: 1px; -fx-border-color: #505150; -fx-padding: 5px;");
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().add(labelSearch);
        settingTextField();
        vBox.getChildren().add(textField);
        vBox.getChildren().add(labelFunctions);
        vBox.getChildren().addAll(settingButton());
    }
    public void settingScrollPane(){
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(prefWidth * 0.74, prefHeight * 0.84);
        scrollPane.setStyle("-fx-padding: 0; -fx-border-color: #515051; -fx-background-insets: 0; -fx-background: #000000; -fx-hbar-policy: never;");
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(flowPane);
    }
    public void settingTextField(){
        textField = new TextField();
        textField.setPromptText("Title or Keyword");
        textField.setFont(Font.font("Arial", 12));
        textField.setStyle("-fx-background-color: #212021; -fx-border-color: #515051; -fx-border-radius: 3px; -fx-border-width: 2px;");
    }
    public void settingFlow(){
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
    public void addBookToGui(Book book){
        Node node = CreatePane(book);
        flowPane.getChildren().add(node);
    }
    /**
     * Polymorphism in the follow class... the same line of code
     * works for every extension, doesn't mean is pdf or ePub.
     */
    public Node CreatePane(Book book){
        double height = 120, width = 80.0;
        VBox pane = new VBox();
        ImageView imageView = new ImageView();
        Label labelTitleBook = new Label(book.getTitle());
        Label labelAuthorBook = new Label(book.getAuthor());
        labelTitleBook.setPrefSize(width, height * 0.10);
        labelTitleBook.setTextFill(Color.web("#ffffff"));
        labelAuthorBook.setPrefSize(width, height * 0.10);
        labelAuthorBook.setTextFill(Color.web("#414041"));
        imageView.setImage(book.getIcon());
        imageView.setFitHeight(height * 0.70);
        imageView.setFitWidth(width);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        pane.setPrefSize(width, height);
        pane.setMaxSize(width, height);
        pane.getChildren().add(imageView);
        pane.getChildren().add(labelTitleBook);
        pane.getChildren().add(labelAuthorBook);
        return pane;
    }
    public void settingRootElement(){
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #000000;");
        borderPane.setAlignment(labelTitle, Pos.CENTER);
        borderPane.setAlignment(labelFooter, Pos.CENTER);
        borderPane.setTop(labelTitle);
        borderPane.setBottom(labelFooter);
        borderPane.setLeft(vBox);
        borderPane.setCenter(scrollPane);
    }
    public void settingLabels(){
        labelTitle = new Label("Ebook Library");
        labelTitle.setTextFill(Color.web("#ffffff"));
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setPrefHeight(prefHeight * 0.13);
        labelFooter = new Label("Powered by: Belle94");
        labelFooter.setFont(Font.font("System", 12));
        labelFooter.setTextFill(Color.web("#414041"));
        labelFooter.setPrefHeight(prefHeight * 0.03);
        labelFooter.setAlignment(Pos.CENTER);
        labelSearch = new Label("Search");
        labelSearch.setFont(Font.font("Arial",FontWeight.BOLD,20));
        labelSearch.setTextFill(Color.web("#ebc31c"));
        labelSearch.setTextAlignment(TextAlignment.LEFT);
        labelSearch.setAlignment(Pos.BOTTOM_LEFT);
        labelSearch.setPrefSize(prefWidth * 0.26, 30.0);
        labelFunctions = new Label("Functions");
        labelFunctions.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        labelFunctions.setTextFill(Color.web("#ebc31c"));
        labelFunctions.setTextAlignment(TextAlignment.LEFT);
        labelFunctions.setAlignment(Pos.BOTTOM_LEFT);
        labelFunctions.setPrefSize(prefWidth*0.26, 30.0);
    }
    public ArrayList<Button> settingButton(){
        Button buttonAdd, buttonEdit, buttonRemove, buttonSave, buttonLoad, buttonPrint, buttonInfo;
        buttonAdd = new Button("Add");
        buttonInfo = new Button("Info");
        buttonEdit = new Button("Edit");
        buttonRemove = new Button("Remove");
        buttonSave = new Button("Save");
        buttonLoad = new Button("Load");
        buttonPrint = new Button("Print");
        buttonAdd.setOnAction(event -> configAddButton());
        buttonPrint.setOnAction(event -> library.stamp());
        ArrayList<Button> buttonsVbox = new ArrayList<>();
        buttonsVbox.add(buttonAdd);
        buttonsVbox.add(buttonInfo);
        buttonsVbox.add(buttonEdit);
        buttonsVbox.add(buttonRemove);
        buttonsVbox.add(buttonSave);
        buttonsVbox.add(buttonLoad);
        buttonsVbox.add(buttonPrint);
        for (Button b : buttonsVbox){
            b.setFont(Font.font("Arial",FontWeight.BOLD,14));
            b.setAlignment(Pos.TOP_LEFT);
            b.setTextAlignment(TextAlignment.LEFT);
            b.setTextFill(Color.web("#ffffff"));
            b.setStyle("-fx-background-color: #414041; -fx-border-color: #525052; -fx-border-width: 2px; -fx-border-radius: 2px;");
            b.setPrefSize(prefWidth * 0.24, 28.0);
            b.setNodeOrientation(NodeOrientation.INHERIT);
        }
        return buttonsVbox;
    }
    public void configAddButton(){
        FileChooser fs = new FileChooser();
        fs.setTitle("Choose one or more Books");
        fs.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"),
                new FileChooser.ExtensionFilter("EPub Files", "*.epub"),
                new FileChooser.ExtensionFilter("Html Files", "*.html"),
                new FileChooser.ExtensionFilter("Mobi Files", "*.mobi"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        List<File> listFiles = fs.showOpenMultipleDialog(new Stage());
        listFiles.stream().filter(f -> f != null).forEach(f -> {
            Book book = null;
            if (f.getAbsolutePath().endsWith(".pdf") || f.getAbsolutePath().endsWith(".PDF")) {
                book = new Pdf(f.getAbsolutePath());
            } else {
                book = new Book(f.getAbsolutePath()) {
                    @Override
                    public String getAuthor() {
                        return "Unknown";
                    }
                    @Override
                    public String getTitle() {
                        return "Unknown";
                    }
                    @Override
                    public Date getDate() {
                        return null;
                    }
                    @Override
                    public int getTotalPage() {
                        return 0;
                    }
                };
            }
            library.addBook(book);
            addBookToGui(book);
        });
    }
}