
public class ExceptionEx {
	public static void main(String[] args) {
		int x = 10;
		int y = 0;
		int r = 0;
		
		// 예외 처리 문법
		try {
			r = x/y;
			System.out.println(r);
		} catch (ArithmeticException e) { // (오류 종류)Exception 
			// 예외를 잡았냈을 때 대응 코드
			System.out.println("오류 발생");
		}
		
		// 배열 예외 처리
		int[] k = new int[3];
		k[0] = 0;
		k[1] = 10;
		k[2] = 100;
		
		try {
			System.out.println(k[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("오류오류");
			System.out.println(e);	// 예외 종류 간략하게 출력
			// e.printStackTrace();
		}
		
		// 다중 예외 처리
		try {
			int x1 = 10;
			int x2 = 0;
			int r2 = x1/x2;
			
			int[] xx1 = {10, 20, 30};
			System.out.println(xx1[3]);
			
		} catch (Exception e) {
			System.out.println("Excption으로 다중 예외 처리");
			System.out.println(e);
		} finally {
			// 예외 발생 여부와 상관없이 무조건 실행
		}
		
		// 각 오류에 대해 각각 대응하고 싶다면 오류 종류 별로 코드 작성
//		catch (ArrayIndexOutOfBoundsException e) {
//			
//		} catch (ArithmeticException e) {
//			
//		}	
		
		System.out.println("end");
	}
}

