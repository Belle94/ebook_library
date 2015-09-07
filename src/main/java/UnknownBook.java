import java.io.File;

/**
 * @author Francesco
 * Created by Francesco on 07/09/2015.
 */
public class UnknownBook extends Book {
    public String defaultString;
    public String defaultDate;

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
        setDefaultDate(defaultDate);
        setDefaultString(defaultString);
    }

    public void setDefaultString(String defaultString) {
        this.defaultString = defaultString;
    }

    public void setDefaultDate(String stringDate){
        this.defaultDate = stringDate;
    }

    @Override
    public String getAuthor() {
        return defaultString;
    }

    @Override
    public String getTitle() {
        return defaultString;
    }

    @Override
    public String getDate() {
        return defaultDate;
    }

    @Override
    public int getTotalPage() {
        return 0;
    }
}
