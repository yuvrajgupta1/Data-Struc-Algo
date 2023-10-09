package src;
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
		AnyType newArray[] = (AnyType[])new Object [2 * MAX_SIZE];		// Allocate a new array, twice as long.
		int j = front;													
	    for (int i = 0; i < numItems; i++) {
	    	newArray[i] = this.items[j];	 							// Copy items to the bigger array.
	    	if (j == MAX_SIZE - 1) {
	    		j = 0;
	    	}
	    	else {
	    		j++;
	    	}
	    	
	    }    
	    this.items = newArray;											// Replace the too-small array with the new one.
		front = 0;	
		rear = numItems - 1;											
	    MAX_SIZE *=2;  

	}
	
	/**
	  * Adds an item to this front of the deque
	  * @param x any object
	  */
	public void addFirst(AnyType x) {
		//complete
		if ( (front == 0 && rear == MAX_SIZE - 1) || ( front == rear + 1 ) ) {	// if array is full, then expand the array
			expand();
		}
		
		if ( isEmpty() ) {								// if array is empty, then point front to start of list
			front = 0;
			rear = 0;
		}
		
		else if ( front == 0 ) {						// else if front is pointing at the beginning,
			front = MAX_SIZE - 1;						// then go to end of the list
		}
		
		else {											// else decrease the value of front by 1
			front--;
		}
		
		this.items[front] = x;							// insert the value at the index at which front is pointing
		numItems++;										// increment the number of items
	}
	
	/**
	  * Adds an item to this rear of the deque
	  * @param x any object
	  */
	public void addLast(AnyType x) {
		//complete
		if ( (front == 0 && rear == MAX_SIZE - 1) || ( front == rear + 1 ) ) {	// if array is full, then expand the array
			expand();
		}
		
		if ( isEmpty() ) {								// if array is empty, then point rear to start of list
			front = 0;
			rear = 0;
		}
		
		else if ( rear == MAX_SIZE - 1 ) {				// else if front is pointing at the end,
			rear = 0;									// then go to beginning of the list
		}
		
		else {											// else increment the value of rear by 1
			rear++;
		}
		
		this.items[rear] = x;							// insert the value at the index at which rear is pointing
		numItems++;										// increment the number of items
	}
	
	/**
	 * Remove and return the item at the front of the deque.
	 * @return the item that was removed from the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType removeFirst() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {										// if array is empty, then throw exception
			throw new NoSuchElementException ("It is empty");
		}
		
		int temp = front;										// temporary variable to store the value of front
		if ( front == rear ) {									// if array has 1 element, call clear function
			clear();
		}
		
		else if ( front == MAX_SIZE - 1 ) {						// if front is at the end of array,
			front = 0;											// bring front to the beginning
			numItems--;											// decrement the number of items
		}
		
		else {													// if the above 2 conditions are not true
			front++;											// increment the value of front
			numItems--;											// decrement number of items
		}
		
		return this.items[temp];								// return the item which is deleted
		
	}
	
	/**
	 * Remove and return the item at the rear of the deque.
	 * @return the item that was removed from the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType removeLast() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {										// if array is empty, then throw exception
			throw new NoSuchElementException ("It is empty");
		}
		
		int temp = rear;										// temporary variable to store the value of rear
		if ( front == rear ) {									// if array has 1 element, call clear function
			clear();											
		}
		
		else if ( rear == 0 ) {									// if rear is at the beginning of array,
			rear = MAX_SIZE - 1;								// bring rear to the end
			numItems--;											// decrement the number of items
		}
		
		else {													// if the above 2 conditions are not true
			rear--;												// decrement the value of rear
			numItems--;											// decrement number of items
		}
		return this.items[temp];								// return the item which is deleted
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
		index = (index + front) % MAX_SIZE;						// consider front as index 0;
		if ( index > MAX_SIZE - 1  ) {							// if index > max size of array, then throw exception
			throw new IndexOutOfBoundsException ("index: " + index );
		}
		
		return this.items[index];								// return the item at the given index
	}
	
	/**
	 * Return the item at the rear of the deque.
	 * @return the item the rear of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType getLast() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {										// if array is empty, throw exception
			throw new NoSuchElementException ("It is empty");
		}
		return this.items[rear];								// return the item which is at the end of the array
	}
	
	/**
	 * Return the item at the front of the deque.
	 * @return the item the front of the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	public AnyType getFirst() throws NoSuchElementException{
		//complete
		if ( isEmpty() ) {										// if array is empty, throw exception
			throw new NoSuchElementException ("It is empty");
		}
		return this.items[front];								// returns the item which is at the beginning of the array
	}		
	
	public String toString() {									// converts the array into the string
		//complete
		String result = "[";
		int i = front;											// temporary variable to iterate through the array
		while( i != rear) {
			result += this.items[i] + ", ";
			i = (i + 1) % MAX_SIZE;								// increment by 1 and when we reach to the end,
		}														// it starts from the beginning
		if (rear != -1)											// if array is not empty
			result += this.items[rear];							// then concatenate the string
		
		return result + "]";									// returns the string
	}
}
