package List;

public class LinkedIntList<T> {

    public ListNode<T> reverse(ListNode<T> head) {

        if (head == null) {
            return null;
        }
        ListNode<T> ptr = null;
        while (head != null) {
            ListNode<T> tmp = head.next;
            head.next = ptr;
            ptr = head;
            head = tmp;
        }
        return ptr;

    }

    public ListNode<T> firstToLast(ListNode<T> head) {
        if (head == null) return null;

        ListNode<T> ptr = head;

        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = head;
        ptr = head;
        head = head.next;
        ptr.next = null;

        return head;
    }

    public ListNode<T> concatenate(ListNode<T> l1, ListNode<T> l2) {
        if (l1 == null) return l2;

        ListNode<T> head = l1;

        while (l1.next != null) l1 = l1.next;

        l1.next = l2;
        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        head.next.next.next = new ListNode<>(4);

        System.out.println(new ListNode<Integer>().toString(head));

        LinkedIntList<Integer> linkedIntList = new LinkedIntList<>();
        head = linkedIntList.reverse(head);

        System.out.println(new ListNode<Integer>().toString(head));
        System.out.println(new ListNode<Integer>().toString(linkedIntList.firstToLast(head)));
    }
}
