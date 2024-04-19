package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book.
 */
public class Book {
    private String title;
    private List<Author> authors;

    /**
     * Constructs a new book with the specified title and author.
     * @param title the title of the book
     * @param authorName the name of the author
     */
    public Book(String title, String authorName) {
        this.title = title;
        this.authors = new ArrayList<Author>();
        this.authors.add(new Author(authorName));
    }

    /**
     * Constructs a new book with the specified title and list of authors.
     * @param title the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the list of authors is empty
     */
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        this.title = title;
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("The author list cannot be empty.");
        }
        this.authors = authors;
    }
    

    /**
     * Returns the list of authors.
     * @return the list of authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets the list of authors.
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the list of authors is empty
     */
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("The author list cannot be empty.");
        }
        this.authors = authors;
    }

    /**
     * Adds an author to the list of authors.
     * @param author the author to add
     */
    public void addAuthor(Author author) {
        authors.add(author);
    }

    /**
     * Returns the title of the book.
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
