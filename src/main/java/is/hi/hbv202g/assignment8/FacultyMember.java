package is.hi.hbv202g.assignment8;

/**
 * Represents a faculty member.
 */
public class FacultyMember extends User {
    private String department;

    /**
     * Constructs a new faculty member with the specified name, username, password, and department.
     *
     * @param name       The name of the faculty member.
     * @param userName   The username of the faculty member.
     * @param password   The password of the faculty member.
     * @param department The department of the faculty member.
     */
    public FacultyMember(String name, String userName, String password, String department) {
        super(name, userName, password);
        this.department = department;
    }

    /**
     * Gets the department of the faculty member.
     *
     * @return The department of the faculty member.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the faculty member.
     *
     * @param department The new department of the faculty member.
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}
