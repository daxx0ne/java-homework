/*
 * Homework 6.1 TreeMap<String, Person> 기반 주소록 (address book) 구현
 * 이름: 박다원
 * 학번: 21912154
 */

package HW06_1_TreeMap;

import java.util.TreeMap;
import java.util.Set;

public class App_AddressBook {
    public static void main(String[] args) {
        TreeMap<String, Person> addrBook = new TreeMap<String, Person>(); // TreeMap<String, Person>을 사용하여 addrBook을 구성

        // Person 객체 배열 생성, 주소록에 추가
        Person[] persons = {
                new Person("Kim", new TelNum(82, 53, 810, 1000)), new Person("Yoon", new TelNum(82, 2, 1234, 5678)), new Person("Lee", new TelNum(82, 51, 2579, 1234)), new Person("Park", new TelNum(82, 53, 1000, 5678)), new Person("Choi", new TelNum(82, 31, 2345, 6789)),
        };

        // 이름을 키로 설정, Person 객체를 값으로 설정
        for (Person p : persons) {
            addrBook.put(p.getName(), p);
        }

        // 저장된 주소록을 출력
        for (Person p : persons) {
            String nm = p.getName();
            System.out.printf("%5s : %s\n", nm, addrBook.get(nm));
        }

        // 키 목록만 출력
        Set<String> keySet_name = addrBook.keySet();
        System.out.printf("keySet_name = %s\n", keySet_name);

        // 키 순서대로 주소록을 출력
        for (String key : keySet_name) {
            System.out.printf("%5s : %s\n", key, addrBook.get(key));
        }
    }
}