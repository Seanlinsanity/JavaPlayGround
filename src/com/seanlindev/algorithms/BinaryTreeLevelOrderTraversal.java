package com.seanlindev.algorithms;

import com.seanlindev.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) { return new ArrayList<>(); }

        List<List<Integer>> result = new ArrayList<>();
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while(nodeList.size() > 0) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = nodeList.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeList.get(0);
                list.add(node.val);
                nodeList.remove(0);
                if (node.left != null) {
                    nodeList.add(node.left);
                }

                if (node.right != null) {
                    nodeList.add(node.right);
                }
            }
            result.add(list);
        }

        return result;
    }
}
