package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder_102 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        /*
            1. 节点出队
            2. 子节点入队
         */
        if(root == null){
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            int queLen = queue.size();
            List<Integer> path = new ArrayList<>();
            while (queLen!=0){
                TreeNode node = queue.poll();
                --queLen;
                path.add(node.val);
                // 子节点入队
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(path);
        }
        return res;
    }
}
