import java.io.File;

/**
 * @author Francesco
 * Created by Francesco on 07/09/2015.
 *
 * the UnknownBook extend Book and have the default title,author and date
 * equals "Unknown" but can be set throw SetXXX method
 */
public class UnknownBook extends Book {
    private String title, author, date;

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
        setTitle(defaultString);
        setAuthor(defaultString);
        setDate(defaultDate);
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
