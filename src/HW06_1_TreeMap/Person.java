package HW06_1_TreeMap;

/* 주소록(이름, 전화번호)을 나타내는 클래스 */
class Person {
    private String name; // 이름
    private TelNum telNum; // 전화번호

    // 객체 초기화
    public Person(String name, TelNum telNum) {
        this.name = name;
        this.telNum = telNum;
    }

    // 이름 반환
    public String getName() {
        return name;
    }

    // 출력 포멧
    @Override
    public String toString() {
        return String.format("Person(name=%5s, telNo=%16s)", name, telNum); // 이름은 5자리, 전화번호는 16자리
    }
}
