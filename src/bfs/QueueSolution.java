package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

}

public class QueueSolution {

    static public int[] levelOrder(TreeNode root) {

        if(root==null) {
            return new int[0];
        }
        //队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //先加入root
        queue.add(root);
        //初始化一个存储答案的list<Integer>
        List<Integer> ans = new ArrayList<>();
        //将头部出队，然后依次加入其左右子节点，再读取该头部的值
        //循环直到所有节点都被加入
        while (queue.size() != 0) {
            TreeNode head = queue.poll();
            if(head.left != null) {
                queue.addLast(head.left);
            }
            if(head.right != null) {
                queue.addLast(head.right);
            }
            ans.add(head.val);
        }
        int[] arr1 = ans.stream().mapToInt(Integer::valueOf).toArray();
        return arr1;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        int[] ok = levelOrder(t1);


    }
}
