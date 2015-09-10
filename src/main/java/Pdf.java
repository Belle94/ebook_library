import javafx.scene.image.Image;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public class Pdf extends Book {
    private Image icon;
    private String author, title;
    private String date;
    private int totalPage;

    /**
     * @param filePdf pdf file
     */
    public Pdf(File filePdf) {
        super(filePdf);
        try {
            icon = new Image("pdf.png");
            PDDocument doc = PDDocument.load(filePdf);
            PDDocumentInformation info = doc.getDocumentInformation();
            author = info.getAuthor();
            title = info.getTitle();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            date = format.format(info.getCreationDate().getTime());
            totalPage = doc.getNumberOfPages();
            if (info.getTitle() == null || Objects.equals(info.getTitle(), "")) {
                title = "no-name";
            }
            if (info.getAuthor() == null || Objects.equals(info.getAuthor(), "")) {
                author = "no-name";
            }
            doc.close();
        } catch (IOException e) {
            System.out.println("[ERROR] Pdf not added");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
        }
    }
    @Override
    public String getAuthor() {
        return author;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getDate() {
        return date;
    }
    @Override
    public Image getIcon() {
        return icon;
    }
    @Override
    public int getTotalPage() {
        return totalPage;
    }
    @Override
    public void setTitle(String title){
        try {
            PDDocument doc = PDDocument.load(getFilePath());
            PDDocumentInformation info = doc.getDocumentInformation();
            info.setTitle(title);
            doc.setDocumentInformation(info);
            this.title = title;
            doc.save(new File(getFilePath()));
            doc.close();
        }catch (IOException | COSVisitorException e){
            System.out.println("[ERROR] Pdf not changed");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
        }
    }
    @Override
    public void setAuthor(String author){
        try {
            PDDocument doc = PDDocument.load(getFilePath());
            PDDocumentInformation info = doc.getDocumentInformation();
            info.setAuthor(author);
            doc.setDocumentInformation(info);
            this.author = author;
            doc.save(new File(getFilePath()));
            doc.close();
        }catch (IOException | COSVisitorException e){
            System.out.println("[ERROR] Pdf not changed");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
        }
    }
}
