/*
 * Homework 11.1 ShoppingMall 응용 서비스 구축을 위한 Database 및 관련 Table 생성, 초기화
 * 이름: 박다원
 * 학번: 21912154
 */

package HW11_1_MySQL_JDBC_AcademicManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class MySQL_JDBCS_AcademicManagementSystem {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
        Connection conn_db_academic = null;
        final String user_name = "root";
        final String passwd = "pms41414";
        try {
            System.out.println("Loading MySQL's JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loading MySQL's JDBC driver successfully !!");
            System.out.flush();

            // DB 연결
            conn_db_academic = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingmall", user_name, passwd);
            System.out.println("Connected to jdbc:mysql://localhost:3306/shoppingmall");

            // 테이블 생성
            createTable(conn_db_academic, "tbl_member", "name VARCHAR(50), contact_point VARCHAR(20), address VARCHAR(100)");
            createTable(conn_db_academic, "tbl_product", "product_name VARCHAR(50), price DOUBLE, manufacturer VARCHAR(50)");
            createTable(conn_db_academic, "tbl_order", "order_number INT, purchaser VARCHAR(50), item VARCHAR(50), total_price DOUBLE");
            createTable(conn_db_academic, "tbl_cart", "customer VARCHAR(50), product_item VARCHAR(50)");

            // 텍스트 파일로부터 데이터 읽기
            fget_and_insert_Data("/Users/daone/IdeaProjects/java-homework/src/HW11_1_MySQL_JDBC_AcademicManagementSystem/member_data.txt", conn_db_academic, "tbl_member", "name, contact_point, address");
            fget_and_insert_Data("/Users/daone/IdeaProjects/java-homework/src/HW11_1_MySQL_JDBC_AcademicManagementSystem/product_data.txt", conn_db_academic, "tbl_product", "product_name, price, manufacturer");
            fget_and_insert_Data("/Users/daone/IdeaProjects/java-homework/src/HW11_1_MySQL_JDBC_AcademicManagementSystem/order_data.txt", conn_db_academic, "tbl_order", "order_number, purchaser, item, total_price");
            fget_and_insert_Data("/Users/daone/IdeaProjects/java-homework/src/HW11_1_MySQL_JDBC_AcademicManagementSystem/cart_data.txt", conn_db_academic, "tbl_cart", "customer, product_item");

            conn_db_academic.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error in loading MySQL's JDBC driver (ClassNotFoundException) !!, error message = " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error in connection to MySQL DB !!, error message =" + e.getMessage());
        }
    }

    // 테이블이 존재하는지 확인하는 메서드
    static boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables " + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    // 테이블 생성 알림 메서드
    static void createTable(Connection connection, String tableName, String columns) throws SQLException {
        if (!tableExistsSQL(connection, tableName)) {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE " + tableName + " (" + columns + ")";
            System.out.printf("Table (%s) is created\n", tableName);
        } else {
            System.out.printf("Table (%s) is already existing\n", tableName); // 테이블이 이미 존재함
        }
    }

    // 데이터 삽입 메서드
    static void fget_and_insert_Data(String fileName, Connection connection, String tableName, String columns) throws FileNotFoundException, SQLException {
        try (Scanner fin = new Scanner(new File(fileName))) {
            while (fin.hasNext()) {
                String[] data = fin.nextLine().split("\t");

                String placeholders = getPlaceholderString(data.length);
                String sql = "INSERT INTO " + tableName + "(" + columns + ") VALUES (" + placeholders + ")";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    for (int i = 0; i < data.length; i++) {
                        preparedStatement.setString(i + 1, data[i]);
                    }
                    int result = preparedStatement.executeUpdate();
                    System.out.printf("Inserted Data into %s\n", tableName);
                } catch (SQLException e) {
                    System.out.println("Error inserting data into " + tableName + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file " + fileName + ": " + e.getMessage());
        }
    }

    static String getPlaceholderString(int length) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < length; i++) {
            placeholders.append("?");
            if (i < length - 1) {
                placeholders.append(",");
            }
        }
        return placeholders.toString();
    }
}
