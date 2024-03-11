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

    public void borrowBook(User user, Book book){
        Lending lending = new Lending(book,user);
        lendings.add(lending);
        
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate){
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                lending.setDueDate(newDueDate);
            }
        }
    }

    public void returnBook(User user, Book book){
        lendings.remove(new Lending(book, user));
    }
}
