package HW04_1_Class_Inheritance_1;

/* 운전자 정보 클래스 */
public class Person {
    private String name;
    private int reg_id;

    // 생성자
    public Person(String name, int reg_id) {
        this.name = name;
        this.reg_id = reg_id;
    }

    // Override 를 통해 객체 정보를 문자열로 반환함
    @Override
    public String toString() {
        return "Name: " + name + ", Registration ID: " + reg_id;
    }

    // 운전자명을 반환
    public String getName() {
        return name;
    }
}