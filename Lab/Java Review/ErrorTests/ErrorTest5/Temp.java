package ErrorTest5;

public class Temp {
	static int noCreated;
	private int data;
	private Temp(int data) 
    { 
		data = 0;
        System.out.printf(" Constructor called "); 
        noCreated += 1;
    } 
    public static Temp create(int data) 
    { 
        Temp obj = new Temp(data); 
        return obj; 
    } 
    public void myMethod() 
    { 
        System.out.printf(" Method called "); 
    } 
}
