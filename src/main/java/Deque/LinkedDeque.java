package Deque;

import List.ListNode;

public class LinkedDeque<T> {
    public int size = 0;
    ListNode<T> front = new ListNode<>();
    ListNode<T> back = new ListNode<>();

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {

        ListNode<T> ptr = front.next;
        for (int i = 0; i < size && i <= index; i++) {
            if (i == index) return ptr.val;
            ptr = ptr.next;
        }
        return null;
    }

    public T removeFirst() {
        if (size == 0) return null;

        T val = front.next.val;
        front.next = front.next.next;
        front.next.prev = front;
        size--;

        return val;
    }

    public T removeLast() {
        if (size == 0) return null;

        T val = back.prev.val;

        back.prev = back.prev.prev;
        back.prev.next = back;
        size--;

        return val;
    }

    public void addFirst(T val) {
        ListNode<T> newNode = new ListNode<>(val);
        if (size == 0) {
            initializeDeque(newNode);
        } else {
            front.next.prev = newNode;
            newNode.next = front.next;
            front.next = newNode;
            newNode.prev = front;
        }
        size++;
    }

    public void addLast(T val) {
        ListNode<T> newNode = new ListNode<>(val);
        if (size == 0) {
            initializeDeque(newNode);
        } else {
            back.prev.next = newNode;
            newNode.prev = back.prev;
            back.prev = newNode;
            newNode.next = back;
        }
        size++;
    }

    public void initializeDeque(ListNode<T> newNode) {
        front.next = newNode;
        front.next.prev = front;
        front.next.next = back;
        back.prev = front.next;
    }

    public static void main(String[] args) {
        LinkedDeque<String> stringDeque = new LinkedDeque<>();

        stringDeque.addFirst("a");
        stringDeque.addFirst("b");
        stringDeque.addLast("c");
        stringDeque.addLast("d");
        stringDeque.addFirst("e");

        stringDeque.removeFirst();
        stringDeque.removeLast();

        String a = stringDeque.get(0);
    }
}
