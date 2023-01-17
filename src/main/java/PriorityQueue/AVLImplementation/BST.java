package PriorityQueue.AVLImplementation;

import BTreeNode.B_Node;

public interface BST<T> {

    void add(T item);
    boolean contains(T item);
    T peakMin();
    T peakMax();
    T removeMin();
    T removeMax();
    int size();
    boolean isEmpty();
}
