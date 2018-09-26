package util;

public class MedianHeap<K extends Comparable<? super K>> {
    // Store all elements greater than or equal to the median in minpq, so that it returns the median
    private MinPriorityQueue<K> minpq;
    // Store all elements less than the median in maxpq, so that it returns the item before the median.
    private MaxPriorityQueue<K> maxpq;

    public MedianHeap() {
        this(32);
    }

    public MedianHeap(int initialCapacity) {
        this.minpq = new MinHeap<>((initialCapacity + 1) / 2);
        this.maxpq = new MaxHeap<>(initialCapacity / 2);
    }

    public void insert(K key) {
        if (minpq.size() == maxpq.size()) {
            if (minpq.size() == 0) {
                minpq.insert(key);
            } else if (maxpq.max().compareTo(key) > 0) {
                minpq.insert(maxpq.deleteMax());
                maxpq.insert(key);
            } else {
                minpq.insert(key);
            }
        } else {
            // minpq.size() == maxpq.size() + 1
            if (minpq.min().compareTo(key) < 0) {
                maxpq.insert(minpq.deleteMin());
                minpq.insert(key);
            } else {
                maxpq.insert(key);
            }
        }
    }

    /**
     * Retourne la médiane de l'ensemble des valeurs, ou la valeur juste après celle-ci. Complexité Theta(log(n)).
     *
     * @return la médiane des valeurs stockées (si le nombre d'éléments est impair),
     *         ou la valeur juste après celle-ci (si le nombre d'éléments est pair).
     */
    public K median() {
        return minpq.min();
    }

    public K deleteMedian() {
        if (minpq.size() == maxpq.size()) {
            K temp = minpq.deleteMin();
            minpq.insert(maxpq.deleteMax());
            return temp;
        } else {
            return minpq.deleteMin();
        }
    }

    public int size() {
        return minpq.size() + maxpq.size();
    }
}
