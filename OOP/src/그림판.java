
class 그림판UI {
	// JButton 동그라미버튼;
	// ..
	
	// 다형성
	public void 도형버튼클릭해서그리기(도형 x) {
		x.그리기();
	}
	
	// 아래 코드를 다형성 문법을 사용해서 하나로 만들어 줌
//	public void 동그라미버튼클릭해서그리기(동그라미 x) {
//		// ..
//		x.그리기();
//	}
//	public void 삼각형버튼클릭해서그리기(삼각형 x) {
//		// ..
//		x.그리기();
//	}
//	public void 네모버튼클릭해서그리기(네모 x) {
//		// ..
//		x.그리기();
//	}
}


// 각 클래스에 동일한 메서드들이 있다면 상위 클래스로 빼 줌
abstract class 도형 {
	// 어떤 역할을 할지 애매한 메서드는 추상화 클래스로 만들어 줌
	abstract public void 그리기();
}

class 동그라미 extends 도형 {
	public void 그리기() {
		System.out.println("동그라미 그리기");
	}
}

class 네모 extends 도형 {
	public void 그리기() {
		System.out.println("네모 그리기");
	}
}

class 삼각형 extends 도형 {
	public void 그리기() {
		System.out.println("삼각형 그리기");
	}
}

public class 그림판 {
	public static void main(String[] args) {
		그림판UI u = new 그림판UI();
		
		u.도형버튼클릭해서그리기(new 삼각형());
		u.도형버튼클릭해서그리기(new 동그라미());
		u.도형버튼클릭해서그리기(new 네모());
	}
}
