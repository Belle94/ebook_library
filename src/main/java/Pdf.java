import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.IOException;
import java.util.Date;
/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public class Pdf extends Book {
    private PDDocument doc;
    private PDDocumentInformation info;
    public Pdf(String pdfPath) {
        super(pdfPath);
        try {
            doc = PDDocument.load(pdfPath);
            info = doc.getDocumentInformation();
        }catch (IOException e){
            System.out.println("\n[ERROR] Message: "+e.getMessage());
            System.out.println("\n[ERROR] Cause: "+e.getCause());
        }
    }

    @Override
    String getAuthor() {
        return info.getAuthor();
    }

    @Override
    String getTitle() {
        return info.getTitle();
    }

    @Override
    Date getDate() {
        return null;
    }

    @Override
    int getTotalPage() {
        return doc.getNumberOfPages();
    }
}
