import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public abstract class Book {

    private File file;
    private Pane pane;

    public Book(File file){
        this.file = file;
    }
    public abstract String getAuthor();
    public abstract String getTitle();
    public abstract String getDate();
    public abstract Image getIcon();
    public abstract int getTotalPage();
    public abstract void setTitle(String title);
    public abstract void setAuthor(String title);
    public File getFile(){return file;}
    public String getFilePath() {return file.getAbsolutePath();}
    public void setPane(Pane pane){this.pane = pane;}
    public Pane getPane(){return pane;}
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
    @Override
    public String toString(){
        return "\nTitle: "+this.getTitle()
                +"\nAuthor: "+ this.getAuthor()
                +"\nDate: "+this.getDate()
                +"\nPages: "+ this.getTotalPage()
                +"\nPathFile: "+this.getFilePath();
    }
}
