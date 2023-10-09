
public class test {
    public static void main( String [ ] args )
    {
    	//After you change the PriorityQueue to a max heap,
    	// comment the following line and uncomment the next one to check that PriorityQueue is max heap
    	//testMinHeap();
    	testMaxHeap();
    	
    }
    
    public static void testMinHeap() 
    {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );
        int min = 1000000;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
        	if (min > i)
        		min = i;
        	queue.push(  i  );
            if( queue.top( ) != min )
                System.out.println( "Push error! "+i+"   "+queue.top( ));
        }

        for( int i = 1; i < NUMS; i++ )
             if( queue.pop( ) != i )
                 System.out.println( "Pop error!" );
    }
    
    public static void testMaxHeap() 
    {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );
        int max = -1;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
        	if (max < i)
        		max = i;
        	queue.push(  i  );
            if( queue.top( ) != max )
                System.out.println( "Push error! "+i+"   "+queue.top( ));
        }

        for( int i = NUMS-1; i > 0; i-- )
             if( queue.pop( ) != i )
                 System.out.println( "Pop error!" );
    }
}
