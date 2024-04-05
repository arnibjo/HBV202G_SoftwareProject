package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystemUI {
    private LibrarySystem library;
    private Scanner scanner;

    public LibrarySystemUI() {
        library = new LibrarySystem(); // Assuming you have a LibrarySystem class
        scanner = new Scanner(System.in);
    }

    public void start() throws EmptyAuthorListException {
        System.out.println("Welcome to the Library System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Display all books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    displayBooks();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void addBook() throws EmptyAuthorListException {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author(s) of the book(if multiple authors, separate them with a comma and space): ");
        String[] authors = scanner.nextLine().split(", ");
        if(authors.length > 1) {
            List<Author> authorList = new ArrayList<Author>();
            for (String author : authors) {
                authorList.add(new Author(author));
            }
            library.addBookWithTitleAndAuthorList(title, authorList);
            System.out.println("Book added successfully.");
        }
        else {
            library.addBookWithTitleAndNameOfSingleAuthor(title, authors[0]);
            System.out.println("Book added successfully.");
        }
    }

    private void borrowBook() {
        // Implement borrowing logic
    }

    private void returnBook() {
        // Implement returning logic
    }

    private void displayBooks() {
        System.out.println("All books in the library:");
        for (Book book : library.getAllBooks()) {
            System.out.print(book.getTitle() + " by ");
            System.out.print(book.getAuthors().get(0).getName());
            if (book.getAuthors().size() > 1) {
                for (int i = 1; i < book.getAuthors().size(); i++) {
                    Author author = book.getAuthors().get(i);
                    if (i == book.getAuthors().size() - 1) {
                        System.out.print(" and " + author.getName());
                    }
                    else {
                        System.out.print(", " + author.getName());
                    }
                } 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LibrarySystemUI ui = new LibrarySystemUI();
        try {
            ui.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }
}
