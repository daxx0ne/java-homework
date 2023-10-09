package HW05_1_File_IO;

public class Student extends Person {
    private final int st_id;
    private final String dept;
    private final String school;
    private final double GPA;

    public Student(String nm, int reg_id, int st_id, String dept, String school, double gpa) {
        super(nm, reg_id);
        this.st_id = st_id;
        this.dept = dept;
        this.school = school;
        this.GPA = gpa;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %5s, reg_id: %5d, school: %5s, dept: %5s, st_id: %5d, GPA: %5.2f)",
                getName(), getRegID(), school, dept, st_id, GPA);
    }

    public int compareStudent(String key_attr, Student other) {
        return switch (key_attr) {
            case "name" -> this.getName().compareTo(other.getName());
            case "st_id" -> Integer.compare(this.st_id, other.st_id);
            case "GPA" -> Double.compare(this.GPA, other.GPA);
            default -> 0;
        };
    }
}
