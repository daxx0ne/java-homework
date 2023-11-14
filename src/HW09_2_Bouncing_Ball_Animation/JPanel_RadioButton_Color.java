package HW09_2_Bouncing_Ball_Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/* 공의 색을 변경하기 위한 라디오 버튼을 생성, 이벤트 처리 클래스 */
public class JPanel_RadioButton_Color extends JPanel {
    JRadioButton[] rb_color = new JRadioButton[3]; // 3가지 색을 선택할 수 있는 라디오 버튼 배열
    JPanel_BouncingBall jp_bouncingBall;

    // 생성자
    JPanel_RadioButton_Color(JPanel_BouncingBall jp_bouncingBall) {
        this.jp_bouncingBall = jp_bouncingBall;
        String[] color_str = {"red", "green", "blue"};
        ButtonGroup bgrp_color = new ButtonGroup();
        for (int i = 0; i < rb_color.length; i++) {
            rb_color[i] = new JRadioButton(color_str[i]);
            rb_color[i].addItemListener(new RB_Color_ItemListener()); // 각 라디오 버튼에 대한 이벤트 리스너를 등록
            bgrp_color.add(rb_color[i]);
            add(rb_color[i]);
        }
        rb_color[0].setSelected(true);
    }

    // ItemListener 인터페이스를 구현한 내부 클래스
    class RB_Color_ItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) { // 현재 선택된 색을 확인 후 변경
            if (e.getStateChange() == ItemEvent.DESELECTED)
                return;
            if (rb_color[0].isSelected()) { // 색에 따른 성콘솔 출력
                jp_bouncingBall.ball_color = Color.red;
                System.out.print("BallColor changed to RED\n");
            } else if (rb_color[1].isSelected()) {
                jp_bouncingBall.ball_color = Color.GREEN;
                System.out.print("BallColor changed to GREEN\n");
            } else if (rb_color[2].isSelected()) {
                jp_bouncingBall.ball_color = Color.BLUE;
                System.out.print("BallColor changed to BLUE\n");
            }
        }
    }
}
