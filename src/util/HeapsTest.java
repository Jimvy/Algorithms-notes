package util;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeapsTest {
    @Test
    public void testMinHeap() {
        MinPriorityQueue<Integer> pq = new MinHeap<>(64);
        test1(pq);
    }
    @Test
    public void testMaxHeap() {
        MaxPriorityQueue<Integer> pq = new MaxHeap<>(64);
        test2(pq);
    }
    @Test
    public void testMedianHeap() {
        String a = "hello";
        MedianHeap<Integer> medianHeap = new MedianHeap<>(64);
        for (int i = 0; i < 200; i++) {
            medianHeap.insert(i);
            assertEquals((i+1)/2, (int) medianHeap.median());
        }
        for (int i = 0, j=100; i < 100; i++, j++) {
            assertEquals(j, (int) medianHeap.deleteMedian());
            assertEquals(j-1-2*i, (int) medianHeap.deleteMedian());
        }
        assertEquals(0, medianHeap.size());
        for (int i = 0; i < 100; i++) {
            medianHeap.insert(i);
            assertEquals(i, (int) medianHeap.median());
            medianHeap.insert(200-i);
            assertEquals(200-i, (int) medianHeap.median());
        }
    }
    @Test
    public void testMinMaxHeap() {
        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(64);
        for (int i = 0; i < 100; i++) {
            minMaxHeap.insert(i);
            assertEquals(0, (int) minMaxHeap.min());
            assertEquals(i, (int) minMaxHeap.max());
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, (int) minMaxHeap.deleteMin());
            minMaxHeap.insert(i+100);
            assertEquals(i+100, (int) minMaxHeap.max());
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(i+100, (int) minMaxHeap.deleteMax());
            minMaxHeap.insert(i);
            assertEquals(i, (int) minMaxHeap.min());
        }
        for (int i = 0; i < 50; i++) {
            assertEquals(i, (int) minMaxHeap.deleteMin());
            assertEquals(99-i, (int) minMaxHeap.deleteMax());
        }
        assertEquals(0, minMaxHeap.size());
        for (int i = 51; i <= 150; i++) {
            minMaxHeap.insert(i);
            minMaxHeap.insert(-i);
            assertEquals(i, (int) minMaxHeap.max());
            assertEquals(-i, (int) minMaxHeap.min());
            assertEquals("At iteration " + i, 2*(i-50), minMaxHeap.size());
        }
        for (int i = 150; i >= 101; i--) {
            assertEquals(i, (int) minMaxHeap.deleteMax());
            assertEquals(-i, (int) minMaxHeap.deleteMin());
        }
        for (int i = 1, j = 100; i <= 50; i++, j--) {
            minMaxHeap.insert(i);
            minMaxHeap.insert(-i);
            assertEquals(j, (int) minMaxHeap.deleteMax());
            assertEquals(-j, (int) minMaxHeap.min());
            assertEquals(-j, (int) minMaxHeap.deleteMin());
            assertEquals(j-1, (int) minMaxHeap.max());
            assertEquals(100, minMaxHeap.size());
        }
        for (int i = 50; i >= 1; i--) {
            assertEquals(i, (int) minMaxHeap.deleteMax());
            assertEquals(-i, (int) minMaxHeap.deleteMin());
        }
        assertEquals(0, minMaxHeap.size());
    }
    public void test1(MinPriorityQueue<Integer> priorityQueue) {
        for (int i = 0; i < 100; i++) {
            priorityQueue.insert(199 - i);
            priorityQueue.insert(i);
        }
        assertFalse(priorityQueue.isEmpty());
        for (int i = 0; i < 200; i++) {
            assertEquals(i, (int) priorityQueue.deleteMin());
        }
        assertEquals(0, priorityQueue.size());
        assertTrue(priorityQueue.isEmpty());
        for (int i = 100; i >= 0; i--) {
            priorityQueue.insert(i);
            assertEquals(i, (int) priorityQueue.min());
        }
        for (int i = 0; i < 200; i++) {
            assertEquals(i, (int) priorityQueue.deleteMin());
            priorityQueue.insert(i+101);
            assertEquals(101, priorityQueue.size());
        }
        for (int i = 200; i <= 300; i++) {
            assertEquals(i, (int) priorityQueue.deleteMin());
        }
        assertEquals(0, priorityQueue.size());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int k = random.nextInt()%200;
            priorityQueue.insert(k);
            pq.add(k);
        }
        for (int i = 0; i < 200; i++) {
            int k = random.nextInt()%200;
            assertEquals(pq.poll(), priorityQueue.deleteMin());
            pq.add(k);
            priorityQueue.insert(k);
        }
    }
    public void test2(MaxPriorityQueue<Integer> priorityQueue) {
        for (int i = 99; i >= 0; i--) {
            priorityQueue.insert(i);
            priorityQueue.insert(199 - i);
        }
        assertFalse(priorityQueue.isEmpty());
        for (int i = 199; i >= 0; i--) {
            assertEquals(i, (int) priorityQueue.deleteMax());
        }
        assertEquals(0, priorityQueue.size());
        assertTrue(priorityQueue.isEmpty());
        for (int i = 0; i <= 100; i++) {
            priorityQueue.insert(i);
            assertEquals(i, (int) priorityQueue.max());
        }
        for (int i = 100; i > -100; i--) {
            assertEquals(i, (int) priorityQueue.deleteMax());
            priorityQueue.insert(i-101);
            assertEquals(101, priorityQueue.size());
        }
        for (int i = -100; i >= -200; i--) {
            assertEquals(i, (int) priorityQueue.deleteMax());
        }
        assertEquals(0, priorityQueue.size());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int k = random.nextInt()%200;
            priorityQueue.insert(k);
            pq.add(-k);
        }
        for (int i = 0; i < 200; i++) {
            int k = random.nextInt()%200;
            assertEquals(-pq.poll(), (int)priorityQueue.deleteMax());
            pq.add(-k);
            priorityQueue.insert(k);
        }
    }
}
