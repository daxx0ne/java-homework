package HW09_1_DrawingShapes_Graphics2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/* 별을 그리는 클래스 */
public class JPanel_Star extends JPanel {
    private int radius;
    private Color color;

    public JPanel_Star(int panel_width, int panel_height, int radius, Color color) {
        this.radius = radius; // 외접원의 반지름
        this.color = color; // 선 색깔
        setSize(panel_width, panel_height); // 패널의 가로, 세로 크기
    }

    // 별의 속성을 지정하고 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5)); // 선 굵기 설정
        g2.setColor(color);

        int cx = getWidth() / 2; // 패널의 중심 좌표 계산
        int cy = getHeight() / 2;

        int[] px = new int[5]; // 각 꼭짓점의 x 좌표를 저장할 배열
        int[] py = new int[5]; // 각 꼭짓점의 y 좌표를 저장할 배열

        for (int i = 0; i < 5; i++) {
            double theta_rad = (3.0 * Math.PI) / 2.0 + i * (2.0 * Math.PI) / 5.0;
            px[i] = cx + (int) (radius * Math.cos(theta_rad)); // 각 꼭짓점의 좌표를 삼각함수를 사용하여 계산
            py[i] = cy + (int) (radius * Math.sin(theta_rad));
        }

        g2.drawLine(px[0], py[0], px[2], py[2]); // 좌표와 좌표끼리 선을 그어가면서 별을 그림
        g2.drawLine(px[2], py[2], px[4], py[4]);
        g2.drawLine(px[4], py[4], px[1], py[1]);
        g2.drawLine(px[1], py[1], px[3], py[3]);
        g2.drawLine(px[3], py[3], px[0], py[0]);
    }
}
