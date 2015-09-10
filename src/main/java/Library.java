import java.util.Vector;

/**
 * @author Francesco
 * Created by Francesco on 06/09/2015.
 */
public class Library {
    private Vector<Book> collection;

    public Library(){
        collection = new Vector<>();
    }
    public void addBook(Book book){
        collection.add(book);
    }
    public void removeBook(Book book){
        if (collection.contains(book))
            collection.remove(book);
    }
    public Vector<Book> getCollection(){
        return collection;
    }

    /**
     * Print the complete collection in standard output.
     */
    public void stamp(){
        int i = 1;
        System.out.println("###### EBOOK LIBRARY ######");
        for (Book b:collection){
            System.out.println("\n# Book n."+i++ +b.toString());
        }
    }
}
