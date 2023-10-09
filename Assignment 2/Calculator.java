
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	private ExpressionTree expTree;
	HashMap<String, Integer> VariableTable;
	public Calculator() {
		VariableTable = new HashMap<String, Integer>();
		expTree = null;
	}
	//Add any other method that you need
	
	/*
	 * checks if the string is a number
	 */
	private static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);		// tries to parse to integer
			return true;				// if successful, return true
		} catch(Exception e) {
			return false;				// else return false
		}
	}
	
	/*
	 *  checks the precedence, variable name and symbols.
	 *  prints syntax error if symbol is not found or if the variable is not correct
	 */
	private static int prec_var_check (String s) {
		switch(s) {							// check if s is any operator or "("
		
			case "(":
				return 2;
				
			case "*":
			case "/":
			case "%":
				return 1;
			
			case "+":
			case "-":
				return 0;		
		
		}
		
		// check if the variable name is correct or the string is a number or is ")"
		if((s.length() == 1 && Character.isLetter(s.charAt(0)) || isNumber(s)) || s.equals(")")) {
			return 3;
		}
		// if all conditions are false, then there is syntax error!
		else {
			System.err.println("Syntax error.");		// prints error message
			System.exit(0);								// exit the program
		}
		return -1;
	}
	
	/*
	 * method to check if the string is an operator
	 */
	private static int operator (String s) {
		switch (s) {									// check if "s" is any operator or "("
		case "(":
			return 1;
		case "*":
		case "/":
		case "%":		
		case "+":
		case "-":
			return 0;
		}
		
		return -1;										// else, return -1
	}
	/*
	 * method to check to see does the expression contain
	 * 2 operators simultaneously
	 */
	private void isValidExpression(String [] s) {
		for( int i = 0; i< s.length - 1; i++) {								// loop to iterate over the list of elements
			if(operator(s[i]) == 0) {										
				if(operator(s[i+1]) == 0) {
					System.err.println("Syntax error.");					// if found 2 consecutive operators, print appropriate
					System.exit(0);											// errors and exit the program
				}
				else if(operator(s[i+1]) == 1 && operator(s[i+2]) == 0 ) {	// if found "(" in between, and operators are on either
					System.err.println("Syntax error.");					// side like x+(-y), then appropriate errors and exit
					System.exit(0);											// the program.
				}
			}
		}
	}
	/*
	 * builds expression tree and merges two expression trees into 1.
	 */
	private void buildExpressionTree(Stack <ExpressionTree> treeStack, Stack <String>opStack) {
		 ExpressionTree t1 = treeStack.pop();		// pop 2 trees and store in variables
		 ExpressionTree t2 = treeStack.pop();
		 t1.merge(opStack.pop(), t2, t1 );			// call merge method
		 treeStack.push(t1);						// push the merged tree to treeStack
	}
	
	/*
	 * run() is the main method which will be called to evaluate an input file
	 * It should:
	 * 	read the file, 
	 *      store the variables in the hash (VariableTable) 
	 *      build the expression tree, like:   expTree = stringToExpressionTree() 
	 *      evaluate tree, you should have a method evaluate for class ExpressionTree
	 *			like:   result = expTree.evaluate() 
	 *      print result
	 */
	public void run(String inputFile) {
		//to be implemented
		try {
			Scanner read = new Scanner (new File (inputFile));				// scanner to read every line
			String currLine = "";											// variable to store the current line
			while(read.hasNextLine()) {										// check if file has next line
				currLine = read.nextLine();									// current line stored to currLine
				String[] tokens = currLine.trim().split("\\s+");			// convert string into string array without white spaces
				
				if(tokens.length > 1) {										// variable = value and expression will have length > 1
					if (tokens[1].equals("="))								// if at index 1, it has '=' sign, then it is variable = value
						VariableTable.put(tokens[0], Integer.parseInt(tokens[2]) );	// put corresponding values into hash map
					else
						break;												// else, it is a expression, so get out of loop
				}
					
				else if (tokens.length == 1 && read.hasNextLine())			// check if it is variable and has a next line
					VariableTable.put(tokens[0], null);						// put value into hash map with null as its value
				
				else														// when we reach the end of file, the last line is an expression
					break;													// get out of loop
			}
			expTree = stringToExpressionTree(currLine);						// call method and store expression tree to expTree
			int result = expTree.evaluate();								// call method to evaluate the tree and store into result
			System.out.println(result);										// print result
			
		} catch (FileNotFoundException e) {									// if file is not found
			System.err.println("File not found!");
			e.printStackTrace();											// appropriate errors are printed
		}
		
	}
	
	
	
	/*
	 * method to convert a string to an expression tree
	 */
	private ExpressionTree stringToExpressionTree(String infixExpression){
		Stack<ExpressionTree> treeStack = new Stack<ExpressionTree>();		// create 2 stacks
		Stack<String> opStack = new Stack<String>();

                /* you can convert the infixExpression to a list of tokens
                 * by spliting it based on whitespace
                 * for example if the input is "x + ( 10 - y )"
                 * list of tokens will be ["x", "+", "(", "10", "-", "y", ")"]
		 */
         String[] tokens = infixExpression.trim().split("\\s+");
         
        isValidExpression(tokens); 										// method to check that the expression does not
        																// contain 2 operators simultaneously
         
         for ( int i = 0; i < tokens.length; i++) {					    // loop to iterate over each element of tokens

        	 if(prec_var_check(tokens[i]) == 2) {						// check for (
        		 opStack.push(tokens[i]);								// insert at top of stack
        	 }	
        	 else if(prec_var_check(tokens[i]) == 1) {					// check for *, /, %
        		 if(!opStack.empty()) {									// check if stack is not empty
        			 while( prec_var_check(opStack.peek()) == 1  ) {	// check for *, /, %, if true, continue
        				 buildExpressionTree(treeStack, opStack);		// call method to build expression tree
        				 if(opStack.empty())							// exit the loop when opStack is empty
        					 break;
        			 }
        		 }
        		 opStack.push(tokens[i]);								// insert at top of stack
        	 }
        		 
        	 else if (prec_var_check(tokens[i]) == 0) {					// check for +, -
        		 if(!opStack.empty()) {									// check if stack is not empty
	        		 while(prec_var_check(opStack.peek()) != 2) {		// check ( is not in the expression
	        			 buildExpressionTree(treeStack, opStack);		// call method to build expression tree
	            		 if(opStack.empty())							// exit the loop when opStack is empty
	            			 break;
	        		 }
        		 }
            	 opStack.push(tokens[i]);								// insert at top of stack
        	}
        	 
        	 else if (tokens[i].equals(")")) {							// check for balanced parenthesis
        		 if (!opStack.contains("(")) {							// if unbalanced
        			 System.err.println("Syntax error.");				// print syntax error
        			 System.exit(0);									// exit program
        		 }
        		 while(prec_var_check(opStack.peek()) != 2 ) {			// continue loop till program encounters "("
        			 buildExpressionTree(treeStack, opStack);			// call method to build expression tree
        		 }
        		 opStack.pop();											// remove "(" from opStack
        	 }
        	 else if (Character.isDigit(tokens[i].charAt(0))) {			// check if the token is a number
        		 // push new Expression Tree with number as a string and number itself
        		 treeStack.push(new ExpressionTree (tokens[i], Integer.parseInt(tokens[i]))); 
        	 }
        	 
        	 else {														// check if token is variable.
        		 if(! VariableTable.containsKey(tokens[i])) {			// if hash map does not find the value
        			 System.err.println("Variable error.");				// associated with key, print Variable error
        			 System.exit(0);									// exit the program
        		 }
         		 // push new Expression Tree with variable and its value
        		 treeStack.push(new ExpressionTree (tokens[i], VariableTable.get(tokens[i])));
        	 }
        	     

         }
         
         // after forming all the expression trees, if there is still "(" left,
         // then the expression had unbalanced parenthesis.
         if(opStack.contains("(")) {
        	 System.err.println("Syntax error.");		// print syntax error
			 System.exit(0);							// exit the program
         }
         
         while(!opStack.empty()) {						// if opStack is not empty
        	buildExpressionTree(treeStack, opStack);	// call method to build expression tree
         }
         
         // now the treeStack should be left with only 1 expression tree, so remove it from stack and return to the method
         return treeStack.pop();
	}
	
			//complete this method
			// You can find the algorithm in the description of the assignment
}
