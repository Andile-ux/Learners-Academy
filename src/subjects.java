package src;

public class subjects {
    private final int SubjectID;
    private final String SubjectName,SubjectDescription;


    public subjects(int subjectID, String subjectName, String subjectDescription) {
        SubjectID = subjectID;
        SubjectName = subjectName;
        SubjectDescription = subjectDescription;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getSubjectDescription() {
        return SubjectDescription;
    }
}
