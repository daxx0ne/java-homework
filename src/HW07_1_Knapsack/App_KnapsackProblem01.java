/*
 * Homework 7.1 동적프로그래밍 기반 배낭문제, 파일 입출력
 * 이름: 박다원
 * 학번: 21912154
 */

package HW07_1_Knapsack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App_KnapsackProblem01 {
    public static void main(String[] args) throws IOException {
        KSP_Solution sol;
        int knapsack_capacity = 20;
        final String f_items = "/Users/daone/IdeaProjects/java-homework/src/HW07_1_Knapsack/KSP_items.txt";

        // 아이템 정보 가져오기
        Scanner fin = new Scanner(new File(f_items));
        Knapsack01 kp = new Knapsack01(knapsack_capacity, fin);
        System.out.printf("Knapsack01 (capacity = %d) is initialized for %d items\n", knapsack_capacity, kp.items.length);
        fin.close();
        System.out.print(" Processing to obtain best solution by bruteforce() :\n ");
        sol = kp.BruteForce_KP01();
        System.out.print("Solution obtained by bruteforce() :\n ");
        kp.printSolution(sol);
        System.out.print(" Processing to obtain best solution by DynKSP_BottomUp() :\n ");
        sol = kp.DynKSP_BottomUp();
        System.out.print("Solution obtained by DynKSP_BottomUp() :\n ");
        kp.printSolution(sol);
        System.out.print(" Processing to obtain best solution by DynKSP_TopDown() :\n ");
        sol = kp.DynKSP_TopDown();
        System.out.print("Solution obtained by DynKSP_TopDown() :\n ");
        kp.printSolution(sol);
    }
}
