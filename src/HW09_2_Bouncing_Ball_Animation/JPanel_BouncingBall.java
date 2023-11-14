package HW09_2_Bouncing_Ball_Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Bouncing Ball Animation을 그리고 관리하는 클래스 */
public class JPanel_BouncingBall extends JPanel implements ActionListener {
    Timer timer = new Timer(5, this); // 타이머
    int pos_x = 0, pos_y = 0; // 공의 현재 위치
    int dx = 1, dy = 1; // 공의 이동방향, 속도
    int ball_diameter; // 공의 지름
    int fr_width, fr_height; // 프레임의 가로, 세로 크기
    int fr_x_margin, fr_y_margin; // 프레임의 가로, 세로 여백
    int border_thickness; // 프레임과 패널의 테두리 두께
    double ballSpeed = 1.0; // 공의 이동 속도, 기본값 = 1.0
    Color ball_color = Color.red; // 공의 기본 색깔

    // 생성자
    JPanel_BouncingBall(int ball_diameter, int fr_width, int fr_height, int x_margin, int y_margin, int border_thickness) {
        this.ball_diameter = ball_diameter;
        this.fr_width = fr_width;
        this.fr_height = fr_height;
        this.fr_x_margin = x_margin;
        this.fr_y_margin = y_margin;
        this.border_thickness = border_thickness;
    }

    // 공을 그리는 메서드
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.ball_color);
        g.fillOval(pos_x, pos_y, ball_diameter, ball_diameter);
        timer.start(); // 타이머를 시작하여 일정 시간마다 actionPerformed 메서드를 호출
    }

    // ActionListener 인터페이스를 구현한 메서드
    public void actionPerformed(ActionEvent e) { // 버튼을 누르면 이벤트 생성
        if (pos_x < 0 || pos_x > (fr_width - ball_diameter - fr_x_margin)) dx = -dx; // 현재 위치와 이동방향을 가지고 다음 위치를 계산
        if (pos_y < 0 || pos_y > (fr_height - ball_diameter - fr_y_margin)) dy = -dy; // 경계에 닿으면 방향을 반대로 바꿈
        pos_x += (int) (dx * ballSpeed);
        pos_y += (int) (dy * ballSpeed);
        System.out.printf("Ball speed(%5.2f), position(%3d, %3d)\n", ballSpeed, pos_x, pos_y); // 이동 속도에 따른 위치 업데이트하여 새로운 위치에 공을 그림
        repaint();
    }
}
