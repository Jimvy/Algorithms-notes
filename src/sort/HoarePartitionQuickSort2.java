package sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HoarePartitionQuickSort2<T extends Comparable<? super T>> implements Sort<T> {
    @Override
    public void doSort(T[] tab) {
        HoarePartitionQuickSort2.sort(tab);
    }

    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        HoarePartitionQuickSort2.sort(tab, 0, tab.length);
    }
    private static <T extends Comparable<? super T>> void sort(T[] tab, int start, int end) {
        while (end - start > 1) {
            // at least two elements
            int pivotIndex = start + (int) (Math.random() * (end - start));
            int mid = partition(tab, start, end, pivotIndex);
            // The item at position mid is already at the correct place
            if (mid - start < end - mid) {
            /* The first subarray is the smallest of the two : recurse on him to reduce, on average, call stack size
             * so that it is bound by O(log_2(n)) strictly. */
                sort(tab, start, mid);
                // Tail-call optimisation : we stay in the same function frame
                start = mid + 1;
            } else {
                // the second sub array is the smallest
                sort(tab, mid + 1, end);
                end = mid;
            }
        }
        // 1 element or less, already sorted
    }
    private static <T extends Comparable<? super T>> int partition(T[] tab, int start, int end, int pivotIndex) {
        T pivot = tab[pivotIndex];
        tab[pivotIndex] = tab[start];
        int i = start + 1, j = end - 1;
        while (true) {
            /* In comparison with the other partition algorithm, here we don't have to stop on elements equal to pivot,
             * because we will only advance two elements at a time, so the array will still be split in half.
             */
            while (i < end && j >= start && pivot.compareTo(tab[i]) >= 0 && pivot.compareTo(tab[j]) <= 0) {
                i++;
                j--;
            }
            while (i < end && pivot.compareTo(tab[i]) >= 0)
                i++;
            while (j >= start && pivot.compareTo(tab[j]) <= 0)
                j++;
            if (i >= j) break;
            T tmp = tab[i];
            tab[i] = tab[j];
            tab[j] = tmp;
        }
        tab[start] = tab[j];
        tab[j] = pivot;
        return j;
    }
    @Test
    public void testPartition1() {
        // (13, 15), (0, 5), (2, 0)
        Character[] tab = new Character[] {'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S'};
        assertEquals(15, HoarePartitionQuickSort2.partition(tab, 0, tab.length, 13));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition2() {
        Integer[] tab = new Integer[] {2, 3, 1, 4};
        assertEquals(1, HoarePartitionQuickSort2.partition(tab, 0, tab.length, 0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition3() {
        Integer[] tab = new Integer[] {4, 3, 2, 1};
        assertEquals(3, HoarePartitionQuickSort2.partition(tab, 0, tab.length, 0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition4() {
        Integer[] tab = new Integer[] {2, 3, 1, 4};
        assertEquals(2, HoarePartitionQuickSort2.partition(tab, 0, tab.length, 1));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition5() {
        Integer[] tab = new Integer[] {3, 2, 1};
        assertEquals(2, HoarePartitionQuickSort2.partition(tab, 0, tab.length,0));
        System.out.println(Arrays.toString(tab));
    }
    @Test
    public void testPartition6() {
        Integer[] tab = new Integer[] {1, 2};
        assertEquals(0, HoarePartitionQuickSort2.partition(tab, 0, tab.length,0));
        System.out.println(Arrays.toString(tab));
    }
}
