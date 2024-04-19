package is.hi.hbv202g.assignment8;

/**
 * Represents a user of the library system.
 */
public abstract class User {
    private String name;
    private String username;
    private String password;
    private boolean admin = false;

    /**
     * Constructs a new user with the specified name, username, and password.
     *
     * @param name     The name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the admin status of the user.
     *
     * @return The admin status of the user.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin The new admin status of the user.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
