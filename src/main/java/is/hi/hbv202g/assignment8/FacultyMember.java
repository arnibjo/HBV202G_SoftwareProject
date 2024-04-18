package is.hi.hbv202g.assignment8;

public class FacultyMember extends User {
    private String department;

    public FacultyMember(String name, String userName, String password, String department) {
        super(name, userName, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
