package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<Book> books;
    private List<Lending> lendings;
    private List<User> users;

    public LibrarySystem() {
        books = new ArrayList<Book>();
        lendings = new ArrayList<Lending>();
        users = new ArrayList<User>();
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName){
        books.add(new Book(title, authorName));
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException{
        books.add(new Book(title, authors));
    }
    
    public void addStudentUser(String name, boolean feePaid){
        users.add(new Student(name, feePaid));
    }

    public void addFacultyMemberUser(String name, String department){
        users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("The book with the title " + title + " does not exist in the library system.");
    }

    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("The user with the name " + name + " does not exist in the library system.");
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if(users.indexOf(user) == -1){
            throw new UserOrBookDoesNotExistException("The user with the name " + user.getName() + " does not exist in the library system.");
        }
        if(books.indexOf(book) == -1){
            throw new UserOrBookDoesNotExistException("The book with the title " + book.getTitle() + " does not exist in the library system.");
        }
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                throw new UserOrBookDoesNotExistException("The book with the title " + book.getTitle() + " has already been borrowed.");
            }
        }
        lendings.add(new Lending(book,user));
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException{
        boolean doesLendingExist = false;
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                lending.setDueDate(newDueDate);
                doesLendingExist = true;
                break;
            }
        }
        if(!doesLendingExist){
            throw new UserOrBookDoesNotExistException("The user with the name " + facultyMember.getName() + " did not borrow the book with the title " + book.getTitle() + ".");
        }
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
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

    public List<Book> getAllBooks() {
        return books;
    }
}
