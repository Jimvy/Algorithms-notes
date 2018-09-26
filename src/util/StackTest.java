package util;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void testArrayStack() {
        test1(new ArrayStack<>());
        test2(new ArrayStack<>(20));
    }
    @Test
    public void testListStack() {
        test1(new ListStack<>());
        test2(new ListStack<>());
    }
    private void test1(Stack<Integer> st) {
        int N = 500;
        for (int i = 0; i <= N; i++) {
            st.push(i);
            assertEquals(i, (int) st.peek());
        }
        assertFalse(st.isEmpty());
        for (int i = N; i >= 0; i--) {
            assertEquals(i, (int) st.pop());
            assertEquals(i, st.size());
        }
        assertTrue(st.isEmpty());
        try {
            st.pop();
            fail();
        } catch (EmptyStackException e) {
            // ok
        } catch (Exception e) {
            fail();
        }
    }
    private void test2(Stack<Integer> st) {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                st.push(j);
            }
            assertEquals(i, st.size());
            int k = i-1;
            Iterator<Integer> it = st.iterator();
            assertTrue(it.hasNext());
            for (int item : st) {
                assertEquals(k--, item);
            }
            for (int j = i-1; j >= 0; j--) {
                st.pop();
            }
            assertEquals(0, st.size());
            it = st.iterator();
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
}
