package PriorityQueue.HeapImplementation;

public interface ExtrinsicMinPQ<T> {
    void add(T item, double priority);
    boolean contains(T item);
    T peakMin();
    T removeMin();
    void changePriority(T item, double priority);

    int size();

    boolean isEmpty();
}
