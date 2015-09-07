import javafx.scene.image.Image;

import java.util.Date;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public abstract class Book {

    private String pathFile;
    private Image icon;

    public Book(String pathFile){
        this.pathFile = pathFile;
        icon = new Image("book.png");
    }
    public Image getIcon() {
        return icon;
    }
    public String getPathFile() {
        return pathFile;
    }
    @Override
    public String toString(){
        return "\nTitle: "+this.getTitle()
                +"\nAuthor: "+ this.getAuthor()
                +"\nDate:"+this.getDate()
                +"\nPages: "+ this.getTotalPage()
                +"\nPathFile: "+this.getPathFile();
    }

    public abstract String getAuthor();
    public abstract String getTitle();
    public abstract Date getDate();
    public abstract int getTotalPage();

}
