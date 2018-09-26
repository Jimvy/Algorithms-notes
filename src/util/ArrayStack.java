package util;

import java.util.EmptyStackException;

/**
 * See class ArrayBag for the definition of
 * - method add
 * - methods size() and isEmpty()
 * - method iterator() and the ReverseArrayIterator class
 *
 * @param <Item> type of the data stored in this stack
 */
public class ArrayStack<Item> extends ArrayBag<Item> implements Stack<Item> {
    public ArrayStack() {
        this(42);
    }

    public ArrayStack(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void push(Item e) {
        add(e);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item peek() throws EmptyStackException {
        if (N <= 0) throw new EmptyStackException();
        return (Item) tab[N-1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item pop() throws EmptyStackException {
        if (N <= 0) throw new EmptyStackException();
        Item e = (Item) tab[--N];
        tab[N] = null; // loitering
        if (N > 0 && N <= tab.length/4)
            resize(tab.length/2);
        return e;
    }
}
