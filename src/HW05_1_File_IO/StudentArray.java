package HW05_1_File_IO;

import java.io.*;
import java.util.Scanner;

/* Student 의 인스턴스들을 배열로 관리하는 클래스 */
public class StudentArray {

    // 데이터 멤버
    private Student[] students; // Student 객체 배열
    private int num_students;

    // 생성자
    public StudentArray() {
        students = new Student[5]; // 총 학생이 5명이기 때문에 5 크기의 배열을 생성
        num_students = 0;
    }

    /* 입력 파일의 학생 정보를 읽고 배열에 저장하는 메서드 */
    public void fget_students(Scanner fin) {
        while (fin.hasNextLine()) {
            String line = fin.nextLine(); // 한줄 씩 읽음
            String[] parts = line.split(" "); // 공백 문자로 분할하여 문자열 배열에 저장
            if (parts.length != 6) { // 학생 정보는 총 6가지의 정보를 담고 있음, 이게 아니라면 다음 줄을 읽도록 함
                System.out.println("Invalid data format: " + line);
                continue;
            }
            String name = parts[0]; // 한 줄에서 학생의 정보를 추출함
            int reg_id = Integer.parseInt(parts[1]);
            int st_id = Integer.parseInt(parts[2]);
            String dept = parts[3];
            String school = parts[4];
            double gpa = Double.parseDouble(parts[5]);

            students[num_students] = new Student(name, reg_id, school, dept, st_id, gpa); // 객체 생성 및 student 배열에 추가
            num_students++; // 총 학생 수 체크
        }
    }

    /* 배열에 저장된 학생의 정보를 콘솔에 출력하는 메서드 */
    public void print_students() {
        System.out.println("Total " + num_students + " students :"); // 총 학생 수 출력
        for (int i = 0; i < num_students; i++) {
            System.out.println(students[i]);
        }
    }

    /* 배열에 저장된 학생의 정보를 파일에 출력하는 메서드 */
    public void fprint_students(FileWriter fout) throws IOException {
        fout.write("Total " + num_students + " students :\n"); // 총 학생 수 출력
        fout.flush();
        for (int i = 0; i < num_students; i++) {
            fout.write(students[i].toString() + "\n");
        }
    }

    /* 학생 정보를 속성에 따라서 정렬하는 메서드 */
    public void sort(String key_attr, String sorting_order) {
        for (int i = 1; i < num_students; i++) { // 삽입 정렬 알고리즘
            Student currentStudent = students[i];
            int j = i - 1;
            while (j >= 0 && (sorting_order.equals("increasing")
                    ? students[j].compareStudent(key_attr, currentStudent) > 0 // sorting order 가 increasing 일 때 실행
                    : students[j].compareStudent(key_attr, currentStudent) < 0)) { // sorting order 가 decreasing 일 때 실행
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = currentStudent;
        }
    }
}
