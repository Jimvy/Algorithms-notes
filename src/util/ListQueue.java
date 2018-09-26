package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<Item> implements Queue<Item> {
    private class Node {
        Item element;
        Node next;
        public Node(Item elem, Node next) {
            this.element = elem;
            this.next = next;
        }
    }

    private Node head; // front of the queue : dequeue
    private Node tail; // back of the queue : enqueue
    private int n;

    public ListQueue() {
        this.head = null;
        this.tail = null;
        this.n = 0;
    }

    @Override
    public void enqueue(Item e) {
        if (tail == null) {
            // liste complètement vide : on crée le premier élément
            this.head = this.tail = new Node(e, null);
        } else {
            Node newTail = new Node(e, null);
            tail.next = newTail;
            tail = newTail;
        }
        n++;
    }

    @Override
    public Item dequeue() {
        if (head == null) throw new NoSuchElementException();
        Item e = head.element;
        Node old = head;
        head = head.next;
        n--;
        old.element = null; // loitering
        old.next = null; // loitering
        if (head == null)
            tail = null; // loitering, empty queue
        return e;
    }

    @Override
    public Item peek() {
        if (head == null) throw new NoSuchElementException();
        return head.element;
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
        Node current = head;
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item e = current.element;
            current = current.next; // pas de loitering
            return e;
        }
    }
}
