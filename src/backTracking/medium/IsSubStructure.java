package backTracking.medium;


/*
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 例如:
 * 给定的树 A:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *    4
 *   /
 *  1
 */

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

public class IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        Queue<TreeNode> que = new LinkedList<>();
        if(A == null) {
            return false;
        }
        if(B==null) {
            return false;
        }
        que.offer(A);
        while(!que.isEmpty()){
            //bfs遍历A树的所有结点，一旦发现有与B结点头val相同的结点，则进入回溯算法
            //回溯算法中利用dfs来判断是否满足要求
            //满足则返回true,不满足则返回false,重新找头节点
            TreeNode node = que.poll();
            if(node.val == B.val){
                if(backTracking(node,B)) {
                    return true;
                }
            }
            if(node.left!=null){
                que.offer(node.left);
            }
            if(node.right!=null){
                que.offer(node.right);
            }
        }
        return false;
    }
    private boolean backTracking(TreeNode node,TreeNode B) {
        //当发现B已经到达叶子结点时，无需再向下探索
        if(B.right == null && B.left==null && node.val == B.val){
            return true;
        }
        /**
         * 符合要求的条件应该是，遍历完整个树B都成功
         * 先序遍历，一旦发现不符合立即返回false
         */
        if(node.val == B.val) {
            if(B.left != null){
                if(node.left==null || !backTracking(node.left,B.left)) {
                    return false;
                }
            }
            if(B.right != null){
                if(node.right==null || !backTracking(node.right,B.right)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * 手动创建示例
         */
        TreeNode A = new TreeNode(3);
        TreeNode head = A;
        head.left = new TreeNode(4);
        head.right = new TreeNode(5);
        head = head.left;
        head.left = new TreeNode(1);
        head.right = new TreeNode(2);
        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);
//        B.right = new TreeNode(1);
//        TreeNode bHead = B.left;
//        bHead.left = new TreeNode(6);

        /**
         * 调用代码
         */
        IsSubStructure myWork = new IsSubStructure();
        boolean isSub = myWork.isSubStructure(A, B);
        System.out.println(isSub);
    }


}
