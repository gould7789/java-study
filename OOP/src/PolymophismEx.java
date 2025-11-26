
class A {
	public void printA() {
		System.out.println("A");
	}
}

class B extends A {
	// 상위 클래스에서 상속 받은 메서드를 기능 재정비 -> method Overriding
	public void printA() {
		System.out.println("B클래스 안의 printA()");
	}
	
	public void printB() {
		System.out.println("B");
	}
}

class C extends A {
	public void printA() {
		System.out.println("C클래스 안의 printA()");
	}
	
	public void printC() {
		System.out.println("C");
	}
}

public class PolymophismEx {
	public static void main(String[] args) {
		// (1-1)방식
		A a = new A();
		a.printA();
		
		// (1-2)방식
		// 다형성을 사용하기 위해 이 코드를 사용해야 함
		A aa = new B();
		aa.printA();
		// aa.printB(); -> 이건 불가능
		
		// (2-1)방식
		C c = new C();
		c.printC();
		c.printA();
		
		// (2-2)방식
		A cc = new C();
		cc.printA();
		
		// 다형성
		System.out.println("-------------");
		aa.printA();
		cc.printA();
		
		// 다형성 문법 적용의 조건
		// (1) 클래스가 상속관계에 있어야함
		// (2) 메소드 오버라이딩
		// (3) A aa = new B();
		
//		B b = new B();
//		b.printA();
//		b.printB();
	}
}
