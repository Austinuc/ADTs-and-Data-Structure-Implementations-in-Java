package List;

public class ListNode<T> {
    public T val;
    public ListNode<T> next;
    public ListNode<T> prev;

    public ListNode(T val) {
        this.val = val;
        next = null;
    }
    public ListNode() {

    }

    public String toString(ListNode<T> head) {
        StringBuilder vals = new StringBuilder("[");
        ListNode<T> ptr = head;
        while(ptr != null) {
            vals.append((ptr.next != null) ? ptr.val+"-->" : ptr.val);
            ptr = ptr.next;
        }
        vals.append("]");
        return vals.toString();
    }
}
