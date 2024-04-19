package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for class Author
 */
public class AuthorTest {

    /*
     * Test for method getName()
     */
    @Test
    public void testGetName() {
        String authorName = "John Doe";
        Author author = new Author(authorName);
        assertEquals(authorName, author.getName());
    }

    /*
     * Test for method setName()
     */
    @Test
    public  void testSetName() {
        String authorName = "Jane Smith";
        Author author = new Author("Initial Name");
        author.setName(authorName);
        assertEquals(authorName, author.getName());
    }
}
