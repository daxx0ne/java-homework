/*
 * Homework 4.1 class Vehicle, Sedan, Bus, Truck
 * 이름: 박다원
 * 학번: 21912154
 */

package HW04_1_Class_Inheritance_1;

public class App_Vehicle_Sedan_Bus_Truck {
    public static void main(String[] args) {
        // Person(운전자) 객체 생성
        Person[] drivers = {new Person("Kim", 11111),
                new Person("Park", 22222), new Person("Choi", 33333)};

        // Vehicle(세단, 버스, 트럭) 객체 생성
        Vehicle[] vehicles = {
                new Sedan("Red", "SUV", 3000, drivers[0], 5),
                new Bus("Yellow", "CityBus", 50000, drivers[1], 50),
                new Truck("Black", "CagoTruck", 100000, drivers[2], 200)
        };

        // 차량 목록, 동작 출력
        System.out.print("Vehicles : ");
        for (Vehicle v : vehicles) {
            System.out.print(v);
            if (v != vehicles[vehicles.length - 1])
                System.out.print(", ");
        }
        System.out.println();
        System.out.println("\nDriving of Vehicles :");
        for (Vehicle v : vehicles) {
            System.out.printf("%s :: ", v);
            v.forward(100);
            v.turn(90);
            v.stop();
        }
    }
}

