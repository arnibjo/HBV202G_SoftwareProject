package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for class Lending
 */
public class LendingTest { 

    private Lending lending;
    private Book book;
    private User user;

    /**
     * Sets up the test.
     */
    @Before
    public void setUp() {
        book = new Book("Test Book", "Author");
        user = new Student("John Doe", "jdoe", "password", true);
        lending = new Lending(book, user, false);
    }

    /*
     * Test for method getDueDate()
     */
    @Test
    public void testGetDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plusDays(30);
        assertEquals(expectedDueDate, lending.getDueDate());
    }

    /*
     * Test for method setDueDate()
     */
    @Test
    public void testSetDueDate() {
        LocalDate newDueDate = LocalDate.now().plusDays(15);
        lending.setDueDate(newDueDate);
        assertEquals(newDueDate, lending.getDueDate());
    }

    /*
     * Test for method getBook()
     */
    @Test
    public void testGetBook() {
        assertEquals(book, lending.getBook());
    }

    /*
     * Test for method setBook()
     */
    @Test
    public void testSetBook() {
        Book newBook = new Book("New Book", "Author");
        Lending lending = new Lending(book, user, false);
        lending.setBook(newBook);
        assertEquals(newBook, lending.getBook());
    }

    /*
     * Test for method getUser()
     */
    @Test
    public void testGetUser() {
        assertEquals(user, lending.getUser());
    }

    /*
     * Test for method setUser()
     */
    @Test
    public void testSetUser() {
        User newUser = new Student("New User", "new", "password", true);
        Lending lending = new Lending(book, user, false);
        lending.setUser(newUser);
        assertEquals(newUser, lending.getUser());
    }

    /*
     * Test for method isExtensionRequested()
     */
    @Test
    public void testIsExtensionRequested() {
        lending = new Lending(book, user, true);
        assertEquals(true, lending.isExtensionRequested());
    }

    /*
     * Test for method setExtensionRequested()
     */
    @Test
    public void testSetExtensionRequested() {
        lending.setExtensionRequested(true);
        assertEquals(true, lending.isExtensionRequested());
    }
}

