
public class QuickSort {


    /**
     * Quicksort algorithm.
     * @param a an array of Comparable items.
     */
    public static void quicksort( Comparable [ ] a )
    {
        quicksort( a, 0, a.length - 1 );
    }

    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static final void swapReferences( Object [ ] a, int index1, int index2 )
    {
        Object tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of Comparable items.
     * @param low the left-most index of the subarray.
     * @param high the right-most index of the subarray.
     */
    private static void quicksort( Comparable [ ] a, int first, int last ) {
        if( first < last ) {
        	int pIndex = partition(a, first, last);
            quicksort( a, first, pIndex - 1 );    // Sort small elements
            quicksort( a, pIndex + 1, last );   // Sort large elements
        }
    }

    private static int partition ( Comparable [ ] a, int first, int last ) {
        // your solution here
    	Comparable pivot = a[last];
    	int pivotIndex = first;
    	for (int i = first; i <= last; i++) {
    		if (a[i].compareTo(pivot) >= 0) {
    			swapReferences(a, i, pivotIndex);
    			pivotIndex = i;
    		}
    	}
    	
    	swapReferences(a, pivotIndex, last);
    	return last;
    }
    
    public static void main ( String [ ] args ) {
    	Integer[] items; 
    	items = new Integer[20];
    	for( int i = 0; i < 20; i++) {
            items[i] = new Integer( 20-i );
    	}
    	for( int i = 0; i < 20; i++) {
    		System.out.print(items[i]+" ");
    	}
    	System.out.println();
    	quicksort(items);
    	for( int i = 0; i < 20; i++) {
    		System.out.print(items[i]+" ");
    	}
    }
	
}
