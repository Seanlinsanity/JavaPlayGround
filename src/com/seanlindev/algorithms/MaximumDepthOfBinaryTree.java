package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2


Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //[3,9,20,null,null,15,7]
     public int maxDepth2(TreeNode root) {
         if (root == null) { return 0; }
         List<TreeNode> nodes = new ArrayList<>();
         nodes.add(root);
         int depth = 0;
         int currentIndex = 0;
         while (currentIndex < nodes.size()) { //[1]
             depth += 1; //1
             int size = nodes.size();
             for (int i = currentIndex; i < size; i++) {
                 TreeNode currentNode = nodes.get(i);
                 if (currentNode.left != null) {
                     nodes.add(currentNode.left);
                 }

                 if (currentNode.right != null) {
                     nodes.add(currentNode.right);
                 }
                 currentIndex += 1;
             }
         }

         return depth;
     }
}
