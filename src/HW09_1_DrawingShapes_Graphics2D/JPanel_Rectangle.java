package HW09_1_DrawingShapes_Graphics2D;

import java.awt.*;
import javax.swing.JPanel;

/* 사각형을 그리는 클래스 */
public class JPanel_Rectangle extends JPanel {
    private int rectWidth;
    private int rectLength;
    private Color color;

    public JPanel_Rectangle(int panel_width, int panel_height, int rectWidth, int rectLength, Color color) {
        this.rectWidth = rectWidth; // 사각형의 가로
        this.rectLength = rectLength; // 사각형의 세로
        this.color = color; // 선 색깔
        setSize(panel_width, panel_height); // 패널의 가로, 세로 크기
    }

    // 사각형의 속성을 지정하고 그리는 메서드
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5)); // 선 굵기 설정
        g2.setColor(color);

        int cx = getWidth() / 2; // 패널의 중심 좌표 계산
        int cy = getHeight() / 2;

        g2.drawRect(cx - rectWidth / 2, cy - rectLength / 2, rectWidth, rectLength); // 중심을 기준으로 지정된 가로, 세로 길이만큼 사각형을 그림
    }
}
