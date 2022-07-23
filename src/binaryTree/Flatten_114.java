package binaryTree;

public class Flatten_114 {
    public void flatten(TreeNode root) {
        /**
         * 就是把左子节点插入到节点与其右节点中间
         * 要自底向上改动
         */
        //
//        {
//            //核心处理，把左节点插入到root与root.right中间
//            // 要求root.left!=null
//            TreeNode right = root.right;
//            TreeNode tailNode = root.left.right;
//            while(tailNode.right!=null){
//                tailNode = tailNode.right;
//            }
//            tailNode.right = right;
//            root.right = right.left;
//            root.left = null;
//        }

        if(root.left==null){
            return;
        }
        flatten(root.left);
        transfor(root);
        flatten(root.right);
        return;
    }

    private void transfor(TreeNode root){
        //核心处理，把左节点插入到root与root.right中间
        // 要求root.left!=null
        TreeNode right = root.right;
        TreeNode tailNode = root.left;
        while(tailNode.right!=null){
            tailNode = tailNode.right;
        }
        tailNode.right = right;
        root.right = right.left;
        root.left = null;
    }
}
