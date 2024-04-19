package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for class User
 */
public class UserTest {

    /*
     * Test for method getUsername()
     */
    @Test
    public void testGetUsername() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        assertEquals("jdoe", user.getUsername());
    }

    /*
     * Test for method setUsername()
     */
    @Test
    public void testSetUsername() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    /*
     * Test for method getPassword()
     */
    @Test
    public void testGetPassword() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        assertEquals("password", user.getPassword());
    }

    /*
     * Test for method setPassword()
     */
    @Test
    public void testSetPassword() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    /*
     * Test for method getName()
     */
    @Test
    public void testGetName() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        assertEquals("John Doe", user.getName());
    }

    /*
     * Test for method setName()
     */
    @Test
    public void testSetName() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        user.setName("Jane Smith");
        assertEquals("Jane Smith", user.getName());
    }

    /*
     * Test for method isAdmin()
     */
    @Test
    public void testIsAdmin() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        assertFalse(user.isAdmin());
    }

    /*
     * Test for method setAdmin()
     */
    @Test
    public void testSetAdmin() {
        User user = new ConcreteUser("John Doe", "jdoe", "password");
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }

    /**
     * A concrete implementation of the abstract User class for testing purposes
     */
    private static class ConcreteUser extends User {
        public ConcreteUser(String name, String username, String password) {
            super(name, username, password);
        }
    }
}

