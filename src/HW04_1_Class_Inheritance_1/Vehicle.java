package HW04_1_Class_Inheritance_1;

/* 추상 클래스 */
public abstract class Vehicle implements I_Drive {
    private String v_type;
    private int eng_capa;
    private String v_color;
    private Person driver;

    public Vehicle(String color, String vtype, int eng_capa, Person driver) {
        this.v_color = color;
        this.v_type = vtype;
        this.eng_capa = eng_capa;
        this.driver = driver;
    }

    // 차량의 정보(색, 타입, 운전자명) 반환
    public String toString() {
        return v_color + " " + v_type + " (driver: " + driver.getName() + ")";
    }

    public String getVtype() {
        return v_type;
    }

    public int getEngCapa() {
        return eng_capa;
    }

    public String getColor() {
        return v_color;
    }

    public Person getDriver() {
        return driver;
    }
}
