package HW04_1_Class_Inheritance_1;

public class Bus extends Vehicle {
    private int num_seats;
    private int num_passengers;

    // Vehicle 클래스를 상속받음
    public Bus(String color, String vtype, int eng_capa, Person driver, int num_seats) {
        super(color, "Bus", eng_capa, driver);
        this.num_seats = num_seats; // 좌석 수
        this.num_passengers = 0; // 승객 수
    }

    // Vehicle 클래스의 toString()을 호출하여 버스 정보 출력
    public String toString() {
        return super.toString();
    }

    // 버스의 동작(주행 + 속도, 회전, 정지)을 출력
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
