/*
 * Homework 1.2 Dynamic Fibonacci 수열 계산 프로그램 작성
 * 이름: 박다원
 * 학번: 21912154
 */

package HW01_2_DynFibonacci;

import java.util.Scanner;

public class Dyn_Fibonacci {
    final static int max_n = 1000;
    static double[] fb_n_tbl = new double[max_n];
    static boolean fb_n_tbl_initialized = false;

    public static double Dyn_Fibonacci(int n) {
        if (!fb_n_tbl_initialized) {
            fb_n_tbl[0] = 0; // 0번째 항
            fb_n_tbl[1] = 1; // 1번째 항
            for (int i = 2; i < max_n; i++) { // 2번째 항부터 max_n(999)번째 항까지 계산하여 배열에 저장
                fb_n_tbl[i] = fb_n_tbl[i - 1] + fb_n_tbl[i - 2];
            }
            // 반복문 종료 시 0~999번째 피보나치 수열이 배열에 저장 되어있기 때문에
            fb_n_tbl_initialized = true; // 함수 재 호출 시 조건문을 실행하지 않도록 true 로 바꿔줌
        }
        return fb_n_tbl[n]; // n번째 항을 반환
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n;
        double fibo_n;
        while (true) {
            System.out.print("input n (>= 0) to find 0 ~ nth fibo_n (-1 to terminate) : ");
            n = cin.nextInt();
            if (n == -1)
                break;
            for (int i = 0; i <= n; i++) {
                fibo_n = Dyn_Fibonacci(i);
                System.out.printf("%3d-th Fibonacci Series = %25.0f\n", i, fibo_n);
            }
        }
        cin.close();
    }
}