package HW10_1_Java_TextChatting_GUI_Thread_Socket;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/* Server 클래스 */
public class TextChatting_Socket_Server_GUI extends JFrame {
    static private JTextArea jtxt_display_area = null; // 클라이언트로부터 수신된 메시지 표시
    private JTextArea jtxt_msg_input_area = null; // 서버 메시지를 입력하는 곳
    private final String input_msg = null; // 입력 메시지 저장
    static private final JTextField jtxt_serv_addr = null; // 서버 주소
    static private final JTextField jtxt_cli_addr = null; // 클라이언트 주소
    static private DataOutputStream sockDataOutputstream = null; // 데이터를 클라이언트로 전송하기 위한 스트림
    private static final int port_no = 5056; // 포트 번호

    public static void main(String[] agrs) throws IOException {
        TextChatting_Socket_Server_GUI gui_TxtChat_Server = new TextChatting_Socket_Server_GUI();

        // 서버 소켓 생성 후 포트를 열어서 클라이언트의 연결을 기다림
        ServerSocket servSocket = new ServerSocket(port_no);
        SocketAddress serv_addr = servSocket.getLocalSocketAddress();
        System.out.printf("Socket Server:: ServerSocket (serv_addr: %s) is opened.\n", serv_addr);
        jtxt_display_area.append("Socket Server (" + serv_addr + "):: ServerSocket is opened.\n"); // getting client request

        Socket cliSocket = null;
        System.out.print("Socket Server:: waiting for a client's connection request ....\n");
        jtxt_display_area.append("Socket Server:: waiting for a client's connection request ....\n");
        try {
            // 클라이언트가 연결 요청 시, 소켓 생성 후 연결
            cliSocket = servSocket.accept();
            System.out.printf("Socket Server:: Connected to a client: %s\n", cliSocket);
            jtxt_display_area.append("Socket Server:: Connected to a client" + cliSocket + "\n");

            // 서버-클라이언트 간 통신을 위한 입출력 스트림 설정
            DataInputStream sockDataInputstream = new DataInputStream(cliSocket.getInputStream());
            setDataInputStream(sockDataInputstream);
            DataOutputStream sockDataOutputstream = new DataOutputStream(cliSocket.getOutputStream());
            setDataOutputStream(sockDataOutputstream);

            // 메시지 수신 및 출력
            String recvMsgStr;
            String msgStrToSent;
            while (true) {
                try {
                    recvMsgStr = sockDataInputstream.readUTF();
                    if (recvMsgStr.equals("Exit")) {
                        System.out.println("Client " + cliSocket + " sends exit...");
                        System.out.println("Connection closing...");
                        jtxt_display_area.append("Client " + cliSocket + " sends exit...");
                        jtxt_display_area.append("Connection closing...");
                        cliSocket.close();
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
            if (cliSocket != null) cliSocket.close();
            servSocket.close();
            e.printStackTrace();
        }
    }

    /* 서버 GUI를 설정하는 클래스 */
    public TextChatting_Socket_Server_GUI() {
        setTitle("JavaSwing-based TextChatting_Server");
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
        pnl_display_area.add(new JScrollPane(display_area)); //display_area.append("\nadded line"); // for testing only
        jtxt_display_area = display_area;

        JPanel pnl_message_input_area = new JPanel();
        Border border_message_input_area = BorderFactory.createTitledBorder("Input message to be sent");
        pnl_message_input_area.setBorder(border_message_input_area);
        c.add(pnl_message_input_area);

        JTextArea msg_input_area = new JTextArea("Sample message to be sent to client", 3, 30);
        pnl_message_input_area.add(new JScrollPane(msg_input_area));
        jtxt_msg_input_area = msg_input_area;

        JButton send_button = new JButton("Send Text Message to Client"); //send_button.setLocation(100, 100);
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
            if (b.getText().equals("Send Text Message to Client")) {
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
