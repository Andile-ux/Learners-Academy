package src;

public class users {
    private final String fname, lname, email;
    private final int userType,classID;

    public users(String fname, String lname, String email, int userType, int classID) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.userType = userType;
        this.classID = classID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public int getClassID() {
        return classID;
    }
}
