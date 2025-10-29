
// 프로젝트 진행하다 보면 현재 프로젝트 정책상 예외 취급해야되는 특정 상황, 특정 코드가 생기게 됨
// 예외상황을 관리하기 위해 커스텀된 예외 클래스를 정의해 줄 수 있다

// 커스텀된 예외를 정의해 줄 때에는 자바의 룰에 따라
// 모든 예외처리에 관련된 클래스의 최상위 클래스에 해당하는 Exception 클래스를 상속 받도록 해야함

class MyException extends Exception {
	public MyException() {
		
	}
	public MyException(String str) {
		super(str);
	}
}

public class CustomExceptionEx {
	public static void main(String[] args) throws MyException {
		// 커스텀 예외처리 클래스 사용
		try {
			int x = 10;
			int y = 2;
			
			if (y == 2) {
				throw new MyException();
			}
			
			int r = x / y;
			System.out.println(r);
		} catch (Exception e) {
			System.out.println("문제 발생");
		}
	}
}
