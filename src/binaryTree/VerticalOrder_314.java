package binaryTree;

import java.util.ArrayList;
import java.util.List;


public class VerticalOrder_314 {
    List<List<Integer>> res = new ArrayList<>();
    // 这个解法没有保证从上到下
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null){
            return null;
        }
        /*
            向左列数-1，向右列数+1
            列号相等的放在一个List中
         */
        // 先看看左边一共有多少列，因为我们要从中间开始编号
        TreeNode tmpRoot = root;
        int startIndex = 0;
        while(tmpRoot.left!=null){
            startIndex++;
            tmpRoot = tmpRoot.left;
            res.add(new ArrayList<>());
        }
        dfs(root,startIndex,0);
        return  res;
    }
    private void dfs(TreeNode node,int index,int level){

        if(res.size()<index+1){
            res.add(new ArrayList<>());
        }
        res.get(index).add(node.val);
        if(node.left!=null){
            dfs(node.left,index-1,level+1);
        }
        if(node.right!=null){
            dfs(node.right,index+1,level+1);
        }
    }

}
