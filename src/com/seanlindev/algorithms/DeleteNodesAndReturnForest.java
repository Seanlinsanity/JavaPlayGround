package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.



Example 1:


Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:

Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]


Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int delete: to_delete) {
            deleteSet.add(delete);
        }
        List<TreeNode> result = new ArrayList<>();
        TreeNode node = postOrderDFS(root, deleteSet, result);
        if (node != null) {
            result.add(node);
        }
        return result;
    }

    TreeNode postOrderDFS(TreeNode node, Set<Integer> to_delete, List<TreeNode> result) {
        if (node == null) { return null; }

        if (to_delete.contains(node.val)) {
            TreeNode left = postOrderDFS(node.left, to_delete, result);
            if (left != null) {
                result.add(left);
            }
            TreeNode right = postOrderDFS(node.right, to_delete, result);
            if (right != null) {
                result.add(right);
            }
            return null;
        }

        node.left = postOrderDFS(node.left, to_delete, result);
        node.right = postOrderDFS(node.right, to_delete, result);
        return node;
    }
}
