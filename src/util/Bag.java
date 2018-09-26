package util;

public interface Bag<Item> extends Iterable<Item> {
    void add(Item e);

    int size();

    boolean isEmpty();
}
