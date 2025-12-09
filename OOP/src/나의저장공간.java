
class Person {
	
}

public class 나의저장공간 {
//	// 데이터를 저장하기 위한 메소드
//	public void push(String str) {
//		
//	}
//	
//	public void push(Person p) {
//		
//	}
//		
//	// 나의저장공간에서 데이터를 가져오기 위한 메소드
	public Object pop() {
		return new Person();
	}
//	public String pop() {
//		return "default";
//	}
	
	
	public static void main(String[] args) {
		나의저장공간 n =new 나의저장공간();
		String r = n.pop();
		System.out.println(r);
	}
}
