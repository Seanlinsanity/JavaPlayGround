package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.



Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // Find the Lowest Common Ancestor (LCA) of start and destination nodes
        TreeNode lowestCommonAncestor = findLowestCommonAncestor(
                root,
                startValue,
                destValue
        );

        List<Character> pathToStart = new ArrayList<>();
        List<Character> pathToDest = new ArrayList<>();

        // Find paths from LCA to start and destination nodes
        findPath(lowestCommonAncestor, startValue, pathToStart);
        findPath(lowestCommonAncestor, destValue, pathToDest);

        StringBuilder directions = new StringBuilder();

        // Add "U" for each step to go up from start to LCA
        directions.append("U".repeat(pathToStart.size()));

        // Append the path from LCA to destination
        for (Character c: pathToDest) {
            directions.append(c);
        }

        return directions.toString();
    }

    private TreeNode findLowestCommonAncestor(
            TreeNode node,
            int value1,
            int value2
    ) {
        if (node == null) { return null; }

        if (node.val == value1 || node.val == value2) {
            return node;
        }

        TreeNode left = findLowestCommonAncestor(node.left, value1, value2);
        TreeNode right = findLowestCommonAncestor(node.right, value1, value2);
        if (left != null && right != null) {
            return node;
        }

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;
    }

    private boolean findPath(
            TreeNode node,
            int targetValue,
            List<Character> path
    ) {
        if (node == null) return false;

        if (node.val == targetValue) return true;

        // Try left subtree
        path.add('L');
        if (findPath(node.left, targetValue, path)) {
            return true;
        }
        path.remove(path.size() - 1); // Remove last character

        // Try right subtree
        path.add('R');
        if (findPath(node.right, targetValue, path)) {
            return true;
        }
        path.remove(path.size() - 1); // Remove last character

        return false;
    }
}
