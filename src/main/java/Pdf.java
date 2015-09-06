import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public class Pdf extends Book {
    private PDDocument doc;
    private PDDocumentInformation info;
    private Image icon;

    /**
     * @param pdfPath an absolute or relative string location where is located the pdf file
     */
    public Pdf(String pdfPath) {
        super(pdfPath);
        try {
            icon = new Image("pdf.png");
            doc = PDDocument.load(new File(pdfPath));
            info = doc.getDocumentInformation();
        }catch (IOException e){
            System.out.println("\n[ERROR] Message: "+e.getMessage());
            System.out.println("\n[ERROR] Cause: "+e.getCause());
        }
    }

    @Override
    public String getAuthor() {
        if (info.getAuthor() == null || Objects.equals(info.getAuthor(),"")){
            return "Unknown";
        }
        return info.getAuthor();
    }

    @Override
    public String getTitle() {
        if (info.getTitle() == null || Objects.equals(info.getTitle(),"")){
            return "Unknown";
        }
        return info.getTitle();
    }

    @Override
    public Date getDate() {
        try {
            return info.getCreationDate().getTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*  quando la stampo...
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String data = format.format(Book.getDate());
        */
        return null;
    }

    @Override
    public Image getIcon() {
        return icon;
    }

    @Override
    public int getTotalPage() {
        return doc.getNumberOfPages();
    }
}
