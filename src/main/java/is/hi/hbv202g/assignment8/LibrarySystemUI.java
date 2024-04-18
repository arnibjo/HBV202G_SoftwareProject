package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibrarySystemUI {
    private LibrarySystem library;
    private Scanner scanner;
    private String[] options = {"1. Borrow a book", 
                                "2. Return a book", 
                                "3. Extend lending", 
                                "4. Request extension",
                                "5. Pay fee", 
                                "6. Add a book", 
                                "7. Add a user", 
                                "8. List books", 
                                "9. List users", 
                                "10. Log out"};

    public LibrarySystemUI() {
        library = new LibrarySystem(); 
        scanner = new Scanner(System.in);

        // Add some books
        library.addBookWithTitleAndNameOfSingleAuthor("The Great Gatsby", "F. Scott Fitzgerald");
        library.addBookWithTitleAndNameOfSingleAuthor("To Kill a Mockingbird", "Harper Lee");
        library.addBookWithTitleAndNameOfSingleAuthor("1984", "George Orwell");
        library.addBookWithTitleAndNameOfSingleAuthor("Pride and Prejudice", "Jane Austen");
        library.addBookWithTitleAndNameOfSingleAuthor("The Catcher in the Rye", "J. D. Salinger");

        try {
            library.addStudentUser("John Doe", "johndoe", "password123");
            library.addStudentUser("Jane Smith", "janesmith", "password456");
            library.addFacultyMemberUser("Bob Parker", "admin", "admin012", "Library");
            library.addFacultyMemberUser("Alice Johnson", "alicejohnson", "password789", "Computer Science");
        } catch (UserOrBookDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the Library System and presents the user with a menu to login or exit.
     *
     * @param  None  No parameters needed for this function.
     * @return       No return value for this function.
     */
    public void start() {
        System.out.println("Welcome to the Library System!");
        System.out.println("Enter 1 for login or 0 to exit.");
        System.out.print("Your choice: ");
        String input = scanner.nextLine();
    
        try {
            int option = Integer.parseInt(input);
            if (option == 1) {
                login();
            } else if (option == 0) {
                scanner.close();
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, please try again.");
                start();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            start();
        }
    }
    
    /**
     * Login function for the Library System.
     *
     * @param  None  No parameters needed for this function.
     * @return       No return value for this function.
     */
    private void login() {
        System.out.println("Log In");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        sessionType(username, password);
    }

    /**
     * Determines the session type based on the provided username and password.
     *
     * @param  username  the username of the user
     * @param  password  the password of the user
     */
    private void sessionType(String username, String password) {
        try {
            library.login(username, password);
            if (library.isUserLoggedIn()) {
                User user = library.getLoggedInUser();
                if (user instanceof Student) {
                    studentLoginSession();
                } else if (user instanceof FacultyMember) {
                    if(!((FacultyMember) user).isAdmin()) {
                        adminLoginSession();
                    }
                    else{
                        facultyLoginSession();
                    }
                }
            }
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again.");
            login();
        }
    }

    /**
     * Executes the student login session.
     *
     * @param  None  No parameters needed.
     * @return       No return value.
     */
    private void studentLoginSession() {
        System.out.println("Welcome, " + library.getLoggedInUser().getName() + "!");

        while (library.isUserLoggedIn()) {
            optionsMenu(1);
        }

    }

    /**
     * Executes the faculty login session.
     *
     * @param  None  No parameters needed.
     * @return       No return value.
     */
    private void facultyLoginSession() {
        System.out.println("Welcome, " + library.getLoggedInUser().getName() + "!");
        while (library.isUserLoggedIn()) {
            optionsMenu(2);
        }

    }

    /**
     * Executes the administrator login session.
     *
     * @param  None  No parameters needed.
     * @return       No return value.
     */
    private void adminLoginSession() {
        System.out.println("Administator login session");
        System.out.println("Welcome, " + library.getLoggedInUser().getName() + "!");
        while (library.isUserLoggedIn()) {
            optionsMenu(3);
        }

    }

    /**
     * Presents the options menu to the user.
     *
     * @param  userType  the type of user
     * @return           No return value.
     */
    private void optionsMenu(int userType) {
        boolean validOptionSelected = false;
        boolean[] invalidOption = new boolean[10];
        int[] studentInvalidOptions = {3, 6, 7, 9};
        int[] facultyInvalidOptions = {3, 5, 6, 7, 9};
        int[] adminInvalidOptions = {1, 2, 4, 5};
        
        Arrays.fill(invalidOption, false);
        if (userType == 1) {
            for (int i = 0; i < studentInvalidOptions.length; i++) {
                invalidOption[studentInvalidOptions[i]-1] = true;
            }
        }
        else if (userType == 2) {
            for (int i = 0; i < facultyInvalidOptions.length; i++) {
                invalidOption[facultyInvalidOptions[i]-1] = true;
            }
        }
        else {
            for (int i = 0; i < adminInvalidOptions.length; i++) {
                invalidOption[adminInvalidOptions[i]-1] = true;
            }
        }

        while (library.isUserLoggedIn()) {
            int choice = -1;
            while (!validOptionSelected) {
                System.out.println("\nChoose an option:");
                for (int i = 0; i < options.length; i++) {
                    if (!invalidOption[i]) {
                        System.out.println(options[i]);
                    }
                }
                try {
                    String input = scanner.nextLine();
                    choice = Integer.parseInt(input);
                    if (choice < 1 || choice > options.length || invalidOption[choice - 1]) {
                        System.out.println("Invalid option. Please try again.");
                    } else {
                        validOptionSelected = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
            // Reset the flag for the next iteration
            validOptionSelected = false;
        
            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    extendLending();
                    break;
                case 4:
                    requestExtension();
                    break;
                case 5:
                    payFee();
                    break;
                case 6:
                    addBook();
                    break;
                case 7:
                    addUser();
                    break;
                case 8:
                    displayBooks();
                    break;
                case 9:
                    displayUsers();
                    break;
                case 10:
                    logOut();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * Logs the user out of the system.
     *
     * @param  None  No parameters needed.
     * @return       No return value.
     */
    private void logOut() {
        library.logout();
        System.out.println("Logged out successfully.");
        start();
    }

    /**
     * Displays all the users in the system.
     *
     * @param  None  No parameters needed.
     * @return       No return value.
     */
    private void displayUsers() {
        List<User> users = library.getAllUsers();
        
        System.out.println("Faculty Members:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Name", "Username", "Department");
        System.out.println("-------------------------------------------------");
        for (User user : users) {
            if (user instanceof FacultyMember) {
                FacultyMember facultyMember = (FacultyMember) user; // Casting to FacultyMember
                System.out.printf("%-20s | %-20s | %-20s%n", user.getName(), user.getUsername(), facultyMember.getDepartment());
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Students:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Name", "Username", "Fee Paid?");
        System.out.println("-------------------------------------------------");
        for (User user : users) {
            if (user instanceof Student) {
                Student student = (Student) user; // Casting to FacultyMember
                System.out.printf("%-20s | %-20s | %-20s%n", user.getName(), user.getUsername(), student.isFeePaid());
            }
        }
    }
    
    /**
     * Method to handle payment of fees by students.
     */
    private void payFee() {
        User user = library.getLoggedInUser();
        if(user instanceof Student) {
            Student student = (Student) user;
            if(student.isFeePaid()) {
                System.out.println("Fee is already paid.");
                return;
            }
            else{
                System.out.println("You have yet to pay " +  student.getFee());
                System.out.println("How much of the fee do you want to pay?");
                String input = scanner.nextLine();
                int amount = -1;
                try {
                    amount = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Returning to main menu.");
                    return;
                }
                student.setFee(student.getFee() - amount);
                System.out.println("Fee paid successfully.");
            }
        }
    }

    /**
     * Requests an extension for a book lending for the logged-in user.
     *
     * @param  None
     * @return None
     */
    private void requestExtension() {
        User user = library.getLoggedInUser();
        System.out.println("Request Extension");
        List<Lending> userLendings = new ArrayList<Lending>();
        try {
            userLendings = library.getLendingsByUsername(user.getUsername());
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("For which book do you want to extend the lending?: ");
        for (int i = 0; i < userLendings.size(); i++) {
            System.out.println((i+1) + ". " + userLendings.get(i).getBook().getTitle());
        }
        String input = scanner.nextLine();
        int choice = -1;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        Lending lending = userLendings.get(choice-1);
        lending.setExtensionRequested(true);
    }

    /**
     * Adds a new user to the system.
     *
     * @param  None
     * @return None
     */
    private void addUser() {
        System.out.println("Add User");
        System.out.print("Enter the name of the user: ");
        String name = scanner.nextLine();
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user type (1 for student or 2 for faculty): ");
        String input = scanner.nextLine();
        int userType = -1;
        try {
            userType = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        try {
            if(userType == 1) {
                library.addStudentUser(name, username, password);
                System.out.println("User created successfully.");
            }
            else if (userType == 2) {
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                library.addFacultyMemberUser(name, username, password, department);
                System.out.println("User created successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Adds a new book to the system.
     *
     * @param  None
     * @return None
     */
    private void addBook(){
        System.out.println("Add a book");
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author(s) of the book(if multiple authors, separate them with a comma and space): ");
        String[] authors = scanner.nextLine().split(", ");
        if(authors.length > 1) {
            List<Author> authorList = new ArrayList<Author>();
            for (String author : authors) {
                authorList.add(new Author(author));
            }
            try {
                library.addBookWithTitleAndAuthorList(title, authorList);
                System.out.println("Book added successfully.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
        else {
            library.addBookWithTitleAndNameOfSingleAuthor(title, authors[0]);
            System.out.println("Book added successfully.");
        }
    }

    /**
     * Borrows a book from the library for the logged-in user.
     *
     * @param  None
     * @return None
     */
    private void borrowBook() {
        System.out.println("Borrow a book");
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        String username = library.getLoggedInUser().getUsername();
        try {
            library.borrowBook(username, title); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("You successfully borrowed the book " + title + ".");
    }

    /**
     * Returns a book to the library from the logged-in user.
     *
     * @param  None
     * @return None
     */
    private void returnBook() {
        System.out.print("Return a book");
        System.out.print("Enter your name of the user: ");
        String userName = scanner.nextLine();
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();

        try {
            library.returnBook(userName, title);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("The book with the title " + title + " successfully returned by " + userName + ".");
    }

    /**
     * Extends the due date of a lending.
     *
     * @param  None
     * @return None
     */
    private void extendLending() {
        System.out.println("Extend lending");
        System.out.print("Here are all the extensions that have been requested: ");
        List<Lending> extensions = library.getLendingsByExtensionRequested(true);
        System.out.println("Extensions:");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-4s | %-30s | %-15s | %-12s%n", "No.", "Book Title", "User", "Due Date");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < extensions.size(); i++) {
            Lending extension = extensions.get(i);
            String bookTitle = extension.getBook().getTitle();
            String username = extension.getUser().getUsername();
            LocalDate dueDate = extension.getDueDate();
            System.out.printf("%-4d | %-30s | %-15s | %-12s%n", (i + 1), bookTitle, username, dueDate);
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        
        System.out.println("Select the number of the lending you wish to extend: ");
        String input = scanner.nextLine();
        int index = -1;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to main menu.");
            return;
        }
        Lending lending = extensions.get(index);

        System.out.print("Enter the amount of days you wish to extend: ");
        String days = scanner.nextLine();

        int daysToBeAdded = Integer.parseInt(input);
        LocalDate newDueDate = LocalDate.now().plusDays(daysToBeAdded);
        
        try {
            library.extendLending(lending, newDueDate, daysToBeAdded*5);
            System.out.println("Successfully extended lending.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again.");
        }
    }

    /**
     * Displays all books in the library.
     *
     * @param  None
     * @return None
     */
    private void displayBooks() {
        System.out.println("All books in the library:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-50s | %-30s%n", "Title", "Authors");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (Book book : library.getAllBooks()) {
            String title = book.getTitle();
            String authors = book.getAuthors().stream()
                    .map(Author::getName)
                    .reduce((author1, author2) -> author1 + ", " + author2)
                    .orElse("");
            System.out.printf("%-50s | %-30s%n", title, authors);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        
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
