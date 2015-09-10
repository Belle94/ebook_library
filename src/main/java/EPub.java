import javafx.scene.image.Image;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Francesco on 07/09/2015.
 */
public class EPub extends Book {
    private Image image;
    private String author, title;
    private String date;
    private int totalPage;

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
            System.out.println("[ERROR] Epub not added");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
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
    @Override
    public void setTitle(String title){
        try {
            EpubReader epubReader = new EpubReader();
            nl.siegmann.epublib.domain.Book epub = epubReader.readEpub(new FileInputStream(getFilePath()));
            Metadata metadata = epub.getMetadata();
            metadata.setTitles(new ArrayList<String>() {{add(title);}});
            epub.setMetadata(metadata);
            EpubWriter epubWriter = new EpubWriter();
            epubWriter.write(epub, new FileOutputStream(new File(getFilePath())));
            this.title = title;
        }catch (IOException e){
            System.out.println("[ERROR] Epub not changed");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
        }
    }
    @Override
    public void setAuthor(String author){
        try {
            EpubReader epubReader = new EpubReader();
            nl.siegmann.epublib.domain.Book epub = epubReader.readEpub(new FileInputStream(getFilePath()));
            Metadata metadata = epub.getMetadata();
            metadata.setAuthors(new ArrayList<Author>(){{add(new Author(author));}});
            epub.setMetadata(metadata);
            EpubWriter epubWriter = new EpubWriter();
            epubWriter.write(epub, new FileOutputStream(new File(getFilePath())));
            this.author = author;
        }catch (IOException e){
            System.out.println("[ERROR] Epub not changed");
            System.out.println("[ERROR] Message: " + e.getMessage());
            System.out.println("[ERROR] Cause: " + e.getCause());
        }
    }
}

