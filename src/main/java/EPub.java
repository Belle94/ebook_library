import javafx.scene.image.Image;
import nl.siegmann.epublib.epub.EpubReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Francesco on 07/09/2015.
 */
public class EPub extends Book {
    public Image image;
    String author, title;
    String date;
    int totalPage;

    public EPub(File file){
        super(file);
        try {
            image = new Image("epub.png");
            EpubReader epubReader = new EpubReader();
            nl.siegmann.epublib.domain.Book epub = epubReader.readEpub(new FileInputStream(file.getAbsolutePath()));
            author = epub.getMetadata().getAuthors().toString();
            title = epub.getMetadata().getTitles().toString();
            date = epub.getMetadata().getDates().toString();
            totalPage = 0;
            if (title.equals(""))
                title = "no-name";
            if (author.equals(""))
                author = "no-name";
        }catch (IOException e){
            System.err.println("\n[Error] readEpub can't load file");
            e.printStackTrace();
        }
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
        return totalPage;
    }
    @Override
    public Image getIcon() {
        return image;
    }
}

