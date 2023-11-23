/*
 * Homework 10.1 양방향 이중 통신 텍스트 채팅 프로그램
 * 이름: 박다원
 * 학번: 21912154
 */

package HW10_1_Java_TextChatting_GUI_Thread_Socket;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

/* Client 클래스 */
public class TextChatting_Socket_Client_GUI extends JFrame {
    static private JTextArea jtxt_display_area = null; // 서버로부터 수신된 메시지 표시
    private JTextArea jtxt_msg_input_area = null; // 클라이언트가 입력한 메시지 입력하는 곳
    private final String input_msg = null; // 입력한 메시지를 저장
    static private final JTextField jtxt_serv_addr = null; // 서버 주소
    static private final JTextField jtxt_cli_addr = null; // 클라이언트 주소
    static private DataOutputStream sockDataOutputstream = null; // 데이터를 서버에 전송하기 위한 스트림
    private static final int port_no = 5056; // 포트 번호

    public static void main(String[] agrs) throws IOException {
        InetAddress inetAddr = null;
        InetAddress myAddr = null;
        int serv_port_no = 5056;
        Socket servSocket = null;
        DataInputStream sockDataInputstream = null;
        DataOutputStream sockDataOutputstream = null;
        TextChatting_Socket_Client_GUI gui_TxtChat_Server = new TextChatting_Socket_Client_GUI();

        // 주소를 가져온 후 출력
        myAddr = InetAddress.getByName("localhost");
        System.out.printf("Socket Client:: My inetaddress = %s\n", myAddr);
        jtxt_display_area.append("Socket Client:: client_inetaddr = " + myAddr + "\n");
        jtxt_display_area.append("Socket Client:: Trying to connect server ...\n");

        // 소켓 연결
        servSocket = new Socket("127.0.0.1", serv_port_no);
        SocketAddress serv_addr = servSocket.getLocalSocketAddress();
        System.out.print("Socket Client:: Socket is opened ....\n");
        jtxt_display_area.append("Socket Client:: Socket is opened ....\n");
        inetAddr = servSocket.getInetAddress();
        System.out.printf("Socket client :: connected to server (" + servSocket + ") ....\n");
        jtxt_display_area.append("Socket client :: connected to server (" + servSocket + ") ....\n");

        try {
            // 입출력 스트림 설정
            sockDataInputstream = new DataInputStream(servSocket.getInputStream());
            setDataInputStream(sockDataInputstream);
            sockDataOutputstream = new DataOutputStream(servSocket.getOutputStream());
            setDataOutputStream(sockDataOutputstream);
            System.out.print("Socket_client is ready now ....\n");
            jtxt_display_area.append("Socket_client is ready now ....\n");

            // 서버로부터 메시지 받아온 후 출력
            String recvMsgStr;
            String msgStrToSent;
            while (true) {
                try {
                    recvMsgStr = sockDataInputstream.readUTF();
                    if (recvMsgStr.equals("Exit")) {
                        System.out.println("Server " + servSocket + " sent exit...");
                        System.out.println("Connection closing...");
                        jtxt_display_area.append("Server " + servSocket + " sent exit...");
                        jtxt_display_area.append("Connection closing...");
                        servSocket.close();
                        System.out.println("Closed");
                        jtxt_display_area.append("Closed");
                        break;
                    }
                    jtxt_display_area.append(">> " + recvMsgStr + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

            // 입출력 스트림 닫기
            try {
                sockDataInputstream.close();
                sockDataOutputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // 예외 처리
            servSocket.close();
            servSocket.close();
            e.printStackTrace();
        }
    }

    /* 클라이언트 GUI를 설정하는 클래스 */
    public TextChatting_Socket_Client_GUI() {
        setTitle("JavaSwing-based TextChatting_Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(7, 2, 5, 5);
        Container c = getContentPane();
        c.setBackground(Color.LIGHT_GRAY);
        c.setLayout(new FlowLayout());

        JPanel pnl_addr = new JPanel();
        Border border_addr = BorderFactory.createTitledBorder("Server/Client Address");
        pnl_addr.setBorder(border_addr);
        c.add(pnl_addr);

        JLabel l_serv_addr = new JLabel("Server Addr");
        pnl_addr.add(l_serv_addr);
        JTextField tf_serv_addr = new JTextField("127.0.0.1");
        tf_serv_addr.setBackground(Color.YELLOW);
        pnl_addr.add(tf_serv_addr);

        JLabel l_cli_addr = new JLabel("Client Addr");
        pnl_addr.add(l_cli_addr);
        JTextField tf_cli_addr = new JTextField("127.0.0.1");
        tf_cli_addr.setBackground(Color.YELLOW);
        pnl_addr.add(tf_cli_addr);

        JPanel pnl_display_area = new JPanel();
        Border border_text_area = BorderFactory.createTitledBorder("Program Progress / Received Message");
        pnl_display_area.setBorder(border_text_area);
        c.add(pnl_display_area);

        JTextArea display_area = new JTextArea("Constructor executed...\n", 15, 30);
        pnl_display_area.add(new JScrollPane(display_area));
        jtxt_display_area = display_area;

        JPanel pnl_message_input_area = new JPanel();
        Border border_message_input_area = BorderFactory.createTitledBorder("Input message to be sent");
        pnl_message_input_area.setBorder(border_message_input_area);
        c.add(pnl_message_input_area);

        JTextArea msg_input_area = new JTextArea("Sample mesage to be sent to server", 3, 30);
        pnl_message_input_area.add(new JScrollPane(msg_input_area));
        jtxt_msg_input_area = msg_input_area;

        JButton send_button = new JButton("Send Text Message to Server");
        send_button.setSize(150, 50);
        send_button.setBackground(Color.GREEN);
        send_button.addActionListener(new ActionHandler());
        c.add(send_button);

        setSize(400, 600);
        setVisible(true);
    }

    // 입력 스트림 설정 메서드
    static private void setDataInputStream(DataInputStream sockDinStr) {
    }

    // 출력 스트림 설정 메서드
    static private void setDataOutputStream(DataOutputStream sockDoutStr) {
        sockDataOutputstream = sockDoutStr;
    }

    // 버튼 클릭 이벤트를 처리하는 클래스
    private class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("Send Text Message to Server")) {
                String input_msg = jtxt_msg_input_area.getText();
                jtxt_display_area.append("<< " + input_msg + "\n");
                jtxt_msg_input_area.setText("");
                try {
                    sockDataOutputstream.writeUTF(input_msg);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
