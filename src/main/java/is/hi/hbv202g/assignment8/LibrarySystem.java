package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> books;
    private List<Lending> lendings;
    private List<User> users;
    private User loggedInUser;

    public LibrarySystem() {
        books = new ArrayList<Book>();
        lendings = new ArrayList<Lending>();
        users = new ArrayList<User>();
    }

    /**
     * Adds a new faculty member user to the system.
     *
     * @param  name        the name of the faculty member
     * @param  username    the username of the faculty member
     * @param  password    the password of the faculty member
     * @param  department  the department of the faculty member
     * @throws UserOrBookDoesNotExistException if the username is already taken
     */
    public void addFacultyMemberUser(String name, String username, String password, String department) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                throw new UserOrBookDoesNotExistException("The username " + username + " is already taken.");
            }
        }
        users.add(new FacultyMember(name, username, password, department));
    }

    /**
     * Adds a new student user to the system.
     *
     * @param  name        the name of the student
     * @param  username    the username of the student
     * @param  password    the password of the student
     * @throws UserOrBookDoesNotExistException if the username is already taken
     */
    public void addStudentUser(String name, String username, String password) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                throw new UserOrBookDoesNotExistException("The username " + username + " is already taken.");
            }
        }
        users.add(new Student(name, username, password, true));
    }

    /**
     * Logs in the user with the given username and password.
     *
     * @param  username  the username of the user
     * @param  password  the password of the user
     * @throws UserOrBookDoesNotExistException if the username or password is incorrect
     */
    public void login(String username, String password) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                loggedInUser = user;
                return;
            }
        }
        throw new UserOrBookDoesNotExistException("The username or password is incorrect.");
    }

    /**
     * Logs out the current user.
     *
     * @param  None
     * @return None
     */
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Adds a new book to the system.
     *
     * @param  title       the title of the book
     * @param  authorName  the name of the author
     * @throws UserOrBookDoesNotExistException if the book already exists
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName){
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a new book to the system.
     *
     * @param  title       the title of the book
     * @param  authorName  the name of the author
     * @throws UserOrBookDoesNotExistException if the book already exists
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException{
        books.add(new Book(title, authors));
    }

    /**
     * Finds a book by its title.
     *
     * @param  title  the title of the book to find
     * @return        the book with the specified title
     * @throws UserOrBookDoesNotExistException if the book with the title does not exist in the library system
     */
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("The book with the title " + title + " does not exist in the library system.");
    }

    /**
     * Finds a user by its username.
     *
     * @param  username  the username of the user to find
     * @return           the user with the specified username
     * @throws UserOrBookDoesNotExistException if the user with the username does not exist in the library system
     */
    public User findUserByUsername(String username) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("The user with the username " + username + " does not exist in the library system.");
    }

    /**
     * Borrows a book from the library for the logged-in user.
     *
     * @param  userName  the username of the user borrowing the book
     * @param  bookTitle the title of the book to borrow
     * @throws UserOrBookDoesNotExistException if the book with the title has already been borrowed
     */
    public void borrowBook(String userName, String bookTitle) throws UserOrBookDoesNotExistException {
        User user = findUserByUsername(userName);
        Book book = findBookByTitle(bookTitle);
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                throw new UserOrBookDoesNotExistException("The book with the title " + book.getTitle() + " has already been borrowed.");
            }
        }
        lendings.add(new Lending(book,user));
        if(user instanceof Student){
            ((Student) user).setFee(((Student) user).getFee() + 50);
        }
    }

    /**
     * Extends the due date of a lending.
     *
     * @param  lending     the lending to extend
     * @param  newDueDate  the new due date
     * @param  fee         the fee to add
     * @throws UserOrBookDoesNotExistException if the book with the title has already been borrowed
     */
    public void extendLending(Lending lending, LocalDate newDueDate, int fee) throws UserOrBookDoesNotExistException{
        lending.setDueDate(newDueDate);
        lending.setExtensionRequested(false);
        Student student = (Student) lending.getUser();
        student.setFee(student.getFee() + fee);
    }

    /**
     * Returns a book to the library from the logged-in user.
     *
     * @param  userName  the username of the user returning the book
     * @param  bookTitle the title of the book to return
     * @throws UserOrBookDoesNotExistException if the book with the title has not been borrowed
     */
    public void returnBook(String userName, String bookTitle) throws UserOrBookDoesNotExistException {
        User user = findUserByUsername(userName);
        Book book = findBookByTitle(bookTitle);
        boolean isBookBorrowedByUser = false;
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book) && lending.getUser().equals(user)) {
                int index = lendings.indexOf(lending);
                lendings.remove(index);
                isBookBorrowedByUser = true;
                break;
            }
        }
        if(!isBookBorrowedByUser){
            throw new UserOrBookDoesNotExistException("The user with the name " + user.getName() + " did not borrow the book with the title " + book.getTitle() + ".");
        }
    }

    /**
     * Returns all books in the library.
     * @return
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Returns all lendings in the library.
     * @return
     */
    public List<Lending> getAllLendings() {
        return lendings;
    }

    /**
     * Returns all lendings by a specific user.
     * @param username
     * @return
     * @throws UserOrBookDoesNotExistException
     */
    public List<Lending> getLendingsByUsername(String username) throws UserOrBookDoesNotExistException {
        User user = findUserByUsername(username);
        List<Lending> userLendings = new ArrayList<>();
        for (Lending lending : lendings) {
            if (lending.getUser().equals(user)) {
                userLendings.add(lending);
            }
        }
        return userLendings;
    }

    /**
     * Returns all lendings that have an extension requested(true) or not(false).
     * @param extensionRequested
     * @return
     */
    public List<Lending> getLendingsByExtensionRequested(boolean extensionRequested) {
        List<Lending> extensionList = new ArrayList<>();
        for (Lending lending : lendings) {
            if (lending.isExtensionRequested() == extensionRequested) {
                extensionList.add(lending);
            }
        }
        return extensionList;
    }

    /**
     * Returns all users in the library.
     * @return
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Returns the logged-in user.
     * @return
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Checks if there is a user logged in.
     * @return
     */
    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }
}
