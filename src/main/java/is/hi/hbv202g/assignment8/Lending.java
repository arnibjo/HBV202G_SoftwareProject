package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

/**
 * Represents a lending.
 */
public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;
    private boolean extensionRequested;

    /**
     * Constructs a new lending.
     * @param book book to be lent.
     * @param user user who is lending.
     * @param extensionRequested if the user has requested an extension.
     */
    public Lending(Book book, User user, boolean extensionRequested) {
        this.book = book;
        this.user = user;
        this.extensionRequested = extensionRequested;
        dueDate = LocalDate.now().plusDays(30);
    }

    /**
     * Returns the due date of the lending.
     * @return the due date of the lending.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the lending.
     * @param dueDate the due date of the lending.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the book of the lending.
     * @return the book of the lending.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book of the lending.
     * @param book the book of the lending.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns the user of the lending.
     * @return the user of the lending.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user of the lending.
     * @param user the user of the lending.
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Returns if the user has requested an extension.
     * @return if the user has requested an extension.
     */
    public boolean isExtensionRequested() {
        return extensionRequested;
    }

    /**
     * Sets if the user has requested an extension.
     * @param extensionRequested if the user has requested an extension.
     */
    public void setExtensionRequested(boolean extensionRequested) {
        this.extensionRequested = extensionRequested;
    }

}
