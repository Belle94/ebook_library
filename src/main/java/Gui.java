import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;


/**
 * Created by Francesco on 05/09/2015.
 */
public class Gui {

    private BorderPane borderPane;
    private VBox vBox;
    private FlowPane flowPane;
    private Button buttonAdd, buttonEdit, buttonRemove, buttonSave, buttonLoad, buttonPrint;
    private Label labelSearch, labelFunctions, labelTitle, labelFooter;
    private TextField textField;
    private Double prefWidth = 580.0;
    private Double prefHeight = 420.0;
    public Gui(){
        settingLabels();
        settingVbox();
        settingFlow();
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
        flowPane.setColumnHalignment(HPos.LEFT);
        flowPane.setRowValignment(VPos.CENTER);
        flowPane.setPrefSize(prefWidth * 0.74, prefHeight * 0.84);
        flowPane.setStyle("-fx-background-color: #000000; -fx-border-color: #515051");
    }

    public void settingRootElement(){
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #000000;");
        borderPane.setAlignment(labelTitle, Pos.CENTER);
        borderPane.setAlignment(labelFooter, Pos.CENTER);
        borderPane.setTop(labelTitle);
        borderPane.setBottom(labelFooter);
        borderPane.setLeft(vBox);
        borderPane.setCenter(flowPane);
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
        labelFunctions.setFont(Font.font("Arial",FontWeight.BOLD,20));
        labelFunctions.setTextFill(Color.web("#ebc31c"));
        labelFunctions.setTextAlignment(TextAlignment.LEFT);
        labelFunctions.setAlignment(Pos.BOTTOM_LEFT);
        labelFunctions.setPrefSize(prefWidth*0.26, 30.0);

    }
    public ArrayList<Button> settingButton(){
        buttonAdd = new Button("Add");
        buttonEdit = new Button("Edit");
        buttonRemove = new Button("Remove");
        buttonSave = new Button("Save");
        buttonLoad = new Button("Load");
        buttonPrint = new Button("Print");
        ArrayList<Button> buttonsVbox = new ArrayList<>();
        buttonsVbox.add(buttonAdd);
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
}