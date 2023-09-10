package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.



Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]


Constraints:

The number of the nodes in the tree will be in the range [1, 5000]
-200 <= Node.val <= 200
 */
public class FindDuplicateSubtree {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> treeCountMap = new HashMap<>();
        preOrder(root, treeCountMap, result);
        return result;
    }

    String preOrder(TreeNode root, Map<String, Integer> treeCountMap, List<TreeNode> result) {
        if (root == null) {
            return "n";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(root.val));
        builder.append("-");
        builder.append(preOrder(root.left, treeCountMap, result));
        builder.append("-");
        builder.append(preOrder(root.right, treeCountMap, result));
        String traverseStr = builder.toString();

        treeCountMap.putIfAbsent(traverseStr, 0);
        Integer count = treeCountMap.get(traverseStr);
        if (count == 1) {
            result.add(root);
        }
        treeCountMap.put(traverseStr, count + 1);

        return traverseStr;
    }
}
