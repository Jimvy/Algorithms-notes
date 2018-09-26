package util;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void testListQueue() {
        //
    }
    public void test1(Queue<Integer> q) {
        int N = 500;
        for (int i = 0; i < N; i++) {
            q.enqueue(i);
            assertEquals(i, (int) q.peek());
            assertEquals(i+1, q.size());
        }
        assertFalse(q.isEmpty());
        int k = 0;
        for (int i : q) {
            assertEquals(k++, i);
        }
        Iterator<Integer> it = q.iterator();
        assertTrue(it.hasNext());
        for (int i = 0; i < N; i++) {
            assertEquals(i, (int) q.dequeue());
        }
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
        try {
            q.peek();
            fail();
        } catch (NoSuchElementException e) {
            //
        } catch (Exception e) {
            fail();
        }
        try {
            q.dequeue();
            fail();
        } catch (NoSuchElementException e) {
            //
        } catch (Exception e) {
            fail();
        }
        it = q.iterator();
        assertFalse(it.hasNext());
        try {
            it.next();
            fail();
        } catch (NoSuchElementException e) {
            //
        } catch (Exception e) {
            fail();
        }
    }
}
