package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 */
public class SerializeDeserializeBinaryTree {
    private int index;

    public SerializeDeserializeBinaryTree() {
        index = 0;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) { return ""; }

        List<String> nodes = new ArrayList<>();
        preOrder(root, nodes);
        return String.join(",", nodes);
    }

    private void preOrder(TreeNode root, List<String> nodes) {
        if (root == null) {
            nodes.add("N");
            return;
        }

        nodes.add(String.valueOf(root.val));
        preOrder(root.left, nodes);
        preOrder(root.right, nodes);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) { return null; }

        String[] nodes = data.split(",");
        TreeNode root = dfs(nodes);

        return root;
    }

    private TreeNode dfs(String[] nodes) {
        if (index == nodes.length) {
            return null;
        }

        if (nodes[index].equals("N")) {
            index += 1;
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(nodes[index]));
        index += 1;
        node.left = dfs(nodes);
        node.right = dfs(nodes);
        return node;
    }
}
