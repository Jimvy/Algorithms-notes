package search;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SequentialSearchTable<K, V> implements SearchTable<K, V>, Iterable<K> {
    private class Node {
        Node next;
        K key;
        V value;
        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node front;
    private int n;

    public SequentialSearchTable() {
        this.front = null;
    }

    @Override
    public void put(K key, V value) {
        if (value != null) {
            for (Node runner = front; runner != null; runner = runner.next) {
                if (runner.key.equals(key)) {
                    runner.value = value;
                    return;
                }
            }
            front = new Node(key, value, front);
            n++;
        } else {
            remove(key);
        }
    }

    @Override
    public V get(K key) {
        if (front == null)
            return null;
        for (Node runner = front; runner != null; runner = runner.next) {
            if (runner.key.equals(key))
                return runner.value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (this.front == null)
            return null; // nothing to remove
        if (front.key.equals(key)) {
            V old = front.value;
            front.key = null;
            front.next = null;
            front.value = null;
            front = null;
            n--;
            return old;
        }
        for (Node runner = front; runner.next != null; runner = runner.next) {
            if (runner.next.key.equals(key)) {
                Node old = runner.next;
                runner.next = runner.next.next;
                old.next = null;
                old.key = null;
                n--;
                V oldVal = old.value;
                old.value = null;
                return oldVal;
            }
        }
        return null;
    }

    @Override
    public boolean delete(K key, V value) {
        V ret = remove(key);
        return (ret != null && value != null && ret.equals(value));
    }

    @Override
    public boolean contains(K key) {
        return (get(key) != null);
    }

    @Override
    public Iterable<K> keys() {
        return this;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node current = front;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public K next() {
                K key = current.key;
                current = current.next;
                return key;
            }
        };
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n == 0);
    }
}
