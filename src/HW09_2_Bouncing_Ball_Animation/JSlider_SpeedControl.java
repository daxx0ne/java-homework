package HW09_2_Bouncing_Ball_Animation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/* 속도를 조절하기 위한 슬라이더를 생성하는 클래스 */
public class JSlider_SpeedControl extends JPanel {
    JSlider jslider_SpeedControl;
    Container contentPane;
    double ballSpeed; // 속도
    int fr_width, fr_height; // 프레임의 가로, 세로 크기
    int fr_x_margin, fr_y_margin; // 프레임의 가로, 세로 여백
    int border_thickness; // 프레임과 패널의 테두리 두깨
    final int JSlider_MAX = 100; // 슬라이더의 최대 값
    JPanel_BouncingBall jp_bouncingBall;

    JSlider_SpeedControl(JPanel_BouncingBall jp_bouncingBall, int fr_width, int fr_height, int x_margin, int y_margin, int border_thickness) { // constructor
        this.fr_width = fr_width;
        this.fr_height = fr_height;
        this.fr_x_margin = x_margin;
        this.fr_y_margin = y_margin;
        this.border_thickness = border_thickness;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 닫힐 때 프로그램이 종료됨
        jslider_SpeedControl =
                new JSlider(JSlider.HORIZONTAL, 0, JSlider_MAX, 50); // 초기 속도 값 = 50
        jslider_SpeedControl.addChangeListener(new ChangeSliderListener());
        add(jslider_SpeedControl);
        this.jp_bouncingBall = jp_bouncingBall;
        this.jp_bouncingBall.ballSpeed = (double) jslider_SpeedControl.getValue() * 10.0 / JSlider_MAX;
        setVisible(true);
    }

    private void setDefaultCloseOperation(int exitOnClose) {
    }

    // ChangeListener 인터페이스를 구현한 내부 클래스
    class ChangeSliderListener implements ChangeListener {
        // 속도를 계산하고 업데이트 하는 메서드
        public void stateChanged(ChangeEvent e) {
            ballSpeed = (double) jslider_SpeedControl.getValue() * 10.0 / JSlider_MAX;
            jp_bouncingBall.ballSpeed = ballSpeed; // 속도 업데이트
            System.out.printf("BallSpeed changed : %.2f\n", ballSpeed); // 콘솔 출력
        }
    }
}