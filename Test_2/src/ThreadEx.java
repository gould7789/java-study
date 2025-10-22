
// 자바에서 Thread 사용을 위한 문법 2가지

// (1)번 방식으로 정의
class MyThread1 extends Thread {
	public void run() {	// callback method 
		// Thread가 수행할 작업을 코딩
		for (int i=0; i<10; i++) {
			System.out.println(i);
		}
	}
}

// (2)번 방식으로 정의
class MyThread2 implements Runnable {
	
	// start 메소드를 만들어 주는 것도 방법
	public void start() {
		new Thread(this).start();
	}
	
	public void run() {
		// Thread 수행할 작업을 코딩
		for (int i = 100; i < 1000; i += 100) {
			System.out.println(i);
		}
	}
}

public class ThreadEx {
	public static void main(String[] args) {
		System.out.println("Start");
		
		// (1)번 방식 사용
		MyThread1 m1 = new MyThread1(); 
		m1.start(); // Thread 실행을 위해서는 꼭!! start() 메소드를 호출할 것
//		m1.run(); 잘못 된 코드
		
		// (2)번 방식 사용
		// (2)번 방식은 Thread를 상속하지 않았음
		MyThread2 m2 = new MyThread2();
		m2.start();
		
		// Thread로 감싸줘야 함
//		Thread m2t =  new Thread(m2);
//		m2t.start(); 
		// start() 메소드 호출이 이제 Thread 실행을 의미하는 것은 아님
		// 해당 Thread가 CPU 제어권을 얻을 수 있는 대기방에 들어가게 됨을 의미
		
		System.out.println("End");
	}
}
