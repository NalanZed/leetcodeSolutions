package bfs;

import bfs.TreeNode;

import java.util.ArrayList;
import java.util.List;



public class RecursionSolution {

    public static List<List<Integer>> loader = new ArrayList<>();

    public static int[] levelOrder(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        recursion(root,1);

        List<Integer> lineList = new ArrayList<>();
        for (List<Integer> list: loader) {
            for (int i=0;i<list.size();i++){
                lineList.add(list.get(i));
            }
        }
        int[] ans = new int[lineList.size()];
        for(int i = 0;i<lineList.size();i++){
            ans[i] = lineList.get(i);
        }
        return ans;
    }

    //用这种方式实现层序遍历，其实本质还是DFS的方法
    //通过设置一个额外的level，来记录当前结点的层数，使其对号入座
    //但实际遍历的过程，还是深度优先

    public static void recursion(TreeNode node , int level){
        //发现本结点为null,立即终止
        if(node == null) {
            return;
        }
        //如果本结点的层数超过了二维数组的行数，那就创建一行来存储，即开辟新的一行
        if(level > loader.size()){
            loader.add(new ArrayList<Integer>());
        }
        //对号入座，自己的level是多少，就加到二维数组的第几行去
        loader.get(level-1).add(node.val);
        //左右子结点递归
        recursion(node.left,level + 1);
        recursion(node.right,level + 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        int [] ok = levelOrder(t1);
        for (int i = 0; i < ok.length; i++) {
            System.out.println("ok[i] = " + ok[i]);
        }
    }

}