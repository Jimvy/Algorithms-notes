package util;

public interface Stack<Item> extends Iterable<Item> {
    void push(Item e);

    Item pop();

    Item peek();

    boolean isEmpty();

    int size();
}
