package util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {
    private class Node {
        T element;
        Node next;
        Node previous;
        public Node(T element, Node next, Node previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node head;
    private Node tail;
    private int N;

    public DoubleLinkedList() {
        N = 0;
        head = tail = null;
    }
    public void addFirst(T elem) {
        Node oldHead = head;
        head = new Node(elem, oldHead, null);
        if (oldHead == null)
            head = tail;
        else
            oldHead.previous = head;
        N++;
    }
    public void addLast(T elem) {
        Node oldTail = tail;
        tail = new Node(elem, null, oldTail);
        if (oldTail == null) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        N++;
    }
    public T removeFirst() throws NoSuchElementException {
        if (N <= 0) throw new NoSuchElementException();
        // N >= 1 : soit head==tail et on vide donc la liste, soit il y a au moins deux éléments
        Node oldHead = head;
        head = oldHead.next;
        if (head == null) { // c'était le dernier élément
            tail = null;
        } else {
            head.previous = null;
        }
        oldHead.next = null;
        return oldHead.element;
    }
    public T removeLast() throws NoSuchElementException {
        if (N <= 0) throw new NoSuchElementException();
        // N >= 1 : tail != null
        Node oldTail = tail;
        tail = oldTail.previous;
        if (tail == null) { // c'était le dernier élément
            head = null;
        } else {
            tail.next = null;
        }
        return oldTail.element;
    }
    public T removeAt(DoubleLinkedListIterator iterator) {
        return removeNodeAt(iterator).element;
    }
    private Node removeNodeAt(DoubleLinkedListIterator iterator) {
        Node old = iterator.current;
        if (old == null && N == 0) // empty list
            throw new NoSuchElementException();
        else if (old == null) // non-empty list
            throw new IllegalArgumentException("Malformed list");
        if (old.next == null) { // old was at the end
            tail = old.previous;
        } else {
            old.next.previous = old.previous; // may be null ; was catched after
        }
        if (old.previous == null) { // old was at the beginning
            head = old.next;
        } else {
            old.previous.next = old.next; // may be null ; was catched before
        }
        old.next = old.previous = null;
        this.N--;
        return old;
    }
    public void insertAfter(DoubleLinkedListIterator iterator, T elem) {
        Node current = iterator.current;
        Node newNode = new Node(elem, null, null);
        insertNodeAfter(current, newNode);
    }
    private void insertNodeAfter(Node current, Node newNode) {
        // TODO
        if (current == null && this.N == 0) { // the list was empty
            head = tail = newNode;
            newNode.next = newNode.previous = null;
        } else if (current == null) {// non empty list, but the iterator says it is empty ; probably too old
            throw new IllegalArgumentException("Malformed iterator");
        } else {
            newNode.next = current.next;
            newNode.previous = current;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            } else {
                newNode.next.previous = newNode;
            }
        }
        this.N++;
    }
    public void insertBefore(DoubleLinkedListIterator iterator, T elem) {
        Node current = iterator.current;
        Node newNode = new Node(elem, null, null);
        insertNodeBefore(current, newNode);
    }
    private void insertNodeBefore(Node current, Node newNode) {
        if (current == null && this.N == 0) { // the list was empty
            head = tail = newNode;
            newNode.next = newNode.previous = null;
        } else if (current == null) {// non empty list, but the iterator says it is empty ; probably too old
            throw new IllegalArgumentException("Malformed iterator");
        } else {
            newNode.previous = current.previous;
            newNode.next = current;
            current.previous = newNode;
            if (newNode.previous == null) {
                head = newNode;
            } else {
                newNode.previous.next = newNode;
            }
        }
        this.N++;
    }
    public void mergeLists(DoubleLinkedList<T> that, Comparator<T> comparator) {
        Node curThis = this.head;
        Node curThat = that.head;
        while (curThis != null && curThat != null) {
            if (comparator.compare(curThis.element, curThat.element) <= 0) {
                curThis = curThis.next; // Il suffit juste d'avancer ce pointeur
            } else {
                /*
                    curThis     curThat
                       v           v
                 A1 -> A2 -> A3    B1 -> B2 -> B3
                    <-    <-          <-    <-
                devient
                          curThis     curThat
                             v           v
                 A1 -> B1 -> A2 -> A3    B2 -> B3
                    <-    <-    <-          <-
                 curThis reste identique à ce qu'il était avant
                 */
                if (curThis.previous == null) {
                    this.head = curThat;
                } else {
                    curThis.previous.next = curThat;
                }
                // curThat.previous = null normalement
                curThat.previous = curThis.previous;
                Node newCurThat = curThat.next;
                curThat.next = curThis;
                curThis.previous = curThat;
                curThat = newCurThat;
                this.N++;
            }
        }
        if (curThat != null) {
            curThat.previous = this.tail;
            if (curThat.previous == null) {
                this.head = curThat;
            } else {
                curThat.previous.next = curThat;
            }
            this.tail = that.tail;
            //curThat = curThat.next;
            //this.tail.next = null;
        }
    }
    public int size() {
        return N;
    }
    public boolean isEmpty() {
        return N==0;
    }
    public DoubleLinkedListIterator iterator() {
        return new ForwardLinkedListIterator();
    }
    public abstract class DoubleLinkedListIterator implements Iterator<T> {
        Node current = head; // package-private
    }

    private class ForwardLinkedListIterator extends DoubleLinkedListIterator {
        //private Node current = head;
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T e = current.element;
            current = current.next;
            return e;
        }
    }
}
