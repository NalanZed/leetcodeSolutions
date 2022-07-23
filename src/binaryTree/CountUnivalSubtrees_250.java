package binaryTree;

public class CountUnivalSubtrees_250 {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return count;
    }
    // boolean 返回当前是否仍然符合规则
    private boolean dfs(TreeNode root){
        boolean continueL = true;
        boolean continueR = true;
        // 叶子节点，加上
        if(root.right==null && root.left==null){
            ++count;
            return true;
        }
        // 非叶子节点，查看其子节点是否允许其继续计数
        if(root.left!=null){
            continueL = dfs(root.left);
        }
        if(root.right != null){
            continueR = dfs(root.right);
        }
        if(continueL && continueR){
            if((root.left!=null && root.val != root.left.val) || (root.right!=null && root.val != root.right.val)  ){
                return false;
            }else {
                ++count;
                return true;
            }
        }
        return false;
    }
}
