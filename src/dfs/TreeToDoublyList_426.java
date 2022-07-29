package dfs;



public class TreeToDoublyList_426 {
    /*
        后序遍历
        后序遍历的关键是，对于每个节点，
        他都要与其左子树中最大的节点建立连接，与其右子树最小的节点建立连接

        最后再把收尾相接

     */
    public TreeNode treeToDoublyList(TreeNode root) {
        TreeNode leftMin = root;TreeNode rightMax = root;
        while (leftMin!=null){
            leftMin = leftMin.left;
        }
        while (rightMax!=null){
            rightMax = rightMax.right;
        }
        dfs_h(root,false);
        leftMin.left = rightMax;
        rightMax.right = leftMin;

        return leftMin;
    }

    // direction表示方向，false表示左，true表示右
    // 方向决定了它的返回值，到底返回最小的还是最大的。
    private TreeNode dfs_h(TreeNode root,boolean direction){
        if(root == null){
            return null;
        }
        TreeNode leftMax = dfs_h(root.left,false);
        TreeNode rightMin = dfs_h(root.right,true);
        if(leftMax!=null){
            leftMax.right = root;
            root.left = leftMax;
        }else {
            root.left = null;
        }
        if(rightMin != null){
            rightMin.left = root;
            root.right = rightMin;
        }else {
            root.right = null;
        }
        // direction 为true表示，此时要返回最小的节点，于是，在已经建立好的链表中找最小的节点返回
        if(direction){
            while(root.left!=null){
                root=root.left;
            }
            //为false时同理
        }else {
            while(root.right!=null){
                root=root.right;
            }
        }
        return root;
    }
}
