
public class BinaryNode {
	private int value; // the associated value of this subtree
    private String token; // ‘a’, ‘+’, ‘3’
    private boolean isOperator; // internal or leaf node
    private int result;
    private BinaryNode right; // right child
    private BinaryNode left; // left child


    //Constructor to create internal node (operators)
    public BinaryNode(String token, BinaryNode lt, BinaryNode rt , boolean isOp) {
        this.token = token;
        this.isOperator = true;
        this.left    = lt;
        this.right   = rt;
        this.value = 0;   //if the node is corresponding to an operator the value will be calculated later
    }

    //Constructor to create leaf nodes (operands)
    public BinaryNode(String token, BinaryNode lt, BinaryNode rt , int value) {
        this.token = token;
        this.isOperator = false;
        this.left    = lt;
        this.right   = rt;
        this.value = value;
    }
    
    public void printInOrder() {
    	if( left != null )
            left.printInOrder( );	            // Left
        System.out.print( token + " " );	    // Node
        if( right != null )
            right.printInOrder( );  			// Right
    }
    
    
    
    public void printPreOrder() {
    	System.out.print( token + " " );		// Node
    	if( left != null ) {
            left.printPreOrder( );				// Left
    	}
        if( right != null )
            right.printPreOrder( );				// Right
    }
    
    public void printPostOrder() {
    	if( left != null ) {
            left.printPostOrder( );				// Left
    	}
        if( right != null )
            right.printPostOrder( );            // Right
        System.out.print( token + " ");			// Node
    }
    
    public float evaluate() {

    	if(this.left == null && this.right == null)		// if left and right both are null,
    		return this.value;							// then we have reached leaf node, return its value
    	
    	float leftEval = left.evaluate();				// recursive method to evaluate left subtree
    	float rightEval = right.evaluate();				// recursive method to evaluate right subtree
    	
    	switch(this.token) {							// switch case implemented to see which operator
    													// is in the token
    		case"-":									// corresponding operations are executed
    			return leftEval - rightEval;			// according to the symbols matched.
    		case "+":
    			return leftEval + rightEval;
    		case "*":
    			return leftEval * rightEval;
    		case "/":
    			if(rightEval == 0) {					// check if denominator is 0, then print appropriate error
    				System.err.println("Division by zero not possible");
    				System.exit(0);
    			}
    			return leftEval / rightEval;
    		default:
    			return leftEval % rightEval;			// by default, return the remainder of division
    	}
    }
    
}
