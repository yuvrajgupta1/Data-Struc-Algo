
public class ExpressionTree {
	private BinaryNode root;
	private String [] operators = {"*", "/", "+", "-", "%"};
	
	// constructor to create an empty tree
	public ExpressionTree() {
        root = null;
    }

	// constructor to build an expression with just one node 
    public ExpressionTree( String token , int value){
        root = new BinaryNode( token, null, null, value );
    }
    
    // merge method: it takes two other subtrees and a token as root
    // root will be an operand
    public void merge( String token, ExpressionTree t1, ExpressionTree t2 ) {
        if( t1.root == t2.root && t1.root != null ) {
            System.err.println( "leftTree==rightTree; merge aborted" );
            return;
        }

        if (IsOperator(token)==false) {
            System.err.println( "root should be an operator" );
            return;
        }
        // Allocate new node (internal constructor)
        root = new BinaryNode( token, t1.root, t2.root , true);

        // Ensure that every node is in just one tree!
        if( this != t1 )
            t1.root = null;
        if( this != t2 )
            t2.root = null;
     }

    private boolean IsOperator(String token) {
        //To be implemented!!
    	for(int i = 0; i < 5; i++) {			// iterate over the list of operators
    		if(token.equals(operators[i])) {
    			return true;
    		}

    	}
    	return false;
    }
    
    public void printPreOrder() {
        //To be implemented!!  
    	if( root != null )
            root.printPreOrder();
    }

    public void printInOrder(){
        if( root != null )
           root.printInOrder();
    }

    public void printPostOrder() {
        //To be implemented!! 
    	if( root != null )
            root.printPostOrder();
    }

    /*
     * It evaluates the expression tree and return the result
     * ( you can add a helper method in class BinaryNode
     *   like what I did for printInOrder() )
     */
    public int evaluate() {
        //To be implemented!! 
    	if(root != null) {
    	return (int) root.evaluate();	// type casting from float to int
    	}
    	return 0;
    }
}
