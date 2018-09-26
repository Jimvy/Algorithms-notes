package hash;

import search.SearchTable;

import java.util.Arrays;
import java.util.LinkedList;

public class LinearProbingHashTable<K, V> implements SearchTable<K, V> {

    private static final double alpha = 0.5; // maximum array fill, N/M
    // Number of keys in our table
    private int N;
    // Number of array places
    private int M;
    // Keys - not necessarily at the correct hash and place ; null if no key
    private K[] keys;
    // Values - null if no value and correspondingly no key
    private V[] values;

    public LinearProbingHashTable() {
        this(997);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int initialCapacity) {
        this.M = initialCapacity;
        this.N = 0;
        this.keys = (K[]) new Object[M];
        this.values = (V[]) new Object[M];
    }
    @Override
    public void put(K key, V value) {
        if (value == null) {
            remove(key);
            return;
        }
        if (((double)N)/M > alpha)
            resize(2*M+1);
        int hashcode = hashCode(key), i;
        for (i = hashcode; keys[i] != null; i++, i %= M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        // empty place, and new key
        keys[i] = key;
        values[i] = value;
        N++;
    }

    @Override
    public V get(K key) {
        int hashCode = hashCode(key);
        // let's assume that there is at least one null value in the keys table
        for (int i = hashCode; keys[i] != null; i++, i %= M) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null; // keys[i] == null
    }

    @Override
    public V remove(K key) {
        if (get(key) == null)
            return null;
        int hashcode = hashCode(key), i;
        for (i = hashcode; !key.equals(keys[i]);)
            i = (i+1)%M;
        keys[i] = null;
        V temp = values[i];
        values[i] = null;
        for (i = (i+1)%M; keys[i] != null; i = (i+1)%M) {
            K todoKey = keys[i];
            V todoVal = values[i];
            keys[i] = null; // loitering
            values[i] = null; // loitering
            N--; // currently, it is absent
            put(todoKey, todoVal); // re-put it ; it will increase N again.
        }
        N--; // this is the effective removal of key.
        if (((double)N)/M < alpha/4)
            resize(M/2);
        return temp;
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
        LinkedList<K> list = new LinkedList<>();
        for (K key : keys)
            if (key != null)
                list.add(key);
        return list;
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
        return ((key.hashCode() & 0x7FFFFFFF) % M);
    }

    @SuppressWarnings("unchecked")
    private void resize(int newM) {
        this.M = newM;
        K[] oldKeys = this.keys;
        V[] oldValues = this.values;
        K[] newKeys = (K[]) new Object[newM];
        V[] newValues = (V[]) new Object[newM];
        this.keys = newKeys;
        this.values = newValues;
        Arrays.fill(newKeys, null);
        Arrays.fill(newValues, null);
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                this.put(oldKeys[i], oldValues[i]);
                N--; // don't forget me
            }
        }
        Arrays.fill(oldKeys, null); // loitering
        Arrays.fill(oldValues, null); // loitering
    }
}
