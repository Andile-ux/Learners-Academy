package src;

public class classes {
    private int classID;
    private String ClassName,ClassDescription;
    public classes(int classID, String ClassName, String ClassDescription) {
        this.classID = classID;
        this.ClassName = ClassName;
        this.ClassDescription = ClassDescription;
    }



    public int getClassID() {
        return classID;
    }

    public String getClassDescription() {
        return ClassDescription;
    }

    public String getClassName() {
        return ClassName;
    }
}
