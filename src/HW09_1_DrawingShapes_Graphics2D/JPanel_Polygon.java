package HW09_1_DrawingShapes_Graphics2D;

import java.awt.*;
import javax.swing.JPanel;

/* 다각형을 그리는 클래스 */
public class JPanel_Polygon extends JPanel {
    private int radius;
    private int num_vert;
    private Color color;

    public JPanel_Polygon(int panel_width, int panel_height, int radius, int num_vert, Color color) {
        this.radius = radius; // 외접원의 반지름
        this.num_vert = num_vert; // 다각형의 꼭짓점 개수
        this.color = color; // 선 색깔
        setSize(panel_width, panel_height); // 패널의 가로, 세로 크기
    }

    // 다각형의 속성을 지정하고 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5)); // 선 굵기 설정
        g2.setColor(color);

        int cx = getWidth() / 2; // 패널의 중심 좌표 계산
        int cy = getHeight() / 2;

        int[] px = new int[num_vert]; // 각 꼭짓점의 x 좌표를 저장할 배열
        int[] py = new int[num_vert]; // 각 꼭짓점의 y 좌표를 저장할 배열

        for (int i = 0; i < num_vert; i++) {
            double angle = 2 * Math.PI * i / num_vert;
            px[i] = cx + (int) (radius * Math.cos(angle));  // 각 꼭짓점의 좌표를 삼각함수를 사용하여 계산
            py[i] = cy + (int) (radius * Math.sin(angle));
        }

        g2.drawPolygon(px, py, num_vert);
    }
}
