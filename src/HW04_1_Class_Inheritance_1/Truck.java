package HW04_1_Class_Inheritance_1;

public class Truck extends Vehicle {
    private int cargo_capa;

    // Vehicle 클래스를 상속받음
    public Truck(String color, String vtype, int eng_capa, Person driver, int cargo_capa) {
        super(color, "Truck", eng_capa, driver);
        this.cargo_capa = cargo_capa;
    }

    // 화물 용량 반환
    public int getCagoCapa() {
        return this.cargo_capa;
    }

    // Vehicle 클래스의 toString()을 호출하여 트럭 정보 출력
    public String toString() {
        return super.toString();
    }

    // 트럭의 동작(주행 + 속도, 회전, 정지)을 출력
    @Override
    public void forward(int speed) {
        System.out.print("moving forward at speed " + speed + " Km/h;  ");
    }

    @Override
    public void turn(int angle) {
        System.out.print("turning " + angle + " degree;  ");
    }

    @Override
    public void stop() {
        System.out.println("stopping;");
    }
}
