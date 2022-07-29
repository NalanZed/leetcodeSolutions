package dfs;

public class TwoSumBSTs_1214 {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        return dfs(root1,root2,target-root1.val-root2.val);
    }

    private boolean dfs(TreeNode root1,TreeNode root2, int target){
        if(target == 0){
            return true;
        }
        boolean try1,try2;
        if(target<0){
            if(root1.left!=null){
                try1 = dfs(root1.left,root2,target+ root1.val-root1.left.val);
                if (try1){
                    return true;
                }
            }
            if(root2.left!=null){
                try2 = dfs(root1,root2.left,target+root2.val-root2.left.val);
                if (try2){
                    return true;
                }
            }
        }else {
            if(root1.right!=null){
                try1 = dfs(root1.right,root2,target+root1.val-root1.right.val);
                if (try1){
                    return true;
                }
            }
            if(root2.right!=null){
                try2 = dfs(root1,root2.right,target+root2.val-root2.right.val);
                if (try2){
                    return true;
                }
            }
        }
        return false;
    }

}
