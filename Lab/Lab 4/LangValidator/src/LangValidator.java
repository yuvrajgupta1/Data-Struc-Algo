
/******************************************************************************
 *  Under Linux:
 *  Compilation:  javac LangValidator.java
 *  Execution:    java LangValidator file.txt
 *  
 *  Reads in a text file and checks to see if the strings (one string per line)
 *  belongs to the following language:
 *  L={w$w' | w is possibly an empty string of characters except $, and w' = reverse(w)}
 *
 *  % java  java LangValidator
 *  $
 *  true
 *
 *  % java LangValidator
 *  aab$aba
 *  false
 *
 ******************************************************************************/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;


public class LangValidator {
	private static final char delimeter    = '$';
    public static boolean isValidString(String s) {
    	//Hint: use a stack of characters:
        Stack<Character> stack = new Stack<Character>();
        Stack<Character> stack1 = new Stack<Character>();
        if(s.length() == 1 && s.charAt(0) == delimeter ) {
        	return true;						// returns true only if length = 1 and '$' is present
        }
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
        	if(s.charAt(i) == delimeter || s.charAt(j) == delimeter ) {
        		continue;
        	}
        	stack.push(s.charAt(i));
        	stack1.push(s.charAt(j));
        }
        
    	if(stack.empty() || stack1.empty() ) 
    		return false;
        return stack.equals(stack1);
    }

	
    public static void main(String[] args) throws FileNotFoundException {
       	Scanner fin = new Scanner(new FileReader("input.txt"));
        while (fin.hasNext()) {
            String str = fin.nextLine();
            System.out.println(str);
            System.out.println(isValidString(str));
            System.out.println();
        }
    }

	
	
}
