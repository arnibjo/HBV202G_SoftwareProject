package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/*
 * Tests for class Book
 */
public class BookTest {
    private Book book;
    private final String title = "Test Book";

    /*
     * Test for method getTitle()
     */
    @Before
    public void setUp() {
        book = new Book(title, "Author 1");
    }

    /*
     * Test for method setTitle()
     */
    @Test
    public void testGetTitle() {
        assertEquals(title, book.getTitle());
    }

    /*
     * Test for method getAuthors()
     */
    @Test
    public void testSetTitle() {
        String newTitle = "New Title";
        book.setTitle(newTitle);
        assertEquals(newTitle, book.getTitle());
    }

    /*
     * Tests for method setAuthors()
     */
    @Test
    public void testGetAuthors() {
        List<Author> authors = book.getAuthors();
        assertEquals(1, authors.size());
        assertEquals("Author 1", authors.get(0).getName());
    }

    /*
     * Tests for method addAuthor()
     */
    @Test
    public void testSetAuthors() throws EmptyAuthorListException {
        List<Author> newAuthors = new ArrayList<>();
        newAuthors.add(new Author("Author 2"));
        newAuthors.add(new Author("Author 3"));

        book.setAuthors(newAuthors);
        assertEquals(2, book.getAuthors().size());
        assertEquals("Author 2", book.getAuthors().get(0).getName());
        assertEquals("Author 3", book.getAuthors().get(1).getName());
    }

    /*
     * Tests for method addAuthor()
     */
    @Test
    public void testAddAuthor() {
        book.addAuthor(new Author("Author 2"));
        assertEquals(2, book.getAuthors().size());
        assertEquals("Author 2", book.getAuthors().get(1).getName());
    }

    /*
     * Test for constructor with empty author list
     */
    @Test
    public void testConstructorWithEmptyAuthorList() {
        assertThrows(EmptyAuthorListException.class, () -> new Book(title, new ArrayList<>()));
    }
}
