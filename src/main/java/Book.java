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
        icon = new Image("defaultIcon.png");
    }
    public Image getIcon() {
        return icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }
    public String getPathFile() {
        return pathFile;
    }
    @Override
    public String toString(){
        return "\nTitle: "+this.getTitle()
                +"\nAuthor: "+ this.getAuthor()
                +"\nPages: "+ this.getTotalPage()
                +"\nPathFile: "+this.getPathFile();
    }

    abstract String getAuthor();
    abstract String getTitle();
    abstract Date getDate();
    abstract int getTotalPage();

}
