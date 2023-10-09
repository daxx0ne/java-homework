package HW05_1_File_IO;

import java.io.*;
import java.util.Scanner;

public class StudentArray {
    private Student[] students;
    private int num_students;

    public StudentArray() {
        students = new Student[10];
        num_students = 0;
    }

    public void fget_students(Scanner fin) {
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] parts = line.split(" ");

            if (parts.length != 6) {
                System.out.println("Invalid data format: " + line);
                continue;
            }

            String name = parts[0];
            int reg_id = Integer.parseInt(parts[1]);
            int st_id = Integer.parseInt(parts[2]);
            String dept = parts[3];
            String school = parts[4];
            double gpa = Double.parseDouble(parts[5]);

            students[num_students] = new Student(name, reg_id, st_id, dept, school, gpa);
            num_students++;
        }
    }


    public void print_students() {
        System.out.println("Total " + num_students + " students :");
        for (int i = 0; i < num_students; i++) {
            System.out.println(students[i]);
        }
    }


    public void fprint_students(FileWriter fout) throws IOException {
        fout.write("Total " + num_students + " students :\n");
        fout.flush();
        for (int i = 0; i < num_students; i++) {
            fout.write(students[i].toString() + "\n");
        }
    }


    public void sort(String key_attr, String sorting_order) {
        for (int i = 1; i < num_students; i++) {
            Student currentStudent = students[i];
            int j = i - 1;

            while (j >= 0 && (sorting_order.equals("increasing")
                    ? students[j].compareStudent(key_attr, currentStudent) > 0
                    : students[j].compareStudent(key_attr, currentStudent) < 0)) {
                students[j + 1] = students[j];
                j--;
            }

            students[j + 1] = currentStudent;
        }
    }
}
