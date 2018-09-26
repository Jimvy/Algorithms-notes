package sort;

/**
 * Algorithme :
 * - Réarranger le tableau tab de sorte qu'il s'agisse d'une max-heap.
 * - Jusqu'à ce que celle-ci soit vide, on retire le maximum de la heap, que l'on déplace à la fin du tableau.
 * Propriétés :
 * - Complexité O(n log(n)) dans tous les cas, sans amortissement. Il y a par contre une faible localité du cache.
 * - L'algorithme n'est <b>pas</b> stable.
 * - L'algorithme est en-place.
 *
 * @param <T> le type de données que l'on doit trier.
 */
public class HeapSort<T extends Comparable<? super T>> implements Sort<T> {
    private static final int ROOT = 0;
    // default constructor
    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     */
    @Override
    public void doSort(T[] tab) {
        HeapSort.sort(tab);
    }

    /**
     * Voir description de la classe pour l'algorithme. Après exécution de l'appel, le tableau {@code tab} est trié.
     *
     * @param tab le tableau à trier.
     * @param <T> Le type de données contenues dans le tableau. Le type de donnée doit implémenter {@code Comparable<T>}.
     */
    public static <T extends Comparable<? super T>> void sort(T[] tab) {
        for (int i = tab.length/2; i >= 0; i--)
            sink(tab, tab.length, i);
        for (int i = tab.length-1; i > 0; i--) {
            T temp = tab[ROOT];
            tab[ROOT] = tab[i];
            tab[i] = temp;
            sink(tab, i, ROOT);
        }
    }

    /**
     * Restores heap property from index i down in the array and the heap.
     * In contrast with the standard heap, here ROOT=0.
     * @param tab the array in which the heap is stored
     * @param n the number of elements in the heap
     * @param i index from which we have to repair the heap
     * @param <T> type of data stored in the heap
     */
    private static <T extends Comparable<? super T>> void sink(T[] tab, int n, int i) {
        T temp = tab[i];
        int j;
        while ((j=((i << 1) + 1)) < n) {
            if (j < n-1 && tab[j].compareTo(tab[j+1]) < 0)
                j++;
            if (temp.compareTo(tab[j]) < 0) {
                tab[i] = tab[j];
                i = j;
            } else {
                break;
            }
        }
        tab[i] = temp;
    }
}
