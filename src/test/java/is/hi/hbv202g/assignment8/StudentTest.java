package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for class Student
 */
public class StudentTest {

    /**
     * Tests for method isFeePaid()
     */
    @Test
    public void testFeePaid() {
        Student student = new Student("John Doe", "jdoe", "password", false);
        assertFalse(student.isFeePaid());
        student.setFeePaid(true);
        assertTrue(student.isFeePaid());
    }

    /**
     * Tests for method getFee()
     */
    @Test
    public void testFee() {
        Student student = new Student("John Doe", "jdoe", "password", false);
        assertEquals(0, student.getFee());
        
        student.setFee(50);
        assertEquals(50, student.getFee());
        assertFalse(student.isFeePaid());
        
        student.setFee(0);
        assertEquals(0, student.getFee());
        assertTrue(student.isFeePaid());
        
        student.setFee(-10); // Negative fee should be ignored
        assertEquals(0, student.getFee());
        assertTrue(student.isFeePaid());
    }
}
