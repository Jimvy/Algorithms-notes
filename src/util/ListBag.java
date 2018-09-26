package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListBag<Item> implements Bag<Item> {
    protected class Node {
        Node next;
        Item element;
        Node(Item element, Node next) {
            this.next = next;
            this.element = element;
        }
    }

    protected Node top;
    protected int n;

    public ListBag() {
        this.top = null;
    }
    @Override
    public void add(Item e) {
        top = new Node(e, top);
        n++;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n == 0);
    }

    @Override
    public Iterator<Item> iterator() {
        return new NodeListIterator();
    }

    private class NodeListIterator implements Iterator<Item> {
        Node current = top;
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item e = current.element;
            current = current.next;
            return e;
        }
    }
}
