// To Do List
//1: 클라이언트 접속 대기
//   -> 좁속 후 클라이언트와 매칭되는 소켓 생성
//2: 접속한 모든 클라이언트로부터 메시지 수신
//   -> 수신된 메시지를 모든 클라이언트로 송신
//   : 클라이언트 수 + 1개의 Thread 필요

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

//서버가 클라이언트 접속대기도 하면서 클라이언트들이 보내는 메시지 수신
//두 가지 작업을 동시에 처리할 수 있도록 Thread 만들기
class ServerThread extends Thread {

    Socket server;
    public ServerThread(Socket server) {
        this.server = server;
    }
    
    public void run() {
        //2) 접속한 클라이언트로부터 지속적으로 메시지 수신
        while (true) {
            try {
                //종이컵에서 읽기 위한 실 뽑기
                InputStream is = server.getInputStream();
                byte[] b = new byte[1024];
                is.read(b); // 1024 byte 읽어서 배열 b에 저장
                
                //수신된 메시지를 모든 클라이언트로 송신
                //종이컵 저장공간에서 종이컵을 하나씩 가져와서 해당 클라이언트로 메시지 전송
                //즉, 어딘가에 여태 만든 종이컵이 저장되어 있는곳이 있어야 함

                // 종이컵 저장 공간 (totalSocket)에서 종이컵 하나씩 가져와서
                // 쓰기 위한 실(OutputStream)을 뽑아내서 데이터 전송
                for (int i = 0 ; i<Server.totalSocket.size(); i++) {
                    Socket temp = (Socket) Server.totalSocket.get(i);
                    OutputStream os = temp.getOutputStream();
                    os.write(b); //방금전에 읽은 1024 바이트 만큼의 데이터를 전송
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Server {
    static ArrayList totalSocket = new ArrayList();

    public static void main(String[] args) throws IOException {

        System.out.println("Server starting...");
        //1: 클라이언트 접속을 지속적으로 대기
        ServerSocket ss = new ServerSocket(8888);
        while (true) {
            //클라이언트 접속 대기 + 접속하면 소켓 생성
            Socket server = ss.accept();
            //종이컵 저장
            totalSocket.add(server);
//          이제 클라이언트가 접속했으니
//          해당 클라이언트 담당하는 Thread 만들어서 구동시켜 줘야 함
            new ServerThread(server).start();
        }
    }
}
