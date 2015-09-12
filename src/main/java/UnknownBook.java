import javafx.scene.image.Image;
import java.io.File;

/**
 * @author Francesco
 * Created by Francesco on 07/09/2015.
 */
public class UnknownBook extends Book {
    private String title, author, date;
    private Image image;
    /**
     *
     * @param file
     */
    public UnknownBook(File file){
        super(file);
    }

    /**
     *
     * @param file
     * @param defaultString
     * @param defaultDate
     */
    public UnknownBook(File file, String defaultString, String defaultDate){
        this(file);
        image = new Image("book.png");
        setTitle(defaultString);
        setAuthor(defaultString);
        setDate(defaultDate);
    }

    public UnknownBook(File filePdf, String title, String author, String data){
        super(filePdf);
        image = new Image("book.png");
        this.title = title;
        this.author = author;
        this.date = data;
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
    public int getTotalPage() {
        return 0;
    }
    @Override
    public Image getIcon(){return image;}
    @Override
    public void setTitle(String title){
        this.title = title;
    }
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
