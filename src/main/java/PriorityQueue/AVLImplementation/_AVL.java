package PriorityQueue.AVLImplementation;

import BTreeNode.B_Node;

public class _AVL {

    public <T extends Comparable<T>> B_Node<T> addNode(B_Node<T> root, T val) {
        if (root == null) return new B_Node<>(val);

        if (root.getVal().compareTo(val) < 0)
            root.setRightChild(addNode(root.getRightChild(), val));
        else
            root.setLeftChild(addNode(root.getLeftChild(), val));

        if (root.getLeftChild() == null)
        root.setHeight(root.getHeight() + 1);
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

    public <T> B_Node<T> rotateLeft(B_Node<T> node) {
        if (node == null) return null;

        B_Node<T> rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());
        rightChild.setLeftChild(node);

        return rightChild;
    }

    public <T> B_Node<T> rotateRight(B_Node<T> node) {
        if (node == null) return null;

        B_Node<T> leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());
        leftChild.setRightChild(node);
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
        avl.addNode(root, 8);
        avl.addNode(root, 15);
        avl.addNode(root, 6);
        avl.addNode(root, 9);
        avl.addNode(root, 14);
        avl.addNode(root, 19);
        avl.addNode(root, 16);
        avl.addNode(root, 23);
        avl.addNode(root, 18);

        System.out.println(root);
        avl.deleteNode(root, 23);
        System.out.println(root);
        System.out.println("Height: " + root.getHeight());
    }
}
