/*
 * Homework 2.1 BigRandIntArray 생성, 퀵정렬, 샘플 출력
 * 이름: 박다원
 * 학번: 21912154
 */

package HW02_1_BigRandIntArray_QuickSort;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class BigRandArray_QuickSort_Ex {
    private static final int MIN_RANDOM_VALUE = 0;

    /* 중복되지 않는 정수형 난수 배열을 생성하는 함수 */
    public static int[] genBigRandIntArray(int size, int offset) {
        int[] randomArr = new int[size];
        HashSet<Integer> unique = new HashSet<>(); // 중복 값 방지

        Random rand = new Random(); // 난수 범위 설정
        int minValue = MIN_RANDOM_VALUE + offset;
        int maxValue = size + offset;

        for (int i = 0; i < size; i++) {
            int randValue;
            do { // 범위 내에서 난수 생성
                randValue = rand.nextInt(maxValue - minValue + 1) + minValue;
            } while (unique.contains(randValue)); // 중복 검사

            unique.add(randValue); // 중복되지 않는 값 저장
            randomArr[i] = randValue;
        }

        return randomArr; // 난수 배열 반환
    }

    /* 첫부분과 끝부분에서 한 줄에 per_line 개씩의 원소를 sample_lines 줄씩 출력하는 함수 */
    public static void printBigArraySample(int[] bigArray, int size, int per_line, int sample_lines) {
        int totalLines = (size + per_line - 1) / per_line; // 총 줄 수 계산
        int digit = String.valueOf(size).length(); // 정수 출력 자릿수 계산 (깔끔하게 출력하기 위해서)

        // 처음 per_line 개씩의 원소를 sample_lines 줄씩 출력
        int printNum = Math.min(sample_lines, totalLines);
        for (int i = 0; i < printNum; i++) {
            int startIdx = i * per_line;
            int endIdx = Math.min(startIdx + per_line, size);

            for (int j = startIdx; j < endIdx; j++) { // 한 줄에 per_line 개씩의 원소 출력
                System.out.printf("%" + digit + "d ", bigArray[j]);
            }

            System.out.println();
        }

        // 총 줄 수가 sample_lines * 2 보다 크면, 중간에 " . . . . ." (생략 표시) 출력
        if (totalLines > sample_lines * 2) {
            System.out.println("   . . . . .");
        }

        // 끝에서 per_line 개씩의 원소를 sample_lines 줄씩 출력
        printNum = Math.min(sample_lines, totalLines - sample_lines);
        for (int i = totalLines - printNum; i < totalLines; i++) {
            int startIdx = (size - (totalLines - i) * per_line);
            int endIdx = Math.min(startIdx + per_line, size);

            // 한 줄에 per_line 개씩의 원소 출력
            for (int j = startIdx; j < endIdx; j++) {
                System.out.printf("%" + digit + "d ", bigArray[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /* 퀵 정렬 내부 함수 - 배열 분할 */
    public static int _partition(int[] array, int size, int left, int right, int pivotIndex) {
        int pivot = array[pivotIndex]; // 선택한 pivot 값 가져옴
        int i = left - 1;

        for (int j = left; j < right; j++) { // pivot 보다 작은 원소를 찾고 왼쪽으로 이동
            if (array[j] < pivot) {
                i++;

                // 작은 원소와 큰 원소를 교환함
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // pivot 을 작은 원소들과 큰 원소들 사이로 이동함
        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1; // 새로운 pivot 위치를 반환함
    }

    /* 퀵 정렬 내부 함수 - 정수 배열을 퀵 정렬 */
    public static void _quickSort(int[] array, int size, int left, int right) {
        while (left < right) { // 스택 오버플로우 방지를 위해 반복문 사용
            int partitionIndex = _partition(array, size, left, right, right); // 배열을 두 개로 분할

            if (partitionIndex - left < right - partitionIndex) {
                _quickSort(array, size, left, partitionIndex - 1);
                left = partitionIndex + 1;
            } else {
                _quickSort(array, size, partitionIndex + 1, right);
                right = partitionIndex - 1;
            }
        }
    }

    /* 정수형 배열의 퀵 정렬 알고리즘으로 정렬하는 함수 */
    public static void quickSort(int[] array, int size) {
        _quickSort(array, size, 0, size-1); // 함수 호출하여 배열 정렬
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int big_size;
        int offset = 0;
        int[] bigRandIntArray;
        while (true) {
            System.out.print("input big_size (> 32767) to generate non-duplicated random big integer array (0 to terminate) : ");
            big_size = cin.nextInt();
            if (big_size == 0)
                break;
            bigRandIntArray = genBigRandIntArray(big_size, offset);
            System.out.printf("Before sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size, 10, 2);
            quickSort(bigRandIntArray, big_size);
            System.out.printf("After sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size, 10, 2);
        }
    }
}


