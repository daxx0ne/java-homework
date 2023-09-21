/*
 * Homework 3.1 class IntStack
 * 이름: 박다원
 * 학번: 21912154
 */

package HW03_1_IntStack;

public class IntStack {
    private int[] int_array; // 정수 배열 저장
    private int max_stack_size; // 스택의 최대 크기
    private int stack_top = -1; // 스택 초기 위치

    /* 객체 초기화 */
    public IntStack(int max_stack_size) {
        this.max_stack_size = max_stack_size; // 스택의 최대 크기 설정
        this.int_array = new int[max_stack_size]; // 크기에 맞는 정수 배열 생성
    }

    /* 스택에 값을 push(추가) */
    public int push(int entry) {
        int_array[++stack_top] = entry;
        return entry;
    }

    /* 스택에서 값을 pop(제거 및 반환) */
    public int pop() {
        return int_array[stack_top--];
    }

    /* 스택이 비었는지 확인 */
    public boolean isEmpty() {
        return stack_top == -1;
    }

    /* 스택이 다 차있는지 확인 */
    public boolean isFull() {
        return stack_top == max_stack_size - 1;
    }

    /* 스택 출력하기 */
    public void print() {
        if (isEmpty()) {
            System.out.println(" ");
            return;
        }
        for (int i = stack_top; i >= 0; i--) { // Print in reverse order
            System.out.printf("%3d ", int_array[i]);
        }
    }
}