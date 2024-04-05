package is.hi.hbv202g.assignment8;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main
{
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
