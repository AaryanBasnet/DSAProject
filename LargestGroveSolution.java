package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class LargestGroveSolution {

    // Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Helper class to store information about each subtree
    class NodeInfo {
        int min;        // Minimum value in the subtree
        int max;        // Maximum value in the subtree
        int sum;        // Sum of all nodes in the subtree
        int maxBSTSum;  // Maximum sum of all BST subtrees encountered so far
        boolean isBST;  // Whether the subtree is a BST

        NodeInfo(int min, int max, int sum, int maxBSTSum, boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.maxBSTSum = maxBSTSum;
            this.isBST = isBST;
        }
    }

    // Main function to find the largest BST subtree sum
    public int largestBSTSubtree(TreeNode root) {
        return dfs(root).maxBSTSum;
    }

    // Depth-first search function to compute NodeInfo for each subtree
    private NodeInfo dfs(TreeNode node) {
        // Base case: if the node is null, return a default NodeInfo
        if (node == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, true);
        }

        // Recurse on the left and right subtrees
        NodeInfo left = dfs(node.left);
        NodeInfo right = dfs(node.right);

        // Check if the current subtree is a BST
        if (left.isBST && right.isBST && left.max < node.val && node.val < right.min) {
            // Current subtree is a BST
            int currSum = left.sum + right.sum + node.val;
            int currMin = Math.min(node.val, left.min);
            int currMax = Math.max(node.val, right.max);
            int maxBSTSum = Math.max(currSum, Math.max(left.maxBSTSum, right.maxBSTSum));
            return new NodeInfo(currMin, currMax, currSum, maxBSTSum, true);
        } else {
            // Current subtree is not a BST
            int maxBSTSum = Math.max(left.maxBSTSum, right.maxBSTSum);
            return new NodeInfo(0, 0, 0, maxBSTSum, false);
        }
    }

    // Helper function to build a binary tree from an array
    public TreeNode buildTree(Integer[] nodes) {
        if (nodes.length == 0 || nodes[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            if (nodes[i] != null) {
                current.left = new TreeNode(nodes[i]);
                queue.add(current.left);
            }
            i++;

            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        LargestGroveSolution solution = new LargestGroveSolution();

        // Input: Forest [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Integer[] nodes = {1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6};
        TreeNode root = solution.buildTree(nodes);

        // Find and print the largest BST subtree sum
        int largestBSTSum = solution.largestBSTSubtree(root);
        System.out.println("The largest BST subtree sum is: " + largestBSTSum);
    }
}
