import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.File;
import java.util.List;
import java.util.Vector;

/**
 * Classe che definisce gli attributi da scrivere nel Database, ho creato questa classe per rendere
 * indipendente le informazioni scritte nel database da il resto dell' applicazione
 * @author Francesco
 * Created on 11/09/2015.
 */

@DatabaseTable(tableName = "library")
public class DatabaseBook {

    @DatabaseField(id = true)
    public String filePath;
    @DatabaseField(canBeNull = true)
    private String title;
    @DatabaseField(canBeNull = true)
    private String author;
    @DatabaseField(canBeNull = true)
    private String date;
    @DatabaseField(canBeNull = false)
    private int totalPage;

    public DatabaseBook(){}

    /**
     * Costruttore
     * @param book libro da salvare
     */
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

    /**
     * Metodo utilizzato per la conversione da generico DbBook a uno specifico Book.
     * Controlla l' estensione del libro e crea l' istanza per quella estensione
     * @param listBook lista di libri da convertire in Book
     * @return vettore di libri
     */
    public static Vector<Book> convertDbBookToBook(List<DatabaseBook> listBook){
        Vector<Book> collection = new Vector<>();
        for (DatabaseBook dbBook : listBook) {
            if (Book.getExtension(new File(dbBook.getFilePath())) instanceof Pdf)
                collection.add(new Pdf(new File(dbBook.getFilePath()),
                                                dbBook.getTitle(),
                                                dbBook.getAuthor(),
                                                dbBook.getDate(),
                                                dbBook.getTotalPage()));
            else if (Book.getExtension(new File(dbBook.getFilePath())) instanceof EPub)
                collection.add(new EPub(new File(dbBook.getFilePath()),
                                                dbBook.getTitle(),
                                                dbBook.getAuthor(),
                                                dbBook.getDate(),
                                                dbBook.getTotalPage()));
            else if (Book.getExtension(new File(dbBook.getFilePath())) instanceof UnknownBook)
                collection.add(new UnknownBook(new File(dbBook.getFilePath()),
                                                dbBook.getTitle(),
                                                dbBook.getAuthor(),
                                                dbBook.getDate()));
        }
        return collection;
    }
}
