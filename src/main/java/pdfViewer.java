import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * pdfViewer class. classe che implementa il visualizzatore pdf
 * @author Francesco
 * Created on 12/09/2015.
 */
public class pdfViewer {
    private Pdf book;
    private Stage primaryStage;
    private List pdPagesList;
    private int page, firstPage, lastPage;
    private PDDocument doc;
    private PDFPagePanel pagePanel;
    private SwingNode pagePanelFx;
    private Label pageLabel;
    private BorderPane pdfBox;

    /**
     * Costruttore
     * @param book il libro da leggere
     */
    public pdfViewer(Pdf book){
        this.book = book;
        setPrimaryStage();
        lastPage = book.getTotalPage() -1;
        firstPage = 0;
        page = firstPage;
        try {
            doc = PDDocument.load(book.getFile());
            pdPagesList = doc.getDocumentCatalog().getAllPages();
            pagePanel = new PDFPagePanel();
            pagePanelFx = new SwingNode();
            pagePanel.setPage((PDPage) pdPagesList.get(page));
            SwingUtilities.invokeLater(() -> pagePanelFx.setContent(pagePanel));
            settingLayout();
        }catch (IOException e){
            System.err.println("Unable to create pdfViewer" + e.getMessage());
        }
    }

    /**
     * @return ritorna il pdf-viewer
     */
    public Stage getStage(){return primaryStage;}

    /**
     * Configura le impostazioni dello Stage per il pdf-viewer
     */
    public void setPrimaryStage(){
        primaryStage = new Stage();
        primaryStage.setTitle("Pdf Reader");
        primaryStage.setOnCloseRequest(event -> {
            try{
                doc.close();
            }catch (IOException e){
                System.err.println("[Error] can't close document");
            }
        });
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case KP_UP:
                case UP:
                case KP_LEFT:
                case LEFT: {
                    readEbook(page - 1);
                    break;
                }
                case KP_DOWN:
                case DOWN:
                case KP_RIGHT:
                case RIGHT: {
                    readEbook(page + 1);
                    break;
                }
                case HOME: {
                    readEbook(firstPage);
                    break;
                }
                case END: {
                    readEbook(lastPage);
                }
            }
        });
    }

    /**
     * visualizza la pagina presa in input se non � fuori range
     * @param page pagina da leggere
     */
    public void readEbook(int page) {
        if (page <= lastPage && page >= firstPage){
            this.page = page;
            pagePanel.setPage((PDPage) pdPagesList.get(page));
            repaint();
            updateTextLabel();
        }
    }

    /**
     * configura il layout del pdf-viewer
     */
    public void settingLayout(){
        pdfBox = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();
        VBox containerTop = new VBox();
        HBox hBox = new HBox();
        Label titleLabel = new Label();
        Button nextButton = new Button("Next"), previousButton = new Button("Previous");
        pageLabel = new Label();
        containerTop.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.setAlignment(Pos.CENTER);
        titleLabel.setText(book.getTitle());
        titleLabel.setStyle("-fx-font: 24 'Arial'; -fx-alignment: center");
        updateTextLabel();
        nextButton.setMinWidth(70);
        previousButton.setMinWidth(70);
        nextButton.setOnAction(event -> readEbook(page+1));
        previousButton.setOnAction(event -> readEbook(page-1));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(pdfBox);
        pdfBox.setCenter(pagePanelFx);
        hBox.getChildren().addAll(previousButton, pageLabel, nextButton);
        containerTop.getChildren().addAll(titleLabel, hBox);
        container.getChildren().addAll(containerTop, scrollPane);
        repaint();
        primaryStage.setScene(new Scene(container));
    }

    /**
     *Aggiorna la label con la pagina corrente
     */
    public void updateTextLabel(){
        pageLabel.setText("Page: "+ (page+1));
    }

    public void repaint(){
        int width = pagePanel.getWidth() ;
        int height = pagePanel.getHeight() ;
        pdfBox.setPrefSize(width, height);
        pagePanel.repaint();
    }
}