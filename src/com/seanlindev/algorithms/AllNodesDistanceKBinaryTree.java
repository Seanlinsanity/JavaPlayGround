package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.*;

/*
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []


Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
 */
public class AllNodesDistanceKBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        dfs(root, null, graph);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(target.val);
        visited.add(target.val);
        int distance = 0;
        while (queue.size() > 0 && distance <= k) {
            if (distance == k) { return new ArrayList<>(queue); }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer node = queue.remove();
                for (Integer neighbor: graph.get(node)) {
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }

            distance += 1;
        }

        return new ArrayList<>();
    }

    void dfs(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) { return; }

        List<Integer> neighbors = new ArrayList<>();
        if (parent != null) {
            neighbors.add(parent.val);
        }

        if (node.left != null) {
            neighbors.add(node.left.val);
        }

        if (node.right != null) {
            neighbors.add(node.right.val);
        }

        graph.put(node.val, neighbors);

        dfs(node.left, node, graph);
        dfs(node.right, node, graph);
    }
}
