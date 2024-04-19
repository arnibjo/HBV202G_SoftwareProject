package is.hi.hbv202g.assignment8;

/**
 * Represents an author of a book.
 */
public class Author {
    private String name;

    /**
     * Constructs an author with the specified name.
     *
     * @param name The name of the author.
     */
    public Author(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the author.
     *
     * @return The name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name The new name of the author.
     */
    public void setName(String name) {
        this.name = name;
    }
}
