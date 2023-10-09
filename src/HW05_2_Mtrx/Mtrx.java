package HW05_2_Mtrx;

import java.io.*;
import java.util.Scanner;

/* 행렬의 연산을 위한 클래스*/
public class Mtrx {
    private String m_name; // 행렬의 이름
    private int n_row; // 행의 수
    private int n_col; // 열의 수
    private double[][] m_data; // 행렬 값 (행렬은 2차원 배열)

    // 기본 생성자
    public Mtrx() {
        this.m_name = "";
        this.n_row = 0;
        this.n_col = 0;
        this.m_data = new double[0][0];
    }

    // 이름 지정 생성자
    public Mtrx(String nm) {
        this.m_name = nm;
        this.n_row = 0;
        this.n_col = 0;
        this.m_data = new double[0][0];
    }

    // 이름, 행/열 수 지정 생성자
    public Mtrx(String nm, int n_row, int n_col) {
        this.m_name = nm;
        this.n_row = n_row;
        this.n_col = n_col;
        this.m_data = new double[n_row][n_col];
    }

    // 이름, 행/열 수, 행렬 값 지정 생성자
    public Mtrx(String nm, int n_row, int n_col, double[][] m_data) {
        this.m_name = nm;
        this.n_row = n_row;
        this.n_col = n_col;
        this.m_data = m_data;
    }

    /* 파일에서 인스턴스를 읽어오는 메서드 */
    public static Mtrx fget_Mtrx(Scanner fin) {
        String nm = fin.next(); // 행렬의 이름을 읽음
        int rows = fin.nextInt(); // 행의 수를 읽음
        int cols = fin.nextInt(); // 열의 수를 읽음

        double[][] data = new double[rows][cols]; // 행렬 값을 담을 2차원 배열 초기화
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = fin.nextDouble(); // 행렬 값을 읽음
            }
        }
        return new Mtrx(nm, rows, cols, data);
    }

    /* 행렬의 이름을 설정하는 메서드 */
    public void setMtrxName(String nm) {
        this.m_name = nm;
    }

    /* 계산한 행렬을 콘솔에 출력하는 메서드 */
    public void printMtrx() {
        System.out.println(m_name + " (" + n_row + " x " + n_col + ") =");
        for (int i = 0; i < n_row; i++) {
            for (int j = 0; j < n_col; j++) {
                System.out.printf("%7.2f ", m_data[i][j]); // 소수점 2자리까지 나타내고 공백을 이용하여 줄 맞춤
            }
            System.out.println();
        }
    }

    /* 계산한 행렬을 파일에 출력하는 메서드 */
    public void fprintMtrx(FileWriter fout) throws IOException {
        fout.write(m_name + " (" + n_row + " x " + n_col + ") =" + "\n");
        for (int i = 0; i < n_row; i++) {
            for (int j = 0; j < n_col; j++) {
                fout.write(String.format("%7.2f ", m_data[i][j]));
            }
            fout.write("\n");
        }
    }

    /* 두 행렬을 더하는 메서드 */
    public Mtrx addMtrx(Mtrx other) {
        double[][] result = new double[n_row][n_col];
        for (int i = 0; i < n_row; i++) {
            for (int j = 0; j < n_col; j++) {
                result[i][j] = m_data[i][j] + other.m_data[i][j];
            }
        }

        return new Mtrx(m_name, n_row, n_col, result); // 계산된 행렬을 반환
    }

    /* 두 행렬을 빼는 메서드 */
    public Mtrx subMtrx(Mtrx other) {
        double[][] result = new double[n_row][n_col];
        for (int i = 0; i < n_row; i++) {
            for (int j = 0; j < n_col; j++) {
                result[i][j] = m_data[i][j] - other.m_data[i][j];
            }
        }

        return new Mtrx(m_name, n_row, n_col, result);
    }

    /* 두 행렬을 곱하는 메서드 */
    public Mtrx mulMtrx(Mtrx other) {
        double[][] result = new double[n_row][other.n_col];
        for (int i = 0; i < n_row; i++) {
            for (int j = 0; j < other.n_col; j++) {
                for (int k = 0; k < n_col; k++) {
                    result[i][j] += m_data[i][k] * other.m_data[k][j];
                }
            }
        }

        return new Mtrx(m_name, n_row, other.n_col, result);
    }
}
