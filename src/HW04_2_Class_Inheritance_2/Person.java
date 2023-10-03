package HW04_2_Class_Inheritance_2;

/* 사람의 이름과 ID 정보 추상 클래스 */
public abstract class Person implements PersonActivity {
    private String name; // 이름
    private int reg_id; // ID

    // 클래스 생성자
    public Person(String name, int reg_id) {
        this.name = name;
        this.reg_id = reg_id;
    }

    // 이름 반환
    public String getName() {
        return name;
    }

    // ID 반환
    public int getRegID() {
        return reg_id;
    }
}
