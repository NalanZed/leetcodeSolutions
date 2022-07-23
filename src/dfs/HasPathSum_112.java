package dfs;

import java.util.ArrayList;
import java.util.List;

public class HasPathSum_112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right==null){
            return targetSum-root.val == 0;
        }
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);

    }

    public void backTracking(TreeNode cur, int targetSum,List<List<Integer>> res,List<Integer> path){
        path.add(cur.val);
        if(cur.left == null && cur.right==null){
            if( targetSum-cur.val == 0) {
                res.add(new ArrayList<>(path));
            }else {
                return;
            }
        }
        //
        if(cur.left != null){
            backTracking(cur.left,targetSum-cur.val,res,path);
            path.remove(path.size()-1);
        }
        if(cur.right != null){
            backTracking(cur.right,targetSum-cur.val,res,path);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        backTracking(root,targetSum,res,new ArrayList<>());
        return res;
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        a.left = b;
        HasPathSum_112 p = new HasPathSum_112();
        p.hasPathSum(a,1);
    }
}
