package is.hi.hbv202g.assignment8;

/**
 * Main class.
 *
 */
public class Main
{
    /**
     * Main function that starts the library system interface
     * @param args
     */
    public static void main( String[] args )
    {
        LibrarySystemUI lsui = new LibrarySystemUI();
        try {
            lsui.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
