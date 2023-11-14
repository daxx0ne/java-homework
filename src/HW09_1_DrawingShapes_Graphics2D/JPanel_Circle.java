package HW09_1_DrawingShapes_Graphics2D;

import java.awt.*;
import javax.swing.JPanel;

/* 원을 그리는 클래스 */
public class JPanel_Circle extends JPanel {
    private int radius;
    private Color color;

    // 생성자
    public JPanel_Circle(int panel_width, int panel_height, int radius, Color color) {
        this.radius = radius; // 반지름
        this.color = color; // 선 색깔
        setSize(panel_width, panel_height); // 패널의 가로, 세로 크기
    }

    // 원의 속성을 지정하고 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5)); // 선 굵기 설정
        g2.setColor(color);

        int cx = getWidth() / 2; // 패널의 중심 좌표 계산
        int cy = getHeight() / 2;

        g2.drawOval(cx - radius, cy - radius, 2 * radius, 2 * radius); // 중심을 기준으로 반지름 길이만큼의 원을 그림
    }
}
