import java.util.NoSuchElementException;

public class ArrayBasedDeque<AnyType> implements deque<AnyType> {
	
	private static int MAX_SIZE = 5;  // initial array size
	//DO NOT CHANGE this, it is set to 5 to make sure your code
	//will pass all the tests and works with no issue.
	private AnyType items[];
	private int numItems;
	int front;
	int rear;
	
	// add all data fields which are required

	/**
	 *  ArrayBasedDeque() constructs an empty deque.
	 */
	public ArrayBasedDeque(){ 
		//complete
		items = (AnyType []) new Object[MAX_SIZE];
		front = -1;
		rear = -1;
		numItems = 0;
	}
	
	/**
	 * Returns the size of the deque
	 * @return the number of elements in this deque
	 */
	public int size() {
		//complete
		return numItems;
	}
	
	/**
	 * Removes all elements from this deque
	 */
	public void clear() {
		//complete
		numItems = 0;
		front = -1;
		rear = -1;
	}
	  
	/**
	 * Tests if the deque contains no element
	 * @return true if the deque contains no element
	 */
	public boolean isEmpty() {
	    //complete
		return numItems == 0;
	}
	
	private void expand() {
		AnyType newArray[] = (AnyType[])new Object [2 * MAX_SIZE];
		int j = front;													// Allocate a new array, twice as long.
	    for (int i = 0; i < numItems; i++) {
	    	newArray[i] = this.items[j];	 							// Copy items to the bigger array.
	    	if (j == MAX_SIZE - 1) {
	    		j = 0;
	    	}
	    	else {
	    		j++;
	    	}
	    	
	    }    
	    this.items = newArray;
		front = 0;														// Replace the too-small array with the new one.
	    MAX_SIZE *=2;  

	}
	
	/**
	  * Adds an item to this front of the deque
	  * @param x any object
	  */
	public void addFirst(AnyType x) {
		//complete
		if ( (front == 0 && rear == MAX_SIZE - 1) || ( front == rear + 1 ) ) {
			expand();
		}
		
		else if ( isEmpty() ) {
			front = 0;
			rear = 0;
		}
		
		else if ( front == 0 ) {
			front = MAX_SIZE - 1;
		}
		
		else {
			front--;
		}
		
		this.items[front] = x;
		numItems++;
	}
	
	/**
	  * Adds an item to this rear of the deque
	  * @param x any object
	  */
	public void addLast(AnyType x) {
		//complete
		if ( (front == 0 && rear == MAX_SIZE - 1) || ( front == rear + 1 ) ) {
			expand();
		}
		
		else if ( isEmpty() ) {
			front = 0;
			rear = 0;
		}
		
		else if ( rear == MAX_SIZE - 1 ) {
			rear = 0;
		}
		
		else {
			rear++;
		}
		
		this.items[rear] = x;
		numItems++;
	}
	
	/**
	 * Remove and return the item at the front of the deque.
	 * @return the item that was removed from the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType removeFirst() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {
			throw new NoSuchElementException ("It is empty");
		}
		
		int temp = front;
		if ( front == rear ) {
			front = -1;
			rear = -1;
		}
		
		else if ( front == MAX_SIZE - 1 ) {
			front = 0;
		}
		
		else {
			front++;
		}
		numItems--;
		return this.items[temp];
		
	}
	
	/**
	 * Remove and return the item at the rear of the deque.
	 * @return the item that was removed from the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType removeLast() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {
			throw new NoSuchElementException ("It is empty");
		}
		
		int temp = rear;
		if ( front == rear ) {
			front = -1;
			rear = -1;
		}
		
		else if ( rear == 0 ) {
			rear = MAX_SIZE - 1;
		}
		
		else {
			rear--;
		}
		numItems--;
		return this.items[temp];
	}
	
	/**
	 * Returns the item at position index in deque
	 * front of the deque will be considered as index 0, 
	 * index 1 is the next item, and so on
	 * @param index the index to search in
	 * @return return the item in index
	 * @throws IndexOutOfBoundsException if index is out ot bound
	 */
	public AnyType get(int index) throws IndexOutOfBoundsException {
		//complete
		if ( isEmpty() ) {
			throw new IndexOutOfBoundsException ("index: " + index );
		}
		index = (index + front) % MAX_SIZE;
		return this.items[index];
	}
	
	/**
	 * Return the item at the rear of the deque.
	 * @return the item the rear of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType getLast() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {
			throw new NoSuchElementException ("It is empty");
		}
		return this.items[rear];
	}
	
	/**
	 * Return the item at the front of the deque.
	 * @return the item the front of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType getFirst() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {
			throw new NoSuchElementException ("It is empty");
		}
		return this.items[front];
	}
	
	public String toString() {
		//complete
		String result = "[ ";
		int i = front;
		while( i != rear) {
			result += this.items[i] + " ";
			i = (i + 1) % MAX_SIZE;
		}
		if (rear != -1)
			result += this.items[rear];
		
		return result + " ]";
	}
}
