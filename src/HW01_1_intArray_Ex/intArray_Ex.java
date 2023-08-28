/*
 * Homework 1.1 정수 배열의 입력, 삽입 정렬, 출력
 * 이름: 박다원
 * 학번: 21912154
 */

package HW01_1_intArray_Ex;

import java.util.Scanner;

public class intArray_Ex {

    /* 출력 함수 */
    public static void print_array(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " "); // 배열의 원소를 하나씩 출력
        }
        System.out.println(); // 줄바꿈
    }

    /* 입력 함수 */
    public static int[] get_int_array(Scanner cin, int size) {
        int[] array = new int[size]; // 정수 배열 생성
        for (int i = 0; i < size; i++) {
            array[i] = cin.nextInt(); // 입력 받은 정수 값을 배열에 저장
        }
        return array; // 배열 반환
    }

    /* 삽입 정렬 함수 */
    public static void insertion_sort(int[] array, int size) {
        for (int i = 1; i < size; i++) {
            int temp = array[i]; // 현재 정렬하려는 값
            int j = i - 1; // 이미 정렬된 부분의 마지막 원소 인덱스

            // 현재 원소를 이미 정렬된 부분에 삽입하기 위한 위치를 찾는 반복문
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp; // 삽입할 위치에 현재 원소 삽입
        }
    }

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        System.out.print("Please input number of integers to process : ");
        int num_data = cin.nextInt();
        int[] int_array;
        System.out.print("Please input " + num_data + " integers in a line, separated with space :");
        int_array = get_int_array(cin, num_data);
        System.out.print("Input data : ");
        print_array(int_array, num_data);
        insertion_sort(int_array, num_data);
        System.out.print("Sorted input data : ");
        print_array(int_array, num_data);
    }
}