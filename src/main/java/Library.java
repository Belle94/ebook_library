import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * Classe che definisce la libreria.
 * @author Francesco
 * Created on 06/09/2015.
 */
public class Library {
    private Vector<Book> collection;
    private JdbcConnectionSource jdbcConnectionSource;
    private Dao<DatabaseBook, String> dbBookDao;

    /**
     * Costruttore, crea un nuovo vettore di libri
     */
    public Library(){
        collection = new Vector<>();
    }

    /**
     * Aggiunge un libro alla libreria
     * @param book libro
     */
    public void addBook(Book book){
        collection.add(book);
    }

    /**
     * rimuove un libro dalla libreria
     * @param book libro
     */
    public void removeBook(Book book){
        if (collection.contains(book))
            collection.remove(book);
    }

    /**
     * @return libreria di libri in uso
     */
    public Vector<Book> getCollection(){
        return collection;
    }

    /**
     * carica la libreria dal database
     * @param filePath percorso dove caricare la libreria
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void loadLibrary(File filePath) throws SQLException, ClassNotFoundException {
        openConnection(filePath.getAbsolutePath());
        List<DatabaseBook> dbBooksList = dbBookDao.queryForAll();
        collection = DatabaseBook.convertDbBookToBook(dbBooksList);
        closeConnection();
    }

    /**
     * salva la libreria nel database
     * @param filePath percorso dove salvare la libreria
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveLibrary(File filePath) throws SQLException, ClassNotFoundException {
        if(filePath.exists())
            filePath.delete();
        openConnection(filePath.getAbsolutePath());
        for(Book book: collection) {
            dbBookDao.createIfNotExists(new DatabaseBook(book));
        }
        closeConnection();
    }

    /**
     * Open a connection with the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void openConnection(String databaseUrl) throws SQLException, ClassNotFoundException {
        final String prefixString = "jdbc:sqlite:";
        Class.forName("org.sqlite.JDBC");
        jdbcConnectionSource = new JdbcConnectionSource(prefixString+databaseUrl);
        dbBookDao = DaoManager.createDao(jdbcConnectionSource, DatabaseBook.class);
        TableUtils.createTableIfNotExists(jdbcConnectionSource, DatabaseBook.class);
    }

    /**
     * Closes the connection with the database
     */
    public void closeConnection() {
        jdbcConnectionSource.closeQuietly();
    }

    /**
     * stampa la libreria, per stampare la libreria mi appoggio a una TableView,
     * in questo modo creo una anteprima di stampa, se conferma la stampa attraverso
     * il bottone allora procedo altrimenti non stampo.
     */
    public void print() {
        double width = 595, height = 842;
        //creating table view for printing
        if(collection.size() == 0)
            return;
        ObservableList<Book> observableList = FXCollections.observableList(collection);
        TableView<Book> tableView = new TableView<>();
        tableView.setItems(observableList);
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
        tableView.getColumns().setAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol);
        tableView.setPrefSize(width, height);
        tableView.setMaxSize(width, height);
        Button print = new Button("Print");
        Button cancel = new Button("cancel");
        VBox mainPane = new VBox();
        HBox footerPane = new HBox(5);
        mainPane.getChildren().addAll(tableView, footerPane);
        footerPane.getChildren().addAll(print, cancel);
        footerPane.setAlignment(Pos.BOTTOM_CENTER);
        print.setAlignment(Pos.CENTER);
        cancel.setAlignment(Pos.CENTER);
        tableView.setVisible(true);
        Stage stage = new Stage();
        stage.setTitle("Print preview");
        Scene scene = new Scene(mainPane);
        stage.setMaxWidth(width);
        stage.setMaxHeight(height + footerPane.getHeight());
        stage.setScene(scene);
        //print table
        print.setOnAction(event -> {
            stage.close();
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tableView))
                try {
                    printerJob.endJob();
                } catch (NullPointerException e) {
                    new Alert(Alert.AlertType.ERROR, "Printing error", ButtonType.CLOSE).showAndWait();
                }
            else
                new Alert(Alert.AlertType.ERROR, "No Printer Found", ButtonType.CLOSE).showAndWait();
        });
        cancel.setOnAction(event -> stage.close());
            stage.show();
        }
    }