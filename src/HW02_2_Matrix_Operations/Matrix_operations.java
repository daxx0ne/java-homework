/*
 * Homework 2.2 행렬 (Matrix) 연산
 * 이름: 박다원
 * 학번: 21912154
 */

package HW02_2_Matrix_Operations;

public class Matrix_operations {

    /* 행렬 출력 함수 */
    public static void printMtrx(String mtrx_name, double mtrx_data[][]) {
        System.out.println(mtrx_name + " =");
        for (double[] mtrx_row : mtrx_data) { // 행
            System.out.print("\t"); // 각 행 앞에 공백 출력
            for (int j = 0; j < mtrx_data[0].length; j++) { // 열
                System.out.printf("%.2f\t", mtrx_row[j]); // 소수점 두 자리까지 출력
            }
            System.out.println(); // 줄바꿈
        }
    }

    /* 행렬 덧셈 함수 */
    public static double[][] addMtrx(double mA_data[][], double mB_data[][]) {
        int rows = mA_data.length; // 행의 개수
        int cols = mA_data[0].length; // 열의 개수
        double[][] result = new double[rows][cols]; // 행렬의 덧셈 연산 결과를 저장하는 배열

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = mA_data[i][j] + mB_data[i][j];
            }
        }

        return result; // 연산 결과 반환
    }

    /* 행렬 뺄셈 함수 */
    public static double[][] subMtrx(double mA_data[][], double mB_data[][]) {
        int rows = mA_data.length; // 행의 개수
        int cols = mA_data[0].length; // 열의 개수
        double[][] result = new double[rows][cols]; // 행렬의 뺄셈 연산 결과를 저장하는 배열

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = mA_data[i][j] - mB_data[i][j];
            }
        }

        return result; // 연산 결과 반환
    }

    /* 행렬 곱셈 함수 */
    public static double[][] mulMtrx(double mA_data[][], double mC_data[][]) { // mA * mC 연산이라서 mC_data[]로 매개변수명 수정함
        int rowsA = mA_data.length; // mA - 행의 개수
        int colsA = mA_data[0].length; // mA - 열의 개수
        int colsC = mC_data[0].length; // mC - 열의 개수
        double[][] result = new double[rowsA][colsC]; // 행렬의 곱셈 연산 결과를 저장하는 배열

        // mA의 i번째 행과 mC의 j번째 열을 곱한 후 더하기
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsC; j++) {
                double sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += mA_data[i][k] * mC_data[k][j];
                }
                result[i][j] = sum; // 해당하는 위치에 결과 저장
            }
        }

        return result; // 연산 결과 반환
    }

    public static void main(String[] args) {
        double mA[][] = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        double mB[][] = {{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}};
        double mAddAB[][];
        double mSubAB[][];
        printMtrx("mA", mA);
        printMtrx("mB", mB);
        mAddAB = addMtrx(mA, mB);
        printMtrx("mAddAB", mAddAB);
        mSubAB = subMtrx(mA, mB);
        printMtrx("mSubAB", mSubAB);
        double mC[][] = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, 0}, {0, 0, 0}};
        double mMulAC[][];
        printMtrx("mA", mA);
        printMtrx("mC", mC);
        mMulAC = mulMtrx(mA, mC);
        printMtrx("mMulAC", mMulAC);
    }
}