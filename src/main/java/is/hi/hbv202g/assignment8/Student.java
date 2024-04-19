package is.hi.hbv202g.assignment8;


/**
 * Represents a student.
 */
public class Student extends User {
    
    private boolean feePaid;
    private int fee = 0;

    /**
     * Constructs a new student with the specified name, username, password, and fee paid status.
     * @param name the name of the student
     * @param username the username of the student
     * @param password the password of the student
     * @param feePaid the fee paid status of the student
     */
    public Student(String name, String username, String password, boolean feePaid) {
        super(name,username,password);
        this.feePaid = feePaid;
    }
    
    /**
     * Returns the fee paid status of the student.
     * @return the fee paid status of the student
     */
    public boolean isFeePaid() {
        return feePaid;
    }

    /**
     * Sets the fee paid status of the student.
     * @param feePaid the fee paid status of the student
     */
    public void setFeePaid(boolean feePaid){
        this.feePaid = feePaid;
        if(feePaid == true){
            this.fee = 0;
        }
    }

    /**
     * Returns the fee of the student.
     * @return the fee of the student
     */
    public int getFee() {
        return fee;
    }
    
    /**
     * Sets the fee of the student.
     * @param fee the fee of the student
     */
    public void setFee(int fee) {
        if (fee <= 0) {
            setFeePaid(true);
            fee= 0;
        }
        else if (fee > 0) {
            setFeePaid(false);
            this.fee = fee;
        }
    }
}
