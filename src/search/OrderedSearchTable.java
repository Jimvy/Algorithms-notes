package search;

import java.util.Comparator;
import java.util.Map;

public interface OrderedSearchTable<K extends Comparable<? super K>, V> extends SearchTable<K, V> {
    K firstKey();

    K lastKey();

    Map.Entry<K, V> firstEntry();

    Map.Entry<K, V> lastEntry();

    /**
     * Largest key less than or equal to key.
     *
     * @param key the specified key
     * @return the largest key that is less than or equal to key
     */
    K floorKey(K key);

    /**
     * Smallest key greater than or equals to key.
     *
     * @param key the specified key
     * @return the smallest key that is larger than or equal to key
     */
    K ceilingKey(K key);

    /**
     * Largest key strictly greater than this key.
     *
     * @param key the specified key
     * @return the largest key that is less than or equal to key
     */
    K lowerKey(K key);

    /**
     * Smallest key strictly greater than key.
     *
     * @param key the specified key
     * @return the smallest key that is larger than this key
     */
    K higherKey(K key);

    /**
     * The number of keys strictly less than key. 0 if smallest key, or if the key is smaller than the least key.
     *
     * @param key the specified key
     * @return the number of keys less than key
     */
    int rank(K key);

    /**
     * Reverse of the previous method.
     *
     * @param rank rank of the queried key
     * @return smallest key with rank keys less than it
     */
    K select(int rank);

    void deleteMin();
    void deleteMax();

    /**
     * Number of keys in [lo..hi], inclusive.
     *
     * @param low first key of the interval
     * @param high last key of the interval
     * @return number of keys that are greater than or equal to low, and less than or equal to high
     */
    int size(K low, K high);

    /**
     * Keys in [lo..hi], in sorted order.
     *
     * @param low first key of the interval
     * @param high last key of the interval
     * @return all the keys that are between low and high, inclusive
     */
    Iterable<K> keys(K low, K high);

    /**
     * Keys whose rank is between (inclusive) lowRank and highRank.
     * Note : the size of this set is of course {@code highRank - lowRank + 1}.
     *
     * @param lowRank rank of the first key of the interval
     * @param highRank rank of the last key of the interval
     * @return all the keys whose rank is between these two ranks.
     */
    Iterable<K> keys(int lowRank, int highRank);
}
