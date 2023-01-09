package BTreeNode;

/**
 * @Author: Augustine Igboke
 * @Date: 28-12-2022
 * @email: augustineigboke@gmail.com
 *
 * @Description: A custom node for binary trees
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
}
