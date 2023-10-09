class BinaryNode<AnyType>
{
    protected AnyType             element;  // The data in the node
    protected BinaryNode<AnyType> left;     // Left child
    protected BinaryNode<AnyType> right;    // Right child
    
    // Constructor
    public BinaryNode( ){
        this( null, null, null );
    }
    
    public BinaryNode( AnyType theElement) {
        this( theElement, null, null );
    }

    public BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, 
                           BinaryNode<AnyType> rt) {
        element = theElement;
        left    = lt;
        right   = rt;
    }
}