package util;

import java.util.EmptyStackException;

/**
 * See class ListBag for the definition of
 * - class Node
 * - methods size and isEmpty
 * - method add
 * - iterator() and the NodeListIterator class
 *
 * @param <Item> type of the data stored in this stack.
 */
public class ListStack<Item> extends ListBag<Item> implements Stack<Item> {
    public ListStack() {
        super();
    }

    @Override
    public void push(Item e) {
        add(e);
    }

    @Override
    public Item peek() {
        if (top == null) throw new EmptyStackException();
        return top.element;
    }

    @Override
    public Item pop() {
        if (top == null) throw new EmptyStackException();
        Item e = top.element;
        Node old = top;
        top = top.next;
        old.next = null; // loitering
        old.element = null; // loitering
        n--;
        return e;
    }
}
