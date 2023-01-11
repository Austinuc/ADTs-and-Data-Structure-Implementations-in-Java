package PriorityQueue.AVLImplementation;

import BTreeNode.B_Node;

public class _AVL {

    public <T extends Comparable<T>> B_Node<T> addNode(B_Node<T> root, T val) {
        if (root == null) return new B_Node<>(val);

        if (root.getVal().compareTo(val) < 0)
            root.setRightChild(addNode(root.getRightChild(), val));
        else
            root.setLeftChild(addNode(root.getLeftChild(), val));

        //reset traversed node height after insertion
        root = calculateHeight(root);

        root = balanceNode(root);

        return root;
    }

    private <T extends Comparable<T>> B_Node<T> calculateHeight(B_Node<T> root) {
        if (root == null) return null;
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
     *  the rotation mothods accordingly on the given node
     * @param root
     * @return the balanced node
     * @param <T>
     */
    private <T extends Comparable<T>> B_Node<T> balanceNode(B_Node<T> root) {
        if (root == null) return null;
        int lh = root.getLeftChild() != null ? root.getLeftChild().getHeight() + 1 : 0;
        int rh = root.getRightChild() != null ? root.getRightChild().getHeight() + 1 : 0;
        if (rh - lh > 1) { //right-heavy ? rotate left.
            int rrh = root.getRightChild().getRightChild() != null ? root.getRightChild().getRightChild().getHeight() : 0;
            int rlh = root.getRightChild().getLeftChild() != null ? root.getRightChild().getLeftChild().getHeight() : 0;
            if (rlh > rrh) { // is right child left-heavy ? perform rotateRight on the right child
                root.setRightChild(rotateRight(root.getRightChild()));
            }
            root = rotateLeft(root);
        } else if (lh - rh > 1) { //left-heavy ? rotate right
            int rrh = root.getLeftChild().getRightChild() != null ? root.getLeftChild().getRightChild().getHeight() + 1: 0;
            int rlh = root.getLeftChild().getLeftChild() != null ? root.getLeftChild().getLeftChild().getHeight() + 1: 0;
            if (rlh < rrh) { // is left child right-heavy ? perform rotateLeft on the left child
                root.setLeftChild(rotateLeft(root.getLeftChild()));
            }
            root = rotateRight(root);
        }

        return root;
    }

    public <T extends Comparable<T>> boolean contains(B_Node<T> root, T value) {
        if (root == null) return false;

        if (root.getVal().equals(value)) return true;

        if (root.getVal().compareTo(value) < 0)
            return contains(root.getRightChild(), value);
        else
            return contains(root.getLeftChild(), value);
    }

    public <T extends Comparable<T>> B_Node<T> rotateLeft(B_Node<T> node) {
        if (node == null) return null;

        B_Node<T> rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());
        rightChild.setLeftChild(node);

        // recalculate the heights of the nodes swapped
        calculateHeight(node);
        calculateHeight(rightChild);

        return rightChild;
    }

    public <T extends Comparable<T>> B_Node<T> rotateRight(B_Node<T> node) {
        if (node == null) return null;

        B_Node<T> leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());
        leftChild.setRightChild(node);

        // recalculate the heights of the nodes swapped
        calculateHeight(node);
        calculateHeight(leftChild);
        return leftChild;
    }

    public <T extends Comparable<T>> B_Node<T> deleteNode(B_Node<T> root, T value) {
        if (root == null) return null;

        if (root.getVal().compareTo(value) > 0)
            root.setLeftChild(deleteNode(root.getLeftChild(),value));
        else if (root.getVal().compareTo(value) < 0)
            root.setRightChild(deleteNode(root.getRightChild(), value));
        else {
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

    private <T extends Comparable<T>> T getMinValue(B_Node<T> node) {
        if (node == null) return null;
        if (node.getLeftChild() == null) return node.getVal();
        return getMinValue(node.getLeftChild());
    }

    public static void main(String[] args) {
        B_Node<Integer> root = new B_Node<>(13);
        _AVL avl = new _AVL();
        root = avl.addNode(root, 8);
        root = avl.addNode(root, 15);
        root = avl.addNode(root, 6);
        root = avl.addNode(root, 9);
        root = avl.addNode(root, 14);
        root = avl.addNode(root, 19);
        root = avl.addNode(root, 16);
        root = avl.addNode(root, 17);
        root = avl.addNode(root, 23);
        root = avl.addNode(root, 18);
        root = avl.addNode(root, 20);

        System.out.println(root.getVal());
        System.out.println(root);
        root = avl.deleteNode(root, 23);
        root = avl.deleteNode(root, 18);
        root = avl.deleteNode(root, 20);
        System.out.println(root);
        System.out.println("Height: " + root.getHeight());
    }
}
