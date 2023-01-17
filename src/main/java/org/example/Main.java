package org.example;

import BTreeNode.B_Node;
import HashTable.ArrayMap;


import java.util.Map;

public class Main {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length / 2 ) addNode(root, nums[i]);
        }

        return root;
    }

    public TreeNode addNode(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val > root.val) root.right = addNode(root.right, val);
        else root.left = addNode(root.left, val);

        return root;
    }
    public static void main(String[] args) {
        Main m = new Main();
        TreeNode r = m.sortedArrayToBST(new int[] {-10,-3,-2,0,5,7,9});
        System.out.println(r);
    }
}