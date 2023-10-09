package HW05_1_File_IO;

/* 학생 정보 관리를 위한 클래스 */
public class Student extends Person {
    private final String school; // 학교
    private final String dept; // 학과
    private final int st_id; // 학번
    private final double GPA; // 학점

    // 생성자
    public Student(String nm, int reg_id, String school, String dept, int st_id, double gpa) {
        super(nm, reg_id); // 상위 클래스인 Person 의 생성자를 호출하여 이름과 등록 id 설정
        this.school = school;
        this.dept = dept;
        this.st_id = st_id;
        this.GPA = gpa;
    }

    @Override
    public String toString() { // 문자열로 객체 표현
        return String.format("Student(name: %5s, reg_id: %5d, school: %5s, dept: %5s, st_id: %5d, GPA: %5.2f)",
                getName(), getRegID(), school, dept, st_id, GPA);
    }

    public int compareStudent(String key_attr, Student other) { // 정렬할 때 어떤 속성으로 정렬할지
        return switch (key_attr) {
            case "name" -> this.getName().compareTo(other.getName()); // 이름 순
            case "st_id" -> Integer.compare(this.st_id, other.st_id); // 학번 순
            case "GPA" -> Double.compare(this.GPA, other.GPA); // 학점 순
            default -> 0; // 기본 값
        };
    }
}
