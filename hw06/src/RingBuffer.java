 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  
  *  A class that represents the data structure that
  *  underpins the Karplus-Strong algorithm.
  *  Takes in a positive command line int argument
  *  that tells the size of the ring buffer.
  *
  */

public class RingBuffer {
    private double[] bufferArray; // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int currentSize;      // number of items in the buffer


    //creates an empty ring buffer with given capacity
    public RingBuffer(int capacity) {
        bufferArray = new double [capacity];
        first = 0;
        last = 0;
        currentSize = 0;
    }

   //Testing Methods Only:

   /*
    * Description: gets value of the first variable
    * Input:   n/a
    * Output:  first int
    */
    public int getFirst() {
        return first;
    }

   /*
    * Description: gets value of the last variable
    * Input:   n/a
    * Output:  last int
    */
    public int getLast() {
        return last;
    }
    
   /*
    * Description: gets the array stored in bufferArray
    * Input:   n/a
    * Output:  bufferArray
    */
    public double[] getBuffer() {
        return bufferArray;
    }

   /*
    * Description: returns the number of items
    *              currently in the buffer
    * Input:   n/a
    * Output:  int current number of items
    */
    public int currentSize() {
        return currentSize;
    }

   /*
    * Description: checks if the buffer is empty
    * Input:   n/a
    * Output:  true if empty; false otherwise
    */
    public boolean isEmpty() {
       return currentSize == 0;
    }

   /*
    * Description: checks if buffer is at capacity
    * Input:   n/a
    * Output:  true if full; false otherwise
    */
    public boolean isFull() {
        return currentSize == bufferArray.length;
    }

   /*
    * Description: inserts given double value
    *              to index last and 
    *              increments last.
    *              will throw exception if
    *              buffer is full
    * Input:   double value to be added
    * Output:  n/a
    */
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("ERROR: Attempting to enqueue " +
                                       "to a full buffer.");
        }
        else {
            bufferArray[last] = x;
            last++;
            currentSize++;

            if (last == bufferArray.length) {
                last = 0;
            }
        }
    }

   /*
    * Description: removes an item from
    *              the first index and
    *              increments first.
    *              returns the dequeued value.
    *              will throw exception if
    *              buffer is empty
    * Input:   n/a
    * Output:  value that was removed
    */
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to dequeue " +
                                       "from an empty buffer.");
        } else {
            double dequeueVal = bufferArray [first];
            first++;
            currentSize--;

            if (first == bufferArray.length) {
                first = 0;
            }
            return dequeueVal;
        }
    }

   /*
    * Description: returns the first item
    *              in the buffer but does not
    *              remove it.
    * Input:   n/a
    * Output:  item at the front
    */
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to peek " +
                                       "at an empty buffer.");
        }
        
        return bufferArray[first];
    }


    // print the contents of the RingBuffer object for debugging
    private void printBufferContents() {
        // print out first, last, and currentSize
        System.out.println("first:        " + first);
        System.out.println("last:         " + last);
        System.out.println("currentSize:  " + currentSize);

        /* print bufferArray's length and contents if it is not null
           otherwise just print a message that it is null */
        if (bufferArray != null) {
            System.out.println("array length: " + bufferArray.length);
            System.out.println("Buffer Contents:");
            for (int i = 0; i < bufferArray.length; i++) {
                System.out.println(bufferArray[i]);
            }
        } else {
            System.out.println("bufferArray is null");
        }
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        /* create a RingBuffer with bufferSize elements
           where bufferSize is a command-line argument */
        int bufferSize = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(bufferSize);

        buffer.enqueue(3);
        buffer.enqueue(4);
        System.out.println("dequeued value:" + buffer.dequeue());
        System.out.println("peek:" + buffer.peek());

        buffer.printBufferContents();
    }

}
