//Client 파트 구현 목록
//   1) 서버에 접속(Socket 생성) - 접속 버튼이 클릭되었을 때
//   2) 서버로 메시지 송신
//   3) 서버에서 보내는 메시지 수신


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;




class ClientUI extends JFrame {
    JButton con; //서버 접속을 위한 버튼
    JTextField jtf; //채팅 메시지 입력창
    JTextArea jta;

    Socket client; //서버와의 통신을 위한 종이컵
    OutputStream os; //서버에 데이터 전송 위한 실
    InputStream is;
    //서버에 접속을 성공하고 나면 스레드를 구동시켜줘야 함
    class ClientThread extends Thread {
        public void run() {
            //   3) 서버에서 보내는 메시지 수신
            while (true) {
                try {
                    byte[] b = new byte[1024];
                    is.read(b);
                    //수신된 메시지 화면에 보여주기
                    String str = new String(b);
                    jta.append(str.trim() + '\n');
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    public ClientUI() {
        super("이것은 클라이언트");
        con = new JButton("접속");
        jtf = new JTextField(20);
        jta = new JTextArea(30, 30);


        //입력창 비활성화 코드
        jtf.setEnabled(false);
        
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //calllback method
                //버튼이 클릭되거나 enter 키가 입력되면 호출되는
                //콜백 메소드

                //버튼이 눌려졌는지 enter키가 눌려졌는지 구분
                //눌러진 버튼에 이름 가져오기
                String str = e.getActionCommand();
                if (str.equals("접속")) {

                    try {//1) 서버에 접속(Socket 생성)
                        client = new Socket("127.0.0.1", 8888);
//                        client = new Socket("210.101.236.171", 8888);
                        //서버에 접속 성공 시
                        is = client.getInputStream();
                        os = client.getOutputStream();
                        new ClientThread().start();
                        
                        //입력창 비활성화 코드
                        jtf.setEnabled(true);
                        //버튼 비활성화 코드
                        con.setEnabled(false);
                        
                        con.setText("접속 성공");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    System.out.println("서버 접속 버튼 클릭됨");
                } else {    //입력창에 Enter key 입력되었을때
                    //2)서버로 메시지 송신
                    try {
                        OutputStream os = client.getOutputStream();
                        String msg = jta.getText(); //입력창에서 문자열 가져오기
                        os.write(msg.getBytes());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        }

        FlowLayout layout = new FlowLayout(); //화면 배치 관리자
        setLayout(layout); //현재 판때기에 배치 관리자 설정
        add(con); //접속버튼
        add(jtf); //판때기에 한 줄 입력창 먼저 배치
        add(jta); //대화창 배치

        MyListener m = new MyListener(); //감시자 객체화
        con.addActionListener(m);

        jtf.addActionListener(m);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocation(250,250);
        setVisible(true);

    }
}

public class Client {
    public static void main(String[] args) throws IOException {
        new ClientUI();
        //   1) 서버에 접속(Socket 생성)
//        Socket client = new Socket("127.0.0.1", 8888);
//        OutputStream os = client.getOutputStream();
    }
}


