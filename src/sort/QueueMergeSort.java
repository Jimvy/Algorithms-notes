package sort;

import org.junit.Test;
import util.ListQueue;
import util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Exercice 2.2.15 du livre de référence : Bottom-Up Queue Mergesort.
 * Le principe est de créer, pour chaque élément du tableau, une file, de mettre toutes les files dans une file,
 * puis de prendre les deux premières files, les fusionner, et les remettre dans la grande file.
 * On continue ainsi jusqu'à ne plus avoir qu'une seule file dans la grande file, le tableau trié.
 * Étonnament, l'algorithme garde sa complexité : chaque élément participera à log n fusions de queues, et fusionner toutes les queues durant une passe est en n, donc O(n log n).
 *
 * @param <T> Le type de variable que l'on veut trier.
 */
public class QueueMergeSort<T extends Comparable<? super T>> implements Sort<T> {
    @Override
    public void doSort(T[] tab) {
        QueueMergeSort.sort(tab);
    }

    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        Queue<Queue<T>> qs = new ListQueue<>();
        for (T aTab : tab) {
            Queue<T> temp = new ListQueue<>();
            temp.enqueue(aTab);
            qs.enqueue(temp);
        }
        while (qs.size() > 1) {
            Queue<T> q1 = qs.dequeue(), q2 = qs.dequeue();
            mergeQueues(q1, q2);
            qs.enqueue(q1);
        }
        for (int i = 0; i < tab.length; i++)
            tab[i] = qs.peek().dequeue();
    }
    private static <T extends Comparable<? super T>> void mergeQueues(Queue<T> q1, Queue<T> q2) {
        int N = q1.size(), count = 0;
        while (!q2.isEmpty() && count < N) {
            if (q1.peek().compareTo(q2.peek()) <= 0) {
                q1.enqueue(q1.dequeue());
                count++;
            } else {
                q1.enqueue(q2.dequeue());
            }
        }
        for (int i = count; i < N; i++)
            q1.enqueue(q1.dequeue());
        while (!q2.isEmpty())
            q1.enqueue(q2.dequeue());
    }
    @Test
    public void test1() {
        Queue<Integer> q1 = new ListQueue<>(), q2 = new ListQueue<>();
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(5);
        q2.enqueue(0);
        q2.enqueue(3);
        q2.enqueue(4);
        mergeQueues(q1, q2);
        assertEquals(0, q2.size());
        assertEquals(6, q1.size());
        for (int i=0; i < 6; i++)
            assertEquals(i, (int)q1.dequeue());
    }
}
