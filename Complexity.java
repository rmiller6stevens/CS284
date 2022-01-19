package homework;

public class Complexity {
	// O(n)
	public static void method0(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			System.out.println("Operation " + counter++);
		}
	}
	//O(n^2)
	public static void method1(int n) {
	int counter = 0;
	for (int j = 0; j < n; j++) {
	for (int i = 0; i < n; i++) {
		System.out.println("Operation " + counter++);
		}
	}
}
	//O(n^3)
	public static void method2(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for( int k = 0; k < n; k++) {
					System.out.println("Operation " + counter++);
				}
			}
		}
	}
	//O(log(n))
	public static void method3(int n) {
		int counter = 0;
		for(int i = 1; i < n; i*=2) {
			System.out.println("Operation " + counter++);
		}
	}
	//O(n log(n))
	public static void method4(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j*=2) {
				System.out.println("Operation2 " + counter++);
			}
			counter = 0;
			System.out.println("Operation1 " + counter);
		}
	}
	//O(log(log(n))
	public static void method5(int n) {
		int counter = 0;
		for(double i = n; i > 2; i = Math.sqrt(i)) {
			System.out.println("Operation " + counter++);
			
		}
	}
	//O(2^n)
	public static void method6(int n) {
		if(n == 0) {
			System.out.println("count");
		}
		if(n > 0) {
		method6(n-1);	
		method6(n-1);
	}
}
	//tests
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method5(1);
	}
}
