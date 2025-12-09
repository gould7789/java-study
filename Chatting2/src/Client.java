//client 파트 구현 목록
//1)서버에 접속(Socket 생성)  - 접속 버튼이 클릭되었을때 
//2)서버로 메시지 송신
//3)서버에서 보내는 메시지 수신

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//클라이언트 화면에서 처리되어야 하는 이벤트 목록
//-> "접속" 버튼 클릭시 서버에 접속
//-> 대화창에 "enter" 입력시 서버로 메시지 전송

//채팅 메시지 입력창, 대화창이 기본적으로 제공되는 클라이언트 화면구현
class ClientUI extends JFrame {
	JButton con; // 서버 접속을 위한 버튼
	JTextField jtf; // 채팅 메시지 입력창
	JTextArea jta; // 지난 채팅 메시지를 볼 수 있는 대화창
	String nick;   // 닉네임 저장을 위한 변수

	Socket client; // 서버와의 통신을 위한 종이컵
	OutputStream os;  //서버에 데이터를 전송하기 위한 실
	InputStream is;   //서버에 데이터를 수신하기 위한 실
	
	//서버에 접속을 성공하고 나면 Thread를 구동시켜줘야 함
	class ClientThread extends Thread {
		int lineIndex = 0;
		int lineLimit = 30;
		public void run() {
			// 3)서버에서 보내는 메시지 지속적으로 수신
			
				try {
					while(true) {
					// 종이컵에서 읽기위한 실 뽑아내기
					byte[] b = new byte[1024];
					int i =is.read(b); // 1024byte 읽어서 배열 b에 저장
					if (lineIndex++ > lineLimit) {
						jta.setText(""); // 30줄 넘어가면 화면 초기화
						lineIndex = 0;
					}
					// 수신된 메시지 화면에 보여주기
					if (i == -1) {
						break;
					}
					String str = new String(b); // 바이트값 -> 문자열 변환
					jta.append(str.trim() + '\n');
					}// 빈공백 제거 후 화면에 보여주기
				} catch (IOException e) {
					e.printStackTrace();
					return;
				} 
			
		}
	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// callback method
			// 버튼이 클릭되거나 "enter" 키가 입력되면 호출되는
			// 콜백 메소드

			// 버튼이 눌려졌는지 "enter"키가 눌려졌는지 구분
			// 눌러진 버튼에 이름 가져오기
			String str = e.getActionCommand();
			if (str.equals("접속")) {
				try {
					// 1)서버에 접속(Socket 생성)
//					client = new Socket("127.0.0.1", 8888);
					client = new Socket("210.101.236.171", 8888);
					
					// 버튼 비활성화 코드
					con.setEnabled(false);
					// 입력창 비활성화 코드
					jtf.setEditable(true);
					
					//서버 접속을 성공하고 나면
					is = client.getInputStream();
					os = client.getOutputStream();
					new ClientThread().start();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("서버접속 버튼 클릭됨");
			} else {   //입력창에 "enter" key 입력되었을때
				//2)서버로 메시지 송신
				//종이컵에서 쓰기위한 실뽑아내기
				String msg = jtf.getText(); //입력창에서 문자열 가져오기
				msg = "["+nick+"]" + msg;	// 앞부분에 닉네임 추가
				try {
					os.write( msg.getBytes() );
					jtf.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public ClientUI() {
		super("이것은클라이언트");
		con = new JButton("접속");
		jtf = new JTextField(20);
		jta = new JTextArea(30, 30);
		nick = JOptionPane.showInputDialog("닉네임을 입력해주세요");
		

		// 입력창 비활성화 코드
		jtf.setEditable(false);

		FlowLayout layout = new FlowLayout(); // 화면 배치 관리자
		setLayout(layout); // 현재 판떄기에 배치 관리자 설정
		add(jtf); // 판때기에 한줄 입력창 먼저 배치
		add(con); // 그다음 접속버튼
		add(jta); // 마지막으로 대화창 배치

		// 접속버튼 event handling
		// 감시자 객체화
		MyListener m = new MyListener();
		// 접속 버튼에 감시자 달아주기
		con.addActionListener(m);

		// 입력창 event handling
		// 한줄 입력창에 감시자 달아주기
		jtf.addActionListener(m);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 강제종료 기능 활성화
		setSize(400, 700); // 판때기 사이즈 조절
		setLocation(250, 250); // 판때기 보여질 위치 조절
		setVisible(true); // 판때기 화면에 보여주기
	}
}

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		new ClientUI();
		// 1) 서버에 접속(Socket 생성)
//		Socket client = new Socket("127.0.0.1", 8888);
		// 2)서버로 메시지 송신
//		OutputStream os = client.getOutputStream();
//		os.write("카츠동");
	}
}
