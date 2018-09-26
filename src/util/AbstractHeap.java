package util;

import java.util.Arrays;

/**
 * Classe abstraite servant de code de base pour les deux heaps.
 * Définit notament quelques méthodes auxiliaires communes aux deux classes.
 */
abstract class AbstractHeap<K extends Comparable<? super K>> {

    /**
     * The minimum size of the array.
     * It should be strictly greater than 1 !
     */
    protected static final int minCapacity = 32;

    /**
     * Index of the root of the heap - only for clarity.
     */
    protected static final int ROOT = 1;

    /**
     * The size of the heap.
     */
    protected int n;

    /**
     * Contient les différents noeuds. La racine se trouve en position 1, la position 0 n'est pas utilisée.
     */
    protected K[] tab;

    @SuppressWarnings("unchecked")
    AbstractHeap(int initialCapacity) {
        if (initialCapacity < minCapacity)
            initialCapacity = minCapacity;
        this.tab = (K[]) new Comparable[initialCapacity];
        this.n = 0;
    }

    @SuppressWarnings("unchecked")
    void resize(int newCapacity) {
        if (newCapacity >= minCapacity) {
            assert (n < newCapacity && n < tab.length);
            K[] newTab = (K[]) new Comparable[newCapacity];
            System.arraycopy(tab, 0, newTab, 0, this.n+1);
            Arrays.fill(this.tab, null); // loitering
            this.tab = newTab;
        }
    }

    //abstract void swim(int i);

    //abstract void sink(int i);

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return (n == 0);
    }
}
