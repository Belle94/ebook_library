import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public class Library implements Serializable{
    private Vector<Book> collection;

    public Library(){
        collection = new Vector<>();
    }
    public void addBook(Book book){
        collection.add(book);
    }
    public void removeBook(Book book){
        if (collection.contains(book))
            collection.remove(book);
    }
    public Vector<Book> getCollection(){
        return collection;
    }

    public void saveLibrary(File filePath)throws IOException{
        FileOutputStream f = new FileOutputStream(filePath.getAbsolutePath());
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(collection);
        out.flush();
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void loadLibrary(File filePath)throws Exception{
        collection.clear();
        FileInputStream f = new FileInputStream(filePath.getAbsolutePath());
        ObjectInputStream input = new ObjectInputStream(f);
        collection = (Vector<Book>) input.readObject();
        input.close();
    }
    /**
     * Print the complete collection in standard output.
     */
    public void stamp(){
        int i = 1;
        System.out.println("###### EBOOK LIBRARY ######");
        for (Book b:collection){
            System.out.println("\n# Book n."+i++ +b.toString());
        }
    }

    public void print() {
        //creating table view for printing
        if(collection.size() == 0)
            return;
        ObservableList<Book> observableList = FXCollections.observableList(collection);
        TableView<Book> node = new TableView<>();
        node.setItems(observableList);
        TableColumn<Book,String> firstCol = new TableColumn<>("Title");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book,String> secondCol = new TableColumn<>("Author");
        secondCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book,String> thirdCol = new TableColumn<>("Page");
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("totalPage"));
        TableColumn<Book,String> fourthCol = new TableColumn<>("Date");
        fourthCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Book,String> fifthCol = new TableColumn<>("Path");
        fifthCol.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        node.getColumns().setAll(firstCol, secondCol, thirdCol, fourthCol,fifthCol);
        node.setPrefSize(595,842);
        node.setVisible(true);
        Stage stage = new Stage();
        Scene scene = new Scene(node);
        stage.setScene(scene);
        stage.showAndWait();
        //print table
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(node))
            printerJob.endJob();
    }
}