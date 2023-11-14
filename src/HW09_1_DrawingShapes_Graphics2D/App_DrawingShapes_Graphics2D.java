/*
 * Homework 9.1 Java Graphics2D를 사용한 다각형 그리기
 * 이름: 박다원
 * 학번: 21912154
 */

package HW09_1_DrawingShapes_Graphics2D;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class App_DrawingShapes_Graphics2D extends JFrame {
    public static void main(String[] args) {
        JFrame jframe = new JFrame("Drawing_Shapes");
        jframe.setSize(600, 600);
        Container contentPane = jframe.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        JPanel_Circle jpanel_circle = new JPanel_Circle(300, 300, 100, Color.BLACK);
        jpanel_circle.setSize(300, 300);
        contentPane.add(jpanel_circle);
        JPanel_Rectangle jpanel_rect = new JPanel_Rectangle(300, 300, 200, 150, Color.RED);
        jpanel_rect.setSize(300, 300);
        contentPane.add(jpanel_rect);
        JPanel_Star jpanel_star = new JPanel_Star(300, 300, 100, Color.GREEN);
        jpanel_star.setSize(300, 300);
        contentPane.add(jpanel_star);
        JPanel_Polygon jpanel_polygon = new JPanel_Polygon(300, 300, 80, 7, Color.BLUE);
        jpanel_polygon.setSize(300, 300);
        contentPane.add(jpanel_polygon);
        jframe.setVisible(true);
    }
}