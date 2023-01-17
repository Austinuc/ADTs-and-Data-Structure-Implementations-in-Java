package BTreeNode;

import java.util.ArrayDeque;

/**
 * @Author: Augustine Igboke
 * @Date: 28-12-2022
 * @email: augustineigboke@gmail.com
 *
 * @Description: A custom node for binary trees. It is a generic node, but most suitable for numbers as other data types
 *                  might have unpredictable results.
 **/
public class B_Node<T> {
    private T val;
    private int height;
    private B_Node<T> leftChild;
    private B_Node<T> rightChild;

    public B_Node() {
        val = null;
        leftChild = null;
        rightChild = null;
        height = 0;
    }

    public B_Node(T val) {
        this.val = val;
        height = 0;
        leftChild = null;
        rightChild = null;
    }

    public B_Node(T val, B_Node<T> leftChild, B_Node<T> rightChild) {
        this.val = val;
        height = 0;
        this.leftChild = leftChild;
        this.rightChild = rightChild;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }


    public B_Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(B_Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public B_Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(B_Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return printInOrder(this, new StringBuilder());
    }
    public String printInOrder(B_Node<T> root, StringBuilder sb) {
        if (root == null) return "";

        printInOrder(root.leftChild,sb);
        sb.append(root.val).append(", ");
        printInOrder(root.rightChild, sb);

        return sb.substring(0, sb.toString().length()-2);
    }

    public String nonRecursiveInOrder(B_Node<T> root, StringBuilder sb) {
        ArrayDeque<B_Node<T>> stack = new ArrayDeque<>();

        B_Node<T> ptr = root;
        while (true) {
            while (ptr != null) {
                stack.addLast(ptr);
                sb.append(ptr.val).append(", ");
                ptr = ptr.leftChild;
            }
            if (stack.isEmpty()) break;

            ptr = stack.pollLast().rightChild;
        }
        return sb.substring(0, sb.toString().length()-2);
    }
}
