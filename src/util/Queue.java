package util;

public interface Queue<Item> extends Iterable<Item> {
    void enqueue(Item e);

    Item dequeue();

    Item peek();

    int size();

    boolean isEmpty();
}
