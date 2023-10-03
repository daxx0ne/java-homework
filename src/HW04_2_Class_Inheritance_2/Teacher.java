package HW04_2_Class_Inheritance_2;

/* 선생님 정보를 나타내는 클래스 */
public class Teacher extends Person implements TeacherActivity {
    String school; // 학교
    String major; // 전공
    String lecture; // 강의

    // 클래스 생성자
    public Teacher(String nm, int reg_id, String school, String major, String lecture) {
        super(nm, reg_id);
        this.school = school;
        this.major = major;
        this.lecture = lecture;
    }

    // 전공 반환
    public String getMajor() {
        return major;
    }

    public String toString() {
        return "Teacher (" + getName() + ", " + getRegID() + ", " + school + ", " + major + ")";
    }

    // '듣기, 말하기, 움직이기, 가르치기' 행동 메서드
    @Override
    public void listen() {
        System.out.println("teacher (" + getName() + ", " + major + ") :: listening ...");
    }

    @Override
    public void talk() {
        System.out.println("teacher (" + getName() + ", " + major + ") :: talking ...");
    }

    @Override
    public void move() {
        System.out.println("teacher (" + getName() + ", " + major + ") :: moving ...");
    }

    @Override
    public void teach() {
        System.out.println("teacher (" + getName() + ", " + major + ") :: teaching ...");
    }
}
