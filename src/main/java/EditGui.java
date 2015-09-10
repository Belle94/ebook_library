import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


/**
 * Created by Francesco on 08/09/2015.
 * @author Francesco
 */
public class EditGui {
    private BorderPane borderPane;
    private Book book;
    private double width = 450, height=210;
    private ArrayList<Label> leftLabels;
    private ArrayList<Control> rightLabels;
    private Stage primaryStage;
    private Button save;
    private Button cancel;
    private TextField titleField, authorField;
    private Gui gui;

    public EditGui(Book book, Gui gui) {
        if (book == null)
            return;
        this.book = book;
        this.gui = gui;
        primaryStage = new Stage();
        primaryStage.setTitle("Info Book");
        primaryStage.isAlwaysOnTop();
        primaryStage.initStyle(StageStyle.UTILITY);
        settingBorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
    }

    public Stage getStage(){return primaryStage;}

    public void settingBorderPane() {
        double vboxLWidth = width *0.20;
        double vboxRWidth = width *0.60;
        borderPane = new BorderPane();
        cancel = new Button("Cancel");
        save = new Button("Save");
        settingButton();
        VBox vBoxL = new VBox();
        VBox vBoxR = new VBox();
        HBox hbox = new HBox();
        HBox footerPane = new HBox(5);
        ImageView imageView = new ImageView();
        imageView.setImage(book.getIcon());
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        settingLeftLabels();
        settingRightLabels();
        vBoxR.setPrefSize(vboxRWidth, height);
        vBoxL.setPrefSize(vboxLWidth, height);
        vBoxL.getChildren().addAll(leftLabels);
        vBoxR.getChildren().addAll(rightLabels);
        vBoxL.setSpacing(7);
        vBoxR.setSpacing(5);
        footerPane.getChildren().add(save);
        footerPane.getChildren().add(cancel);
        footerPane.setMaxSize(102,30);
        hbox.getChildren().add(vBoxL);
        hbox.getChildren().add(vBoxR);
        hbox.setPrefSize(width, height);
        BorderPane.setAlignment(imageView, Pos.TOP_LEFT);
        BorderPane.setAlignment(hbox, Pos.CENTER);
        BorderPane.setAlignment(footerPane, Pos.BOTTOM_RIGHT);
        borderPane.setPadding(new Insets(5.0));
        borderPane.setStyle("-fx-background-color: #000000");
        borderPane.setPrefSize(width, height);
        borderPane.setCenter(hbox);
        borderPane.setLeft(imageView);
        borderPane.setBottom(footerPane);
    }

    public void settingLeftLabels() {
        leftLabels = new ArrayList<>();
        leftLabels.add(new Label("Title: "));
        leftLabels.add(new Label("Authors: "));
        leftLabels.add(new Label("Total Page: "));
        leftLabels.add(new Label("Date: "));
        leftLabels.add(new Label("Path file: "));
        leftLabels.forEach(label -> {
            label.setStyle("-fx-text-fill: #fff; -fx-padding: 5,0,5,0;");
        });
    }

    public void settingRightLabels() {
        rightLabels = new ArrayList<>();
        titleField = new TextField(book.getTitle());
        authorField = new TextField(book.getAuthor());
        rightLabels.add(titleField);
        rightLabels.add(authorField);
        String string = (book.getTotalPage() == 0) ? " (ePub pages not implement)" : "";
        rightLabels.add(new Label(String.valueOf(book.getTotalPage()) + string));
        rightLabels.add(new Label(book.getDate()));
        rightLabels.add(new Label(book.getFilePath()));
        for (Control label : rightLabels) {
            if (label instanceof Label) {
                label.setStyle("-fx-text-fill: #414041; ");
                ((Label) label).setWrapText(true);
            }
            if (label instanceof TextField) {
                ((TextField) label).setFont(Font.font("Arial", 12));
                label.setStyle("-fx-background-color: #212021; -fx-border-color: #515051; -fx-border-radius: 3px; -fx-border-width: 2px; -fx-text-fill: #ffffff");
            }
            label.setPadding(new Insets(5, 0, 5, 0));
        }
    }

    public void settingButton(){
        cancel.setOnAction(event -> primaryStage.close());
        save.setOnAction(event -> {
            book.setTitle(titleField.getText());
            book.setAuthor(authorField.getText());
            gui.removeBookToGui(book);
            gui.addBookToGui(book);
            primaryStage.close();
        });
    }
}