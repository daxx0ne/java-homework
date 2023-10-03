package HW04_1_Class_Inheritance_1;

/* 차량의 동작에 대해 정의하는 인터페이스 */
public interface I_Drive {
    public abstract void forward(int speed); // 주행

    public abstract void turn(int angle); // 회전

    public abstract void stop(); // 정지
}
