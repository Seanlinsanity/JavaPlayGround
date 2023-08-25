package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */

public class ConstructBinaryTreeFromInorderPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 1) { return new TreeNode(inorder[0]); }
        Map<Integer, Integer> inorderNumIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderNumIndexMap.put(inorder[i], i);
        }
        return buildTreeRecursion(0, inorder.length - 1, 0, postorder.length - 1, inorder, postorder, inorderNumIndexMap);
    }

    TreeNode buildTreeRecursion(
            int inorderStart,
            int inorderEnd,
            int postorderStart,
            int postorderEnd,
            int[] inorder,
            int[] postorder,
            Map<Integer, Integer> inorderNumIndexMap
    ) {
        if (inorderStart > inorderEnd) { return null; }

        int rootVal = postorder[postorderEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inorderNumIndexMap.get(rootVal);

        int newInorderStart = inorderStart;
        int newInorderEnd = rootIndex - 1;
        int newPostorderStart = postorderStart;
        int newPostorderEnd = postorderStart + newInorderEnd - newInorderStart;
        root.left = buildTreeRecursion(newInorderStart, newInorderEnd, newPostorderStart, newPostorderEnd, inorder , postorder, inorderNumIndexMap);

        newInorderStart = rootIndex + 1;
        newInorderEnd = inorderEnd;
        newPostorderStart = newPostorderEnd + 1;
        newPostorderEnd = postorderEnd - 1;
        root.right = buildTreeRecursion(newInorderStart, newInorderEnd, newPostorderStart, newPostorderEnd, inorder , postorder, inorderNumIndexMap);
        return root;
    }
}
