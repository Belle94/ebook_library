import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

/**
 * Classe libro astratta. Un libro generico per essere definito ha bisogno
 * del file e di un proprio pannello, necessario per l' interfaccia grafica.
 *
 * @author Francesco
 * Created on 06/09/2015.
 */
public abstract class Book {

    private File file;
    private Pane pane;

    /**
     * Costruttore
     * @param file il file del libro
     */
    public Book(File file){
        this.file = file;
    }

    /**
     * metodo abstract get per l' autore del libro non implementato in quanto non si conosce l' estensione
     */
    public abstract String getAuthor();

    /**
     * metodo abstract get per il titolo del libro (non implementato)
     */
    public abstract String getTitle();

    /**
     * metodo abstract get per la data del libro (non implementato)
     */
    public abstract String getDate();

    /**
     * metodo abstract get per l' icona del libro (non implementato)
     */
    public abstract Image getIcon();

    /**
     * metodo abstract get pagine totali del libro (non implementato)
     */
    public abstract int getTotalPage();

    /**
     * metodo abstract set per il titolo del libro (non implementato)
     * @param title titolo libro
     */
    public abstract void setTitle(String title);

    /**
     * metodo abstract set per l' autore del libro (non implementato)
     * @param title autore libro
     */
    public abstract void setAuthor(String title);

    /**
     * metodo get per il libro
     * @return File del libro
     */
    public File getFile(){return file;}

    /**
     * Ritorna il percorso assoluto del libro
     * @return percorso assoluto del file
     */
    public String getFilePath() {return file.getAbsolutePath();}

    /**
     * Associa il pannello al libro
     * @param pane pannello associato al libro
     */
    public void setPane(Pane pane){this.pane = pane;}

    /**
     * Ritorna il Pannello associato al libro
     * @return panello associato al libro
     */
    public Pane getPane(){return pane;}

    /**
     * Implementati pdf ed ePub
     *@return una nuova istanza di Book in base all' estensione del file
     */
    public static Book getExtension(File f){
        Book book;
        if (f.getAbsolutePath().endsWith(".pdf") || f.getAbsolutePath().endsWith(".PDF"))
            book = new Pdf(f);
        else if (f.getAbsolutePath().endsWith(".epub") || f.getAbsolutePath().endsWith(".EPUB"))
            book = new EPub(f);
        else
            book = new UnknownBook(f, "Unknown", "Unknown");
        return book;
    }
}
