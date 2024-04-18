package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;
    private boolean extensionRequested;

    public Lending(Book book, User user, boolean extensionRequested) {
        this.book = book;
        this.user = user;
        this.extensionRequested = extensionRequested;
        dueDate = LocalDate.now().plusDays(30);
    }

    public Lending(Book book, User user) {
        this.book = book;
        this.user = user;
        dueDate = LocalDate.now().plusDays(30);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isExtensionRequested() {
        return extensionRequested;
    }

    public void setExtensionRequested(boolean extensionRequested) {
        this.extensionRequested = extensionRequested;
    }

}
