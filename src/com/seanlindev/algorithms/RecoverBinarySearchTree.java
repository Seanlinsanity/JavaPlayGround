package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.



Example 1:


Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:


Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1


Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 */
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderDFS(root, nodes);

        int firstIdx = -1;
        for (int i = 0; i < nodes.size(); i++) {
            if (i - 1 >= 0 && nodes.get(i).val < nodes.get(i - 1).val) {
                firstIdx = i;
                break;
            }

            if (i + 1 < nodes.size() && nodes.get(i).val > nodes.get(i + 1).val) {
                firstIdx = i;
                break;
            }
        }

        int lastIdx = -1;
        for (int i = nodes.size() - 1; i >= 0; i--) {
            if (i - 1 >= 0 && nodes.get(i).val < nodes.get(i - 1).val) {
                lastIdx = i;
                break;
            }

            if (i + 1 < nodes.size() && nodes.get(i).val > nodes.get(i + 1).val) {
                lastIdx = i;
                break;
            }
        }

        int temp = nodes.get(firstIdx).val;
        nodes.get(firstIdx).val = nodes.get(lastIdx).val;
        nodes.get(lastIdx).val = temp;
    }

    void inorderDFS(TreeNode node, List<TreeNode> nodes) {
        if (node == null) { return; }

        inorderDFS(node.left, nodes);
        nodes.add(node);
        inorderDFS(node.right, nodes);
    }

    private TreeNode previousNode;
    private TreeNode firstSwappedNode;
    private TreeNode secondSwappedNode;

    /**
     * Initiates the recovery process of the binary search tree by calling the depth-first search method
     * and then swapping the values of the two nodes that were identified as incorrectly placed.
     *
     * @param root The root of the binary tree that we are trying to recover.
     */
    public void recoverTree2(TreeNode root) {
        // Start in-order traversal to find the swapped nodes.
        inOrderTraversal(root);

        // Swap the values of the identified nodes to correct the tree.
        int temp = firstSwappedNode.val;
        firstSwappedNode.val = secondSwappedNode.val;
        secondSwappedNode.val = temp;
    }

    /**
     * Performs an in-order traversal of the binary tree to identify the two nodes that are swapped.
     * It assumes that we are dealing with a binary search tree where an in-order traversal
     * would result in a sorted sequence of values.
     *
     * @param node The current node being visited in the traversal.
     */
    private void inOrderTraversal(TreeNode node) {
        // Base case: If the current node is null, return.
        if (node == null) {
            return;
        }

        // Recursively traverse the left subtree.
        inOrderTraversal(node.left);

        // Process current node: Compare current node's value with previous node's value.
        if (previousNode != null && previousNode.val > node.val) {
            // If this condition is true, a swapped node is found.
            // If it's the first swapped node, assign previousNode to firstSwappedNode.
            if (firstSwappedNode == null) {
                firstSwappedNode = previousNode;
            }
            // Assign current node to secondSwappedNode.
            secondSwappedNode = node;
        }

        // Update previous node to the current node before moving to the right subtree.
        previousNode = node;

        // Recursively traverse the right subtree.
        inOrderTraversal(node.right);
    }
}
