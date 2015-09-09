import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
public class EditGui extends Application {
    private BorderPane borderPane;
    private Book book;
    private double width = 450, height=210;
    private ArrayList<Label> leftLabels;
    private ArrayList<Control> rightLabels;

    public EditGui(Book book) {
        if (book == null)
            return;
        this.book = book;
        try {
            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Info Book");
        primaryStage.isAlwaysOnTop();
        primaryStage.initStyle(StageStyle.UTILITY);
        settingBorderPane();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void settingBorderPane() {
        double vboxLWidth = width *0.20;
        double vboxRWidth = width *0.60;
        borderPane = new BorderPane();
        VBox vBoxL = new VBox(10);
        VBox vBoxR = new VBox(10);
        HBox hbox = new HBox();
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
        hbox.getChildren().add(vBoxL);
        hbox.getChildren().add(vBoxR);
        hbox.setPrefSize(width, height);
        borderPane.setAlignment(hbox, Pos.CENTER);
        borderPane.setAlignment(vBoxL, Pos.CENTER_LEFT);
        borderPane.setAlignment(vBoxR, Pos.CENTER_LEFT);
        borderPane.setStyle("-fx-background-color: #000000");
        borderPane.setPrefSize(width, height);
        borderPane.setCenter(hbox);
        borderPane.setLeft(imageView);
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
        rightLabels.add(new TextField(book.getTitle()));
        rightLabels.add(new TextField(book.getAuthor()));
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
}