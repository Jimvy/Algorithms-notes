package sort;

public interface Sort<T extends Comparable<? super T>> {
    void doSort(T[] tab);
}
