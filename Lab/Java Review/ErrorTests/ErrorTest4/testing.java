package ErrorTest4;

public class testing implements Irrational {
	
	public testing () {
		double x = 0;
	}
	public double PINumber() {
		return Math.PI;
	}
	
	public double SquareRootOfTwo (int x ) {
		return Math.sqrt(x);
	}
	
	
	public static void main ( String [] args ) {

		Irrational app = new testing();
		
		System.out.printf("The square root of %d is %.5f",5, app.SquareRootOfTwo (5) );
	}

}
