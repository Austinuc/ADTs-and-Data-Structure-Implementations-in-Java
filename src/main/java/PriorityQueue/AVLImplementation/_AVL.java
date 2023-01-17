package PriorityQueue.AVLImplementation;

import BTreeNode.B_Node;
import PriorityQueue.HeapImplementation.ExtrinsicMinPQ;

public class _AVL<T extends Comparable<T>> implements BST<T> {
    private B_Node<T> root;
    private int size = 0;
    public _AVL() {
        root = new B_Node<>();
    }


    public B_Node<T> getRoot() {
        return root;
    }

    public int getHeight() {
        return root.getHeight();
    }
    @Override
    public void add(T item) {
        root = addNode(root, item);
    }

    @Override
    public boolean contains(T item) {
        return contains(root, item);
    }


    @Override
    public T peakMin() {
        return getMinValue(root);
    }

    @Override
    public T peakMax() {
        return getMaxValue(root);
    }

    @Override
    public T removeMin() {
        T min = getMinValue(root);
        if (min != null)
            root = deleteNode(root, min);
        return min;
    }

    @Override
    public T removeMax() {
        T max = getMaxValue(root);
        if (max != null)
            root = deleteNode(root, max);
        return max;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(T item) {
        if (item != null) {
            root = deleteNode(root, item);
            return item;
        }
        throw new NullPointerException("Value cannot be null");
    }

    private B_Node<T> addNode(B_Node<T> root, T val) {
        if (isEmpty()) {
            size++;
            root.setVal(val);
            return root;
        }
        if (root == null) {
            size++;
            return new B_Node<>(val);
        }

        if (root.getVal().compareTo(val) == 0)
            return root;
        else if (root.getVal().compareTo(val) < 0)
            root.setRightChild(addNode(root.getRightChild(), val));
        else
            root.setLeftChild(addNode(root.getLeftChild(), val));

        //reset traversed node height after insertion
        root = calculateHeight(root);

        root = balanceNode(root);

        return root;
    }

    private B_Node<T> calculateHeight(B_Node<T> root) {
        if (root == null || isEmpty()) return null;
        if (root.getLeftChild() == null && root.getRightChild() == null)
            root.setHeight(0);
        else {
            int lh = root.getLeftChild() != null ? root.getLeftChild().getHeight() : 0;
            int rh = root.getRightChild() != null ? root.getRightChild().getHeight() : 0;

            root.setHeight((Math.max(lh,rh) + 1));
        }
        return root;
    }

    /**
     * Performs single (RR, LR) or double (LR-RR, RR-LR) rotation to balance any unbalanced node by calling
     *  the rotation methods accordingly on the given node
     * @param root
     * @return the balanced node
     */
    private B_Node<T> balanceNode(B_Node<T> root) {
        if (root == null || isEmpty()) return null;
        int lh = root.getLeftChild() != null ? root.getLeftChild().getHeight() + 1 : 0;
        int rh = root.getRightChild() != null ? root.getRightChild().getHeight() + 1 : 0;
        if (rh - lh > 1) { //right-heavy ? rotate left.
            int rrh = root.getRightChild().getRightChild() != null ? root.getRightChild().getRightChild().getHeight() : 0;
            int rlh = root.getRightChild().getLeftChild() != null ? root.getRightChild().getLeftChild().getHeight() : 0;
            //Check if subtree is left skewed
            if (rlh > rrh) { // is right child left-heavy ? perform rotateRight on the right child
                root.setRightChild(rotateRight(root.getRightChild()));
            }
            root = rotateLeft(root);
        } else if (lh - rh > 1) { //left-heavy ? rotate right
            int rrh = root.getLeftChild().getRightChild() != null ? root.getLeftChild().getRightChild().getHeight() + 1: 0;
            int rlh = root.getLeftChild().getLeftChild() != null ? root.getLeftChild().getLeftChild().getHeight() + 1: 0;
            //Check if subtree is right skewed
            if (rlh < rrh) { // is left child right-heavy ? perform rotateLeft on the left child
                root.setLeftChild(rotateLeft(root.getLeftChild()));
            }
            root = rotateRight(root);
        }

        return root;
    }

    private boolean contains(B_Node<T> root, T value) {
        if (root == null || isEmpty()) return false;

        if (root.getVal().equals(value)) return true;

        if (root.getVal().compareTo(value) < 0)
            return contains(root.getRightChild(), value);
        else
            return contains(root.getLeftChild(), value);
    }

    private B_Node<T> rotateLeft(B_Node<T> node) {
        if (node == null || isEmpty()) return null;

        B_Node<T> rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());
        rightChild.setLeftChild(node);

        // recalculate the heights of the nodes swapped
        calculateHeight(node);
        calculateHeight(rightChild);

        return rightChild;
    }

    private B_Node<T> rotateRight(B_Node<T> node) {
        if (node == null || isEmpty()) return null;

        B_Node<T> leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());
        leftChild.setRightChild(node);

        // recalculate the heights of the nodes swapped
        calculateHeight(node);
        calculateHeight(leftChild);
        return leftChild;
    }

    /**
     * There are 3 cases
     * <ol>
     *     <li>If node to delete is a leaf node, then return null in place of that node</li>
     *     <li>If node to delete has only one child, either left/right child, then simply return the child</li>
     *     <li>If node has both children, then get the smallest value from the right child
     *          (which will be the leftmost child of its right subtree) and replace its value
     *          with the smallest value. then delete the smallest value node from the right subtree.
     *     </li>
     * </ol>
     *
     * NOTE: This strategy if mainly for BSTs. For general trees or Heaps, you simply replace with the deepest node
     *          value and then remove the deepest node from the tree.
     *
     * @param root tree
     * @param value to be deleted
     * @return root node
     */
    private B_Node<T> deleteNode(B_Node<T> root, T value) {
        if (root == null || isEmpty()) return null;

        if (root.getVal().compareTo(value) > 0)
            root.setLeftChild(deleteNode(root.getLeftChild(),value));
        else if (root.getVal().compareTo(value) < 0)
            root.setRightChild(deleteNode(root.getRightChild(), value));
        else {
            //Handling the three different cases
            size--;
            if (root.getLeftChild() == null)
                return root.getRightChild();
            else if (root.getRightChild() == null)
                return root.getLeftChild();
            else {
                root.setVal(getMinValue(root.getRightChild()));

                root.setRightChild(deleteNode(root.getRightChild(), root.getVal()));
            }
        }
        //reset traversed node height after deletion
        root = calculateHeight(root);
        root = balanceNode(root);
        return root;
    }

    private T getMinValue(B_Node<T> node) {
        if (node == null || isEmpty()) return null;
        if (node.getLeftChild() == null) return node.getVal();
        return getMinValue(node.getLeftChild());
    }

    private T getMaxValue(B_Node<T> node) {
        if (node == null || isEmpty()) return null;
        if (node.getRightChild() == null) return node.getVal();
        return getMinValue(node.getRightChild());
    }

    public static void main(String[] args) {
        _AVL<Integer> avl = new _AVL();
        System.out.println("Size: " + avl.size());
        avl.add(13);
        avl.add(8);
        avl.add(15);
        avl.add(6);
        avl.add(9);
        avl.add(9);
        avl.add(14);
        avl.add(19);
        avl.add(16);
        avl.add(17);
        avl.add(23);
        avl.add(18);
        avl.add(20);

        System.out.println();
        System.out.println("Size: " + avl.size());
        avl.remove(23);
        avl.remove(18);
        avl.remove(20);
        System.out.println(avl.getRoot());
        System.out.println("Height: " + avl.getHeight());
        System.out.println("Size: " + avl.size());
    }

}
