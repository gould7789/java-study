import java.util.ArrayList;

public class ArrayListEx {
	public static void main(String[] args) {
		ArrayList a  = new ArrayList();
		a.add("ABC");		 // 0번째
		a.add(new Person()); // 1번째
//		a.add(new Animal()); // 2번째
		
		String r1 = (String)a.get(0);
		Person r2 = (Person)a.get(1);
//		Animal r3 = (Animal)a.get(2);
		
		ArrayList<도형> 도형모음 = new ArrayList<도형>();
		도형모음.add(new 네모());
		도형모음.add(new 삼각형());
		도형모음.add(new 동그라미());
		
		// OOP 코드가 아님
//		네모 x0 = (네모)도형모음.get(0);
//		삼각형 x1 = (삼각형)도형모음.get(1);
//		동그라미 x2 = (동그라미)도형모음.get(2);
		
		// 그나마 OOP
//		도형 x0 = (도형)도형모음.get(0);
//		도형 x1 = (도형)도형모음.get(1);
//		도형 x2 = (도형)도형모음.get(2);
		
		// 위에 코드의 중복되는 부분 제거
		for (int i = 0; i < 도형모음.size(); i ++) {
			도형 x = (도형)도형모음.get(i);
			x.그리기();
		}
		for (Object x : 도형모음) {
			System.out.println(x.toString());
		}
	}
}
