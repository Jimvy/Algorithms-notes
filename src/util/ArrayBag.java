package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBag<Item> implements Bag<Item> {
    // Minimum capacity that the array should have. It will not resize the array to have a smaller size.
    private static final int minCapacity = 20;

    protected Object[] tab;
    protected int N;

    public ArrayBag() {
        this(42);
    }

    public ArrayBag(int initialCapacity) {
        tab = new Object[initialCapacity];
    }

    @Override
    public void add(Item e) {
        if (N >= tab.length)
            resize(2 * tab.length);
        tab[N++] = e;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return (N==0);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * It may look strange that the iterator used iterates over the array in reverse order.
     * This is because it is then used by the ArrayStack class.
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        @Override
        public boolean hasNext() {
            return (i > 0);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) tab[--i];
        }
    }

    protected void resize(int newCapacity) {
        if (newCapacity < minCapacity)
            return; // nothing to do
        Object[] newTab = new Object[newCapacity];
        System.arraycopy(tab, 0, newTab, 0, N);
        tab = newTab;
    }
}
