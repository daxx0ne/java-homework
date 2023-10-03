package HW04_2_Class_Inheritance_2;

/* 학생 정보를 나타내는 클래스 */
public class Student extends Person implements StudentActivity {
    private int st_id; // 학번
    private double gpa; // 학점
    private String school; // 학교
    private String major; // 전공

    // 클래스 생성자
    public Student(String nm, int reg_id, String school, String major, int st_id, double st_gpa) {
        super(nm, reg_id);
        this.school = school;
        this.major = major;
        this.st_id = st_id;
        this.gpa = st_gpa;
    }

    // 학번 반환
    public int getStID() {
        return st_id;
    }

    // 학점 반환
    public double getGPA() {
        return gpa;
    }

    public String toString() {
        return "Student (" + getName() + ", " + getRegID() + ", " + school + ", " + major + ", " + getStID() + ")";
    }

    // '듣기, 말하기, 움직이기, 공부하기' 행동 메서드
    @Override
    public void listen() {
        System.out.println("student (" + getName() + ", " + getStID() + ") :: listening ...");
    }

    @Override
    public void talk() {
        System.out.println("student (" + getName() + ", " + getStID() + ") :: talking ...");
    }

    @Override
    public void move() {
        System.out.println("student (" + getName() + ", " + getStID() + ") :: moving ...");
    }

    @Override
    public void study() {
        System.out.println("student (" + getName() + ", " + getStID() + ") :: studying ...");
    }
}
