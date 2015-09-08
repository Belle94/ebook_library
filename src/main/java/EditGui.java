import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

/**
 * Created by Francesco on 08/09/2015.
 * @author Francesco
 */
public class EditGui extends Application{
    private Stage primaryStage;
    private BorderPane borderPane;
    private Book book;
    private ArrayList<Label> leftLabels;
    private ArrayList<Control> rightLabels;


    public EditGui(Book book){
        if (primaryStage != null || book == null)
            return;
        this.book = book;
        primaryStage = new Stage();
        primaryStage.setTitle("Info Book");
        primaryStage.isAlwaysOnTop();
        primaryStage.initStyle(StageStyle.UTILITY);
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage infoStage) throws Exception {
        infoStage = primaryStage;
        settingBorderPane();
        Scene scene = new Scene(borderPane);
        infoStage.setScene(scene);
        infoStage.show();
    }
    public void settingBorderPane(){
        double vBoxWidth = 150;
        double vBoxHeight = 300;

        borderPane = new BorderPane();
        VBox vBoxL = new VBox();
        VBox vBoxD = new VBox();
        HBox hbox = new HBox();
        ImageView imageView = new ImageView();

        imageView.setImage(book.getIcon());
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        vBoxL.setPrefSize(vBoxWidth, vBoxHeight);
        vBoxD.setPrefSize(vBoxWidth,vBoxHeight);
        settingLeftLabels();
        settingRightLabels();

        vBoxL.getChildren().addAll(leftLabels);
        vBoxD.getChildren().addAll(rightLabels);
        hbox.getChildren().add(vBoxL);
        hbox.getChildren().add(vBoxD);
        borderPane.setCenter(hbox);
        borderPane.setLeft(imageView);
    }
    public void settingLeftLabels(){
        Label labelTitle, labelAuthors, labelTotalPage, labelDate, labelPathFile;
        leftLabels = new ArrayList<>();
        labelTitle = new Label("Title: ");
        labelAuthors = new Label("Authors: ");
        labelTotalPage = new Label("Total Page: ");
        labelDate = new Label("Date: ");
        labelPathFile = new Label("Path File: ");
        leftLabels.add(labelTitle);
        leftLabels.add(labelAuthors);
        leftLabels.add(labelTotalPage);
        leftLabels.add(labelDate);
        leftLabels.add(labelPathFile);
        leftLabels.forEach(label -> {
            //set propriety
        });
    }
    public void settingRightLabels(){
        TextField labelTitle, labelAuthors;
        Label labelTotalPage, labelDate, labelPathFile;
        rightLabels = new ArrayList<>();
        labelTitle = new TextField(book.getTitle());
        labelAuthors = new TextField(book.getAuthor());
        labelTotalPage = new Label(String.valueOf(book.getTotalPage()));
        labelDate = new Label(book.getDate());
        labelPathFile = new Label(book.getFilePath());
        rightLabels.add(labelTitle);
        rightLabels.add(labelAuthors);
        rightLabels.add(labelTotalPage);
        rightLabels.add(labelDate);
        rightLabels.add(labelPathFile);
        rightLabels.forEach(label -> {
            //set propriety
        });
    }
}
