/*
 * Homework 3.2 정렬 알고리즘의 경과시간 비교
 * 이름: 박다원
 * 학번: 21912154
 */

package HW03_2_Compare_Sorting_Algorithms;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Compare_Sorting_Algorithms {
    private static final int MIN_RANDOM_VALUE = 0;

    /* 중복되지 않는 정수형 난수 배열을 생성하는 함수 */
    public static int[] genBigRandIntArray(int size, int offset) {
        int[] randomArr = new int[size];
        HashSet<Integer> unique = new HashSet<>(); // 중복 값 방지

        Random rand = new Random(); // 난수 범위 설정
        int minValue = MIN_RANDOM_VALUE + offset;
        int maxValue = size + offset -1;

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

    /* 배열을 무작위로 섞어주는 함수*/
    public static void shuffle_array(int[] array, int size) {
        int j, temp;
        for (int i = 0; i < size; i++) {
            j = (int) (Math.random() * size);
            if (j == i)
                continue;
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /* 삽입 정렬 알고리즘으로 정렬하는 함수 */
    public static void insertion_sort(int[] array) {
        int temp, in, out, size = array.length; // 배열의 크기를 저장
        for (out = 1; out < size; out++) {
            temp = array[out]; // 현재 위치의 값을 temp에 저장
            in = out; // 현재 위치를 저장
            while (in > 0 && array[in - 1] > temp) { // 정렬이 안된 부분의 값을 하나씩 가져와서
                array[in] = array[in - 1];
                in--;
            }
            array[in] = temp; // 정렬이 된 부분에 넣어서 정렬
        }
    }

    /* 선택 정렬 알고리즘으로 정렬하는 함수 */
    public static void selection_sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) { // 최솟값 찾기
                if (array[j] < array[minIndex]) { // 지정해둔 최솟값보다 작은 값을 찾았으면
                    minIndex = j; // 해당 값으로 인덱스를 변경
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    /* 병합 정렬 알고리즘으로 정렬하는 함수 */
    public static void merge_sort(int[] array) {
        _merge_sort(array, 0, array.length - 1);
    }

    /* 병합 정렬 내부 함수 - 배열을 나누고 정렬하는 함수*/
    public static void _merge_sort(int[] array, int left, int right) {
        if (left < right) { // 배열이 나누어질 수 있는지를 확인해야함
            int middle = (left + right) / 2; // 배열을 왼쪽과 오른쪽, 절반으로 나누기
            _merge_sort(array, left, middle); // 왼쪽 정렬
            _merge_sort(array, middle + 1, right); // 오른쪽 정렬
            merge(array, left, middle, right); // 양쪽을 합침
        }
    }

    /* 병합 정렬 내부 함수 - 두 개의 배열을 하나로 합치는 함수 */
    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1; // 왼쪽 배열 크기
        int n2 = right - middle; // 오른쪽 배열 크기

        int[] leftArray = new int[n1]; // 임시로 저장
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        // 왼쪽, 오른쪽 둘 중 더 작은 값을 배열에 저장
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /* 퀵 정렬 알고리즘으로 정렬하는 함수 */
    public static void quick_sort(int[] array) {
        _quick_sort(array, 0, array.length - 1);
    }

    /* 퀵 정렬 내부 함수 - 정수 배열을 퀵 정렬 */
    public static void _quick_sort(int[] array, int left, int right) {
        while (left < right) { // 스택 오버플로우 방지를 위해 반복문 사용
            int partitionIndex = _partition(array, left, right); // 배열을 두 개로 분할

            if (partitionIndex - left < right - partitionIndex) {
                _quick_sort(array, left, partitionIndex - 1);
                left = partitionIndex + 1;
            } else {
                _quick_sort(array, partitionIndex + 1, right);
                right = partitionIndex - 1;
            }
        }
    }

    /* 퀵 정렬 내부 함수 - 배열 분할 */
    public static int _partition(int[] array, int left, int right) {
        int pivot = array[right]; // 선택한 pivot 값 가져옴
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

        /* 정렬 알고리즘 경과시간 비교 기능을 위한 main 함수 */
        public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int big_size;
        int offset = 0;
        int[] bigRandIntArray;
        long t1, t2, elapsed_time_ms;
        while (true) {
            System.out.print("input big_size (> 32767) to compare performances of (quick, merge, insertion, selection) sorting algorithms (0 to terminate) : ");
            big_size = cin.nextInt();
            if (big_size == 0)
                break;
            bigRandIntArray = genBigRandIntArray(big_size, offset);

            /* 퀵 정렬 경과시간 계산 */
            shuffle_array(bigRandIntArray, big_size);
            System.out.printf("Before quick_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size, 10, 2);
            t1 = System.currentTimeMillis();
            quick_sort(bigRandIntArray);
            t2 = System.currentTimeMillis();
            elapsed_time_ms = t2 - t1;
            System.out.printf("After quick_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            System.out.printf("quick_sort() for intArray(size=%d) took %d milliseconds\n\n", big_size, elapsed_time_ms);

            /* 병합 정렬 경과시간 계산 */
            shuffle_array(bigRandIntArray, big_size);
            System.out.printf("Before merge_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            t1 = System.currentTimeMillis();
            merge_sort(bigRandIntArray);
            t2 = System.currentTimeMillis();
            elapsed_time_ms = t2 - t1;
            System.out.printf("After merge_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            System.out.printf("merge_sort() for intArray(size=%d) took %d milliseconds\n\n", big_size, elapsed_time_ms);

            /* 삽입 정렬 경과시간 계산 */
            shuffle_array(bigRandIntArray, big_size);
            System.out.printf("Before insertion_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            t1 = System.currentTimeMillis();
            insertion_sort(bigRandIntArray);
            t2 = System.currentTimeMillis();
            elapsed_time_ms = t2 - t1;
            System.out.printf("After insertion_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            System.out.printf("Insertion_sort() for intArray(size=%d) took %d milliseconds\n\n", big_size, elapsed_time_ms);
            /* 선택 정렬 경과시간 계산 */
            shuffle_array(bigRandIntArray, big_size);
            System.out.printf("Before selection_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            t1 = System.currentTimeMillis();
            selection_sort(bigRandIntArray);
            t2 = System.currentTimeMillis();
            elapsed_time_ms = t2 - t1;
            System.out.printf("After selection_sorting, size = %d, offset = %d\n", big_size, offset);
            printBigArraySample(bigRandIntArray, big_size,10, 2);
            System.out.printf("Selection_sort() for intArray(size=%d) took %d milliseconds\n\n", big_size, elapsed_time_ms);
        }
    }
}