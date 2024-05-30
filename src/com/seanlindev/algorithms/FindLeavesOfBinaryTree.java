package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, collect a tree's leaf nodes by following the given rules and return them in a 2D array.

Collect all the leaf nodes from the left to right.
Remove all the leaf nodes.
Repeat until the tree is empty.
Examples
Example 1:
Input: root = [1,2,3,4]
Image
Expected Output: [[4,3],[2],[1]]

Justification: Initially, nodes 4 and 3 are leaves and are removed in the first round. In the second round, node 2 becomes a leaf. Node 1 is the last one remaining and is removed in the final round.

Example 2:

Input: root = [1,2,3,null, null,4,5,null, null,6,null]
Image
Expected Output: [[2,4,6],[5],[3],[1]]

Justification: In the first round, the leaves are nodes 2, 4, and 6. Once removed, node 5 becomes the leaf in the second round, and then node 3 becomes the leaf node in the third round. Finally, node 1 is left and removed in the last round.

Example 3:

Input: root = [1,2,null,3,null,4]
Image
Expected Output: [[4],[3],[2],[1]]
Justification: This tree has a linear structure. Each node becomes a leaf one after the other, starting from node 4 to node 1.

 */
public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> collect = new ArrayList<>();
        dfs(root, collect);
        return collect;
    }

    private int dfs(TreeNode node, List<List<Integer>> leaves) {
        if (node == null) return -1; // Base case: If node is null, return -1 indicating height -1
        // Recursively calculate the heights of left and right subtrees
        int leftHeight = dfs(node.left, leaves);
        int rightHeight = dfs(node.right, leaves);
        // Calculate the current node's height as the maximum of left and right subtree heights plus 1
        int currHeight = Math.max(leftHeight, rightHeight) + 1;
        // If the list of leaves doesn't have a list at the current height, create a new list
        if (leaves.size() <= currHeight) leaves.add(new ArrayList<>());
        // Add the current node's value to the list of leaves at the current height
        leaves.get(currHeight).add(node.val);
        return currHeight; // Return the current height
    }
}
