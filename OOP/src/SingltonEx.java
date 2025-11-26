
class Camera {
	static Camera c;
	
	private Camera() {
	}
	
	public static Camera open() {
		c = new Camera();
		return c;
	}
}

public class SingltonEx {
	public static void main(String[] args) {
		Camera c1 = Camera.open();
		
		Camera c2 = Camera.open();
	}
}
