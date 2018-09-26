package search;

import java.util.Map;
import java.util.Vector;

/**
 * TODO
 * @param <K> Type of the keys
 * @param <V> Type of the elements/values stored at these keys
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements OrderedSearchTable<K, V> {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int N;
        public Node(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null)
            return 0;
        else
            return n.N;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (n == null) // noeud absent
            return null;
        int cmp = n.key.compareTo(key);
        if (cmp < 0) {
            // dans l'arbre de droite
            return get(n.right, key);
        } else if (cmp > 0) {
            // dans l'arbre de gauche
            return get(n.left, key);
        } else {
            return n.value;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if (n == null && value != null) {
            return new Node(key, value, 1);
        } else if (n == null) {
            return null;
        }
        int cmp = n.key.compareTo(key);
        if (cmp < 0) {
            n.right = put(n.right, key, value);
        } else if (cmp > 0) {
            n.left = put(n.left, key, value);
        } else {
            if (value == null) { // delete the element
                n.value = null;
                if (n.right != null && n.left != null) {
                    Node x = removeSmallest(n.right);
                }
                n.left = null;
                n.right = null;
                return null;
            } else {
                n.value = value;
            }
        }
        n.N = size(n.left) + size(n.right) + 1;
        return n;
    }
    private Node removeSmallest(Node n) {
        if (n == null)
            return null;
        Node current = n;
        if (current.left == null) {
            return current;
        }
        while (current.left.left != null) {
            current = current.left;
        } // current.left.left == null
        Node tmp = current.left;
        current.left = null;
        return tmp;
    }

    @Override
    public V remove(K key) {
        V oldValue = get(key);
        put(key, null);
        return oldValue;
    }

    @Override
    public boolean delete(K key, V value) {
        V oldValue = get(key);
        put(key, null);
        return (oldValue != null && oldValue.equals(value));
    }

    @Override
    public Iterable<K> keys() {
        Vector<K> it = new Vector<>();
        infix(root, it);
        return it;
    }

    private void infix(Node n, Vector<K> it) {
        if (n == null)
            return;
        infix(n.left, it);
        it.add(n.key);
        infix(n.right, it);
    }

    @Override
    public K firstKey() {
        return null;
    }

    @Override
    public Map.Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public K lastKey() {
        return null;
    }

    @Override
    public Map.Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public K floorKey(K key) {
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        return null;
    }

    @Override
    public K lowerKey(K key) {
        return null;
    }

    @Override
    public K higherKey(K key) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public K select(int rank) {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K low, K high) {
        return null;
    }

    @Override
    public Iterable<K> keys(int lowRank, int highRank) {
        return null;
    }

    @Override
    public int size(K low, K high) {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return (get(key) != null);
    }
}
