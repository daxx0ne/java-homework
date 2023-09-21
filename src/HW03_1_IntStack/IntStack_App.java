/*
 * Homework 3.1 class IntStack (IntStack 기능을 시험하기 위한 프로그램)
 * 이름: 박다원
 * 학번: 21912154
 */

package HW03_1_IntStack;

public class IntStack_App {
    public static void main(String[] args) {
        final int stack_size = 20;
        int int_data;
        IntStack int_stack = new IntStack(stack_size);
        System.out.printf("Testing push() operation of integer stack (stack_size = %d) ....\n", stack_size);
        for (int i = 0; i < stack_size; i++) {
            int_data = (int) (Math.random() * stack_size);
            int_stack.push(int_data);
            System.out.printf("After push (%3d) : ", int_data);
            int_stack.print();
            System.out.print("\n");
        }
        System.out.printf("\nTesting pop() operation of integer stack (stack_size = %d) ....\n", stack_size);
        for (int i = 0; i < stack_size; i++) {
            int_data = int_stack.pop();
            System.out.printf("After pop (%3d) : ", int_data);
            int_stack.print();
            System.out.print("\n");
        }
    }
}