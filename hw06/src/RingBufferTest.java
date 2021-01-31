 /*  Name: Allison Chen
  *  PennKey: allchen
  *  Recitation: 209
  *
  *  JUnit testing for the RingBuffer class.
  *  
  */

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import org.junit.Before;

public class RingBufferTest {

    private RingBuffer ringBuffer;

    @Before
    public void setup() {
        ringBuffer = new RingBuffer(10);
    }

    //tests for the constructor:

    @Test
    public void testGetFirst() {
        int expected = 0;
        int actual = ringBuffer.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLast() {
        int expected = 0;
        int actual = ringBuffer.getLast();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentSize() {
        int expected = 0;
        int actual = ringBuffer.currentSize();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBuffer() {
        double expected = 0.0;
        double [] actual = ringBuffer.getBuffer();
        for (int i = 0; i < ringBuffer.currentSize(); i++) {
            assertEquals(expected, actual[i], 0.000001);
        }
    }

    //testing when ringBuffer is not empty
    @Test
    public void notIsEmpty() {
        ringBuffer.enqueue(1.0);
        assertFalse(ringBuffer.isEmpty());
    }

    //testing when ringBuffer is empty
    @Test
    public void testIsEmpty() {
        assertTrue(ringBuffer.isEmpty());
    }

    //testing when ringBuffer is not full
    @Test
    public void notIsFull() {
        assertFalse(ringBuffer.isFull());
    }

    //testing when ringBuffer is full
    @Test
    public void testIsFull() {
        for (int i = 0; i  < 10; i++) {
            ringBuffer.enqueue(i);
        }
        assertTrue(ringBuffer.isFull());
    }

    //testing the throw error in enqueue
    @Test (expected = RuntimeException.class)
    public void enqueueError() {
        for (int i = 0; i < 10; i++) {
            ringBuffer.enqueue(1.0);
        }
        ringBuffer.enqueue(1.0);
    }

    //testing if enqueue works
    @Test
    public void enqueueTest() {
        ringBuffer.enqueue(1.0);

        assertEquals(1, ringBuffer.getLast(), 0);
        assertEquals(1, ringBuffer.currentSize());
    }

    //testing if wrapping in enqueue works
    @Test
    public void enqueueWrap() {
        for (int i = 0; i < 10; i++) {
            ringBuffer.enqueue(1.0);
        }

        assertEquals(0, ringBuffer.getLast(), 0);
        assertEquals(10, ringBuffer.currentSize());
    }

    //testing the throw error in dequeue
    @Test (expected = RuntimeException.class)
    public void dequeueError() {
        ringBuffer.dequeue();
    }

    //testing if dequeue works
    @Test
    public void dequeueTest() {
        for (int i = 0; i < 10; i++) {
            ringBuffer.enqueue(i);
        }

        assertEquals(0, ringBuffer.dequeue(), 0.00001);
        assertEquals(1.0, ringBuffer.dequeue(), 0.00001);
        assertEquals(2.0, ringBuffer.getFirst(), 0.0001);
        assertEquals(8, ringBuffer.currentSize());
    }  

    //testing if wrapping in dequeue works
    @Test
    public void dequeueWrap() {
        for (int i = 0; i < 10; i++) {
            ringBuffer.enqueue(1.0);
        }
        assertEquals(0, ringBuffer.getFirst(), 0.00001);
    }

    //testing the throw error in peek
    @Test (expected = RuntimeException.class)
    public void peekError() {
        ringBuffer.peek();
    }

    //testing if peek works
    @Test
    public void peekTest() {
        for (int i = 0; i < 10; i++) {
            ringBuffer.enqueue(1.0);
        }
        assertEquals(1.0, ringBuffer.peek(), 0.000001);
    }
}