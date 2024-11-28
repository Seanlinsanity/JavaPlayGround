package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.



Example 1:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:


Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false


Constraints:

n == leftChild.length == rightChild.length
1 <= n <= 104
-1 <= leftChild[i], rightChild[i] <= n - 1
 */

public class ValidateBinaryTreeNodes {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> rootNodeSet = new HashSet<>();
        for (int i = 0; i <= n - 1; i++) {
            rootNodeSet.add(i);
        }

        for (int i = 0; i <= n - 1; i++) {
            if (leftChild[i] != -1) {
                rootNodeSet.remove(leftChild[i]);
            }

            if (rightChild[i] != -1) {
                rootNodeSet.remove(rightChild[i]);
            }
        }

        if (rootNodeSet.size() != 1) { return false; }

        int root = new ArrayList<>(rootNodeSet).get(0);
        boolean[] visited = new boolean[n];
        if (!dfs(root, leftChild, rightChild, visited)) {
            return false;
        }

        for (boolean visit: visited) {
            if (!visit) { return false; }
        }

        return true;
    }
    // l =[1,-1,3,-1] / r =[2,-1,-1,-1]
    // 0,[t,f,f,f] => T
    // 1,[t,t,f,f] => T
    // T
    // 2,[t,t,t,f] => T
    //3, [t,t,t,t] => T
    // T
    boolean dfs(int node, int[] leftChild, int[] rightChild, boolean[] visited) {
        if (node == -1) { return true; }

        if (visited[node]) { return false; }

        visited[node] = true;
        return dfs(leftChild[node], leftChild, rightChild, visited) && dfs(rightChild[node], leftChild, rightChild, visited);
    }
}
