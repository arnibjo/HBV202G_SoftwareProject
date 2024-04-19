package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * Tests for class FacultyMember
 */
public class FacultyMemberTest {

    /*
     * Tests for method getDepartment()
     */
    @Test
    public void testGetDepartment() {
        String department = "Computer Science";
        FacultyMember facultyMember = new FacultyMember("John Doe", "jdoe", "password", department);
        assertEquals(department, facultyMember.getDepartment());
    }

    /*
     * Tests for method setDepartment()
     */
    @Test
    public void testSetDepartment() {
        FacultyMember facultyMember = new FacultyMember("John Doe", "jdoe", "password", "Initial Department");
        String newDepartment = "Physics";
        facultyMember.setDepartment(newDepartment);
        assertEquals(newDepartment, facultyMember.getDepartment());
    }
}
