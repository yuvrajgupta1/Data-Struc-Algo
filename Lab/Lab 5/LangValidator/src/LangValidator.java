
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
        int i = 0;
        char c;
        while (i < s.length()) {
        	c = s.charAt(i);
            if (c == delimeter)
            	break;
            stack.push(c);
            i++;
        }
        i++;
        while (i < s.length()) {
        	c = s.charAt(i);
        	if (stack.isEmpty())        return false;
        	if (stack.pop() != c) 		return false;
        	i++;
        }
        return stack.isEmpty();
    }
    
    public static boolean isValidStringRec(String s, int index, int end) { // you may change the header if you need
    	// It should be recursive
    	if(index == end) {
    		return true;		// base case for recursion
    	}
    	
    	if(s.charAt(index) == s.charAt(end) && (s.charAt(index) != delimeter )) { // checks if char matches at the start and end and the char is not '$'
			return isValidStringRec (s, index+1, end-1);			// increase index by 1 and end by 2
    	}
    	
    	return false;
    }

	
    public static void main(String[] args) throws FileNotFoundException {
       	Scanner fin = new Scanner(new FileReader("input.txt"));
        while (fin.hasNext()) {
            String str = fin.nextLine();
            System.out.println(str);
            System.out.println(isValidStringRec(str, 0, str.length() - 1));
            System.out.println();
        }
    }

	
	
}
