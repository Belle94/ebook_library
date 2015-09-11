import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Francesco on 11/09/2015.
 */

@DatabaseTable(tableName = "library")
public class DatabaseBook {

    @DatabaseField(id = true)
    public String filePath;
    private String title;
    private String author;
    private String date;
    private int totalPage;

    public DatabaseBook(){

    }
    public DatabaseBook(Book book){
        filePath = book.getFilePath();
        title = book.getTitle();
        author = book.getAuthor();
        date = book.getDate();
        totalPage = book.getTotalPage();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
