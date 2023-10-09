package ErrorTest2;
import java.util.Calendar;

public class AgeCalc {
	public static void main ( String [] args ) {
		int birthYear = Integer.parseInt(args[0]) ;
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);
		System.out.println(" Your age is "+ (currentYear - birthYear));
	}
}
