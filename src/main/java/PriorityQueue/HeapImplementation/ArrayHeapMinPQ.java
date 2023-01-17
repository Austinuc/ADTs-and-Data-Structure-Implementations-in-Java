package PriorityQueue.HeapImplementation;

import java.util.ArrayList;
import java.util.List;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    int size;
    List<T> arrayHeap = null;

    public ArrayHeapMinPQ() {
        arrayHeap = new ArrayList<>();
    }


    @Override
    public void add(T item, double priority) {

    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public T peakMin() {
        return null;
    }

    @Override
    public T removeMin() {
        return null;
    }

    @Override
    public void changePriority(T item, double priority) {

    }

    @Override
    public int size() {
        return arrayHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
