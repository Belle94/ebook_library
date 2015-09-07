import javafx.scene.image.Image;

import java.io.File;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public abstract class Book {

    private File file;
    private Image icon;

    public Book(File file){
        this.file = file;
        icon = new Image("book.png");
    }
    public Image getIcon() {
        return icon;
    }
    public String getFilePath() {
        return file.getAbsolutePath();
    }
    @Override
    public String toString(){
        return "\nTitle: "+this.getTitle()
                +"\nAuthor: "+ this.getAuthor()
                +"\nDate: "+this.getDate()
                +"\nPages: "+ this.getTotalPage()
                +"\nPathFile: "+this.getFilePath();
    }

    public abstract String getAuthor();
    public abstract String getTitle();
    public abstract String getDate();
    public abstract int getTotalPage();

}
