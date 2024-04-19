package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*
 * Tests for class LibrarySystem
 */
public class LibrarySystemTest {
    private LibrarySystem librarySystem;

    /*
     * Sets up the test.
     */
    @Before
    public void setUp() {
        librarySystem = new LibrarySystem();
    }

    /*
     * Test for method addFacultyMemberUser()
     */
    @Test
    public void testAddFacultyMemberUser() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("John Doe", "jdoe", "password", "Computer Science");
        assertEquals(1, librarySystem.getAllUsers().size());
    }

    /*
     * Test for method addStudentUser()
     */
    @Test
    public void testAddStudentUser() throws UserOrBookDoesNotExistException {
        librarySystem.addStudentUser("Jane Smith", "jsmith", "password");
        assertEquals(1, librarySystem.getAllUsers().size());
    }

    /*
     * Test for method login()
     */
    @Test
    public void testLogin() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("John Doe", "jdoe", "password", "Computer Science");
        librarySystem.login("jdoe", "password");
        assertNotNull(librarySystem.getLoggedInUser());
    }

    /*
     * Test for method logout()
     */
    @Test
    public void testLogout() {
        librarySystem.logout();
        assertNull(librarySystem.getLoggedInUser());
    }

    /*
     * Test for method addBookWithTitleAndNameOfSingleAuthor()
     */
    @Test
    public void testAddBookWithTitleAndNameOfSingleAuthor() {
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        assertEquals(1, librarySystem.getAllBooks().size());
    }

    /*
     * Test for method addBookWithTitleAndAuthorList()
     */
    @Test
    public void testAddBookWithTitleAndAuthorList() throws EmptyAuthorListException {
        List<Author> authors = Arrays.asList(new Author("Author 1"), new Author("Author 2"));
        librarySystem.addBookWithTitleAndAuthorList("Book Title", authors);
        assertEquals(1, librarySystem.getAllBooks().size());
    }

    /*
     * Test for method findBookByTitle()
     */
    @Test
    public void testFindBookByTitle() throws UserOrBookDoesNotExistException {
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        Book foundBook = librarySystem.findBookByTitle("Book Title");
        assertNotNull(foundBook);
    }

    /*
     * Test for method findUserByUsername()
     */
    @Test
    public void testFindUserByUsername() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("John Doe", "jdoe", "password", "Computer Science");
        User foundUser = librarySystem.findUserByUsername("jdoe");
        assertNotNull(foundUser);
    }

    /*
     * Test for method borrowBook()
     */
    @Test
    public void testBorrowBook() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("John Doe", "jdoe", "password", "Computer Science");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        librarySystem.borrowBook("jdoe", "Book Title");
        assertEquals(1, librarySystem.getAllLendings().size());
    }

    /*
     * Test for method returnBook()
     */
    @Test
    public void testReturnBook() throws UserOrBookDoesNotExistException {
        librarySystem.addFacultyMemberUser("John Doe", "jdoe", "password", "Computer Science");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        librarySystem.borrowBook("jdoe", "Book Title");
        librarySystem.returnBook("jdoe", "Book Title");
        assertEquals(0, librarySystem.getAllLendings().size());
    }

    /*
     * Test for method getLendingsByUsername()
     */
    @Test
    public void testGetLendingsByUsername() throws UserOrBookDoesNotExistException {
        librarySystem.addStudentUser("Jane Smith", "jsmith", "password");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        librarySystem.borrowBook("jsmith", "Book Title");
        List<Lending> userLendings = librarySystem.getLendingsByUsername("jsmith");
        assertEquals(1, userLendings.size());
    }

    /*
     * Test for method getLendingsByExtensionRequested()
     */
    @Test
    public void testGetLendingsByExtensionRequested() throws UserOrBookDoesNotExistException {
        librarySystem.addStudentUser("Jane Smith", "jsmith", "password");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "Author Name");
        librarySystem.borrowBook("jsmith", "Book Title");
        List<Lending> extensionLendings = librarySystem.getLendingsByExtensionRequested(false);
        assertEquals(1, extensionLendings.size());
    }

    /*
     * Test for method isUserLoggedIn()
     */
    @Test
    public void testIsUserLoggedIn() {
        assertFalse(librarySystem.isUserLoggedIn());
    }
}
