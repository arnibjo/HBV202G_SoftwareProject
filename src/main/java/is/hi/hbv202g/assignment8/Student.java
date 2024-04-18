package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

public class Student extends User {
    
    private boolean feePaid;
    private int fee = 0;

    public Student(String name, String username, String password, boolean feePaid) {
        super(name,username,password);
        this.feePaid = feePaid;
    }
    
    public boolean isFeePaid() {
        return feePaid;
    }

    public void setFeePaid(boolean feePaid){
        this.feePaid = feePaid;
    }

    public int getFee() {
        return fee;
    }
    
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
