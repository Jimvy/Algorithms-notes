package hash;

import search.SearchTable;
import search.SequentialSearchTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SeparateChainingHashTable<K, V> implements SearchTable<K, V>, Iterable<K> {
    // Number of items in the map
    private int N;
    // Size of the hash table
    private int M;

    private SequentialSearchTable<K, V>[] sts;

    private static final int DEFAULT_HASHTABLE_SIZE = 997;

    public SeparateChainingHashTable() {
        this(DEFAULT_HASHTABLE_SIZE);
    }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int hashTableSize) {
        this.M = hashTableSize;
        this.N = 0;
        this.sts = new SequentialSearchTable[M];
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            remove(key);
            return;
        }
        int hashcode = hashCode(key);
        if (sts[hashcode] == null)
            sts[hashcode] = new SequentialSearchTable<>();
        if (sts[hashcode].get(key) == null)
            N++; // don't forget me ; only in the case that the key didn't exist before.
        sts[hashcode].put(key, value);
    }

    @Override
    public V get(K key) {
        int hashcode = hashCode(key);
        if (sts[hashcode] != null)
            return sts[hashcode].get(key);
        else
            return null;
    }

    @Override
    public V remove(K key) {
        int hashcode = hashCode(key);
        if (sts[hashcode] != null) {
            V ret = sts[hashcode].remove(key);
            if (ret == null) {
                return null;
            } else {
                N--;
                return ret;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(K key, V value) {
        int hashcode = hashCode(key);
        return (sts[hashcode] != null && sts[hashcode].delete(key, value));
    }

    @Override
    public boolean contains(K key) {
        return (get(key) != null);
    }

    @Override
    public Iterable<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        for (SequentialSearchTable<K, V> sst : sts)
            for (K key : sst)
                list.add(key);
        return list;
        //return this;
    }

    // not so sure about this code
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int i = 0; // next iterator to look at
            private Iterator<K> it;

            @Override
            public boolean hasNext() {
                return (i < M || it.hasNext());
            }

            @Override
            public K next() {
                while (i < N && it == null || !it.hasNext()) {
                    if (sts[i] == null)
                        i++;
                    else
                        it = sts[i++].iterator();
                }
                if (i >= N) throw new NoSuchElementException();
                return it.next();
            }
        };
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return (N == 0);
    }

    private int hashCode(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }
}
