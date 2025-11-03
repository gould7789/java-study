
// 서버 파트 구현 목록
// 1) 클라이언트 접속 대기
// 		- 접속 후 클라이언트와 매칭되는 종이컵(Socket) 생성
// 2) 접속한 모든 클라이언트로부터 메시지 수신
//		- 수신된 메시지를 모든 클라이언트로 송신
// 클라이언트 수 + 1개의 Thread 필요

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 서버가 클라이언트 접속대기도 하면서 클라이언트들이 보내는 메시지 수신도 하기 위해
// 두 가지 작업을 동시에 처리해 줄 수 있도록 thread를 만듦
class ServerThread extends Thread {
	Socket server;
	public ServerThread(Socket server) {
		// 현재 클래스의 멤버변수 server, 현재 생성자로 받아온 server
		this.server = server;
	}
	
	public void run() {
		// 2) 접속한 클라이언트로부터 메시지 수신
		try {
			// 종이컵에서 읽기 위한 실 뽑아내기
			InputStream is = server.getInputStream();
			byte[] b = new byte[1024];
			// 1024 바이트를 읽어서 배열 b에 저장
			is.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


public class Server {
	public static void main(String[] args) throws IOException {
		
		// 1) 클라이언트 접속을 지속적으로 대기
		ServerSocket ss = new ServerSocket(8888);
		while (true) {	// 여러 클라이언트 접속을 받아주기 위해 반복문 사용 
			// 클라이언트 접속대기 + 접속하면 종이컵 만들어두기
			Socket server = ss.accept();
			
			// 2) 접속한 모든 클라이언트로부터 메시지 수신
			
			
		}
		
		
	}
}
