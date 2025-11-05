import java.awt.FlowLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// 채팅 메시지 입력창, 대화창이 기본적으로 제공되는 클라이언트 화면 구현
class ClientUI extends JFrame {
	JButton con; // 서버 접속을 위한 버튼
	JTextField jtf; // 채팅 메시지 입력창
	JTextArea jta; // 지난 채팅 메시지를 볼 수 있는 대화창
	
	public ClientUI() {
		// 상위 클래스(여기서는 JFrame) 호출
		super("클라이언트");
		con = new JButton("접속");
		jtf = new JTextField(20);
		jta = new JTextArea(30, 30);
		
		// 화면 배치 관리자
		FlowLayout layout = new FlowLayout();
		setLayout(layout);	// 현재 화면에 배치 관리자 설정
		add(jtf);	// 화면에 한줄 입력창 먼저 배치
		add(con);	// 접속 버튼
		add(jta);	// 대화창 배치
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 강제 종료 기능 활성화
		setSize(400, 700);		// 화면 사이즈 조절
		setLocation(250, 250);	// 화면 위치 조절
		setVisible(true);
		
	}
}

public class Client {
	public static void main(String[] args) throws IOException, Exception {
		new ClientUI();
//		// 1. 서버에 접속(Socket 생성)
//		Socket client = new Socket("127.0.0.1", 8888);
//		// 2. 서버로 메시지 송신
//		OutputStream os = client.getOutputStream();
//		os.write("나는 내가 빛나는 별인 줄 알았어요");
	}
}
