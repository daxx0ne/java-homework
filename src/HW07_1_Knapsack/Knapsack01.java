package HW07_1_Knapsack;

import java.util.ArrayList;
import java.util.Scanner;

/* Knapsack 문제 해결을 위한 클래스 */
public class Knapsack01 {
    Item[] items; // 배낭에 넣을 아이템
    int capacity; // 배낭의 최대 무게

    public Knapsack01(int capacity, Scanner fin) {
        this.capacity = capacity;
        int numItems = fin.nextInt(); // 파일에 담긴 아이템 정보 읽기
        items = new Item[numItems];
        for (int i = 0; i < numItems; i++) {
            String name = fin.next();
            int value = fin.nextInt();
            int weight = fin.nextInt();
            items[i] = new Item(name, value, weight);
        }
    }

    /**
     * BruteForce_KP01 (브루트포스 알고리즘):
     * 가능한 모든 부분 집합을 검사하여 최적의 해를 찾는 방식
     * 가능한 모든 아이템 조합을 생성하고, 배낭의 용량을 초과하지 않는 경우 중 가장 가치가 높은 조합을 선택
     * 최적해를 찾는 것은 보장하지만, 아이템 수가 많을 때 효율성이 떨어질 수 있음

     * DynKSP_BottomUp (다이나믹 프로그래밍, Bottom-Up 방식):
     * 다이나믹 프로그래밍을 사용하여 최적의 해를 찾는 방식
     * 2D 배열을 사용하여 각 상황에서 최적해를 저장
     * 각 상황에서 현재 아이템을 선택하거나 선택하지 않는 두 가지 경우를 고려하며, 최적해를 계산

     * DynKSP_TopDown (다이나믹 프로그래밍, Top-Down 방식):
     * 다이나믹 프로그래밍을 사용하여 최적의 해를 찾는 방식
     * 상황을 저장하고 중복 계산을 피하기 위해 solutionTable을 활용
     * 재귀적인 방식으로 최적해를 계산하며, DynKSP_TopDown 메서드를 호출하여 최종 결과를 얻음

     * _dynKSP_recursive (재귀적 다이나믹 프로그래밍, Top-Down 방식):
     * 다이나믹 프로그래밍을 사용하여 최적의 해를 찾는 방식
     * 재귀적인 방식으로 최적해를 계산함, 중복 계산을 피하기 위해 해를 저장하는 테이블을 활용
     * 최상위 메서드인 DynKSP_TopDown에서 재귀적으로 호출됨, 모든 상황에 대한 최적해를 계산함
     */

    /* 브루트포스 알고리즘을 이용하여 해결하는 메서드 */
    public KSP_Solution BruteForce_KP01() {
        ArrayList<ArrayList<Item>> powerSet = new ArrayList<ArrayList<Item>>();
        ArrayList<Item> subSet_items;
        StringBuilder binStr;
        int num_items = this.items.length;

        for (int i = 0; i < Math.pow(2, num_items); i++) { // 가능한 모든 부분집합을 생성함
            binStr = new StringBuilder(Integer.toBinaryString(i));
            if (binStr.length() > num_items) {
                System.out.print("getBinaryRepr::num_digits is not enough !!");
            }
            int binStr_length = binStr.length();
            for (int j = 0; j < (num_items - binStr_length); j++) {
                binStr.insert(0, "0");
            }
            subSet_items = new ArrayList<Item>();
            for (int j = 0; j < num_items; j++) {
                if (binStr.charAt(j) == '1') {
                    subSet_items.add(this.items[j]);
                }
            }
            powerSet.add(subSet_items);
        }

        int bestSubsetValue = 0, bestSubsetWeight = 0;
        int subsetValue, subsetWeight;
        ArrayList<Item> bestSubset = null;

        for (ArrayList<Item> subSet : powerSet) { // 만든 부분집합 중에서 제일 좋은 결과 찾기
            subsetValue = 0;
            subsetWeight = 0;
            for (Item e : subSet) {
                subsetValue += e.value;
                subsetWeight += e.weight;
            }
            if ((subsetWeight <= this.capacity) && (subsetValue > bestSubsetValue)) {
                bestSubsetValue = subsetValue;
                bestSubset = subSet;
                bestSubsetWeight = subsetWeight;
            }
        }

        // 결과를 KSP_Solution 객체에 저장 후 반환
        KSP_Solution solution = new KSP_Solution();
        solution.selectedItems = bestSubset;
        solution.totalValue = bestSubsetValue;
        solution.totalWeight = bestSubsetWeight;

        return solution;
    }

    /* 다이나믹 프로그래밍의 Bottom-up 방식을 이용하여 해결하는 메서드 */
    public KSP_Solution DynKSP_BottomUp() {
        KSP_Solution[][] m = new KSP_Solution[this.items.length + 1][this.capacity + 1]; // 표 생성
        for (int k = 0; k <= this.items.length; k++) {
            for (int w = 0; w <= this.capacity; w++) {
                m[k][w] = new KSP_Solution();
                m[k][w].selectedItems = new ArrayList<Item>();
            }
            m[k][0].totalValue = 0;
            m[k][0].totalWeight = 0;
        }
        for (int w = 0; w <= this.capacity; w++) {
            m[0][w].totalValue = 0;
            m[0][w].totalWeight = 0;
        }

        for (int k = 1, i = 0; k <= this.items.length; k++, i++) {
            for (int w = 1; w <= this.capacity; w++) {
                if (this.items[i].weight > w) {
                    m[k][w] = m[k - 1][w]; // 아이템을 배낭에 포함 x
                } else {
                    if (m[k - 1][w].totalValue > (m[k - 1][w - this.items[i].weight].totalValue + this.items[i].value)) {
                        m[k][w] = m[k - 1][w]; // 아이템을 배낭에 포함 x
                    } else {
                        m[k][w].selectedItems.addAll(m[k - 1][w].selectedItems);
                        m[k][w].selectedItems.add(this.items[i]);
                        m[k][w].totalValue = m[k - 1][w - this.items[i].weight].totalValue + this.items[i].value;
                        m[k][w].totalWeight = m[k - 1][w - this.items[i].weight].totalWeight + this.items[i].weight;
                    }
                }
            }
        }

        // 최적의 해를 KSP_Solution 객체에 저장 후 반환
        KSP_Solution solution = new KSP_Solution();
        solution = m[this.items.length][this.capacity];
        return solution;
    }

    /* 다이나믹 프로그래밍의 Top-Down 방식을 이용하여 해결하는 메서드 */
    public KSP_Solution DynKSP_TopDown() {
        KSP_Solution[][] solutionTable = new KSP_Solution[this.items.length][this.capacity + 1]; // 아이템의 인덱스와 배낭의 남은 용량을 인덱스로 사용
        for (int k = 0; k < this.items.length; k++) {
            for (int w = 1; w <= this.capacity; w++) {
                solutionTable[k][w] = null; // 배열을 사용하여 각 상황에서의 최적 해를 저장함
            }
            solutionTable[k][0] = new KSP_Solution();
            solutionTable[k][0].selectedItems = new ArrayList<Item>();
            solutionTable[k][0].totalValue = 0;
            solutionTable[k][0].totalWeight = 0;
        }
        int index_start = 0;
        KSP_Solution solution = _dynKSP_recursive(index_start, this.capacity, solutionTable); // _dynKSP_recursive 를 호출하여 결과 얻기
        return solution;
    }

    /* Top-Down 방식 - 재귀적으로 호출되는 메서드 */
    public KSP_Solution _dynKSP_recursive(int index, int avail, KSP_Solution[][] solTbl) {
        KSP_Solution solution = new KSP_Solution();
        if ((index >= this.items.length) || (avail <= 0)) { // 재귀적으로 호출하면서, 아이템을 배낭에 포함하는, 하지 않는 경우를 모두 고려함
            solution = new KSP_Solution();
            solution.selectedItems = new ArrayList<Item>();
            solution.totalValue = 0;
            solution.totalWeight = 0;
            return solution;
        } else if (solTbl[index][avail] != null) { // 중복 계산을 피하기 위해 테이블을 활용함
            return solTbl[index][avail];
        } else if (this.items[index].weight > avail) {
            // _dynKSP_recursive 호출을 통하여 최적의 해를 계산
            solution = _dynKSP_recursive(index + 1, avail, solTbl); // 현재 아이템을 배낭에 포함 x
        } else {
            Item candidateItem = this.items[index];
            KSP_Solution solution_with = new KSP_Solution();
            KSP_Solution solution_without = new KSP_Solution();
            solution_with = _dynKSP_recursive(index + 1, avail - candidateItem.weight, solTbl); // 현재 아이템을 배낭에 포함 o
            solution_with.totalValue += candidateItem.value;
            solution_with.totalWeight += candidateItem.weight;
            solution_with.selectedItems.add(candidateItem);
            solution_without = _dynKSP_recursive(index + 1, avail, solTbl); // 현재 아이템을 배낭에 포함 x
            if (solution_with.totalValue > solution_without.totalValue) {
                solution = solution_with;
            } else {
                solution = solution_without;
            }
        }
        solTbl[index][avail] = new KSP_Solution();
        solTbl[index][avail].selectedItems = solution.selectedItems;
        solTbl[index][avail].totalValue = solution.totalValue;
        solTbl[index][avail].totalWeight = solution.totalWeight;

        // 모든 경우에서의 최적의 해를 반환
        return solution;
    }

    /* 최적 해(solution)를 화면으로 출력하는 멤버 함수 */
    public void printSolution(KSP_Solution solution) {
        if (solution != null) {
            System.out.println("Total value = " + solution.totalValue + ", Total weight = " + solution.totalWeight + ", Selected items = " + solution.selectedItems);
        } else {
            System.out.println("No solution found.");
        }
    }

}