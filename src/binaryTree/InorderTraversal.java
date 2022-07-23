package binaryTree;

import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 */
public class InorderTraversal {

    List<Integer> res;

    //递归做法
//    public List<Integer> inorderTraversal(TreeNode root) {
//        if(root == null){
//            return null;
//        }
//        inorderTraversal(root.left);
//        res.add(root.val);
//        inorderTraversal(root.right);
//        return res;
//    }

    //非递归
    public List<Integer> inorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty() ){
            // 加入新节点前，先加入其最左边的节点
            if(root!=null){
                stack.push(root);
                root = root.left;
            }
            //将最顶层的出栈
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return  res;
    }
}
