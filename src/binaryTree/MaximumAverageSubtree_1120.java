package binaryTree;


import linkedNodes.ListNode;

import java.util.ArrayList;
import java.util.List;

class AvgScore{
    int nodesNum;
    int totalSocre;
    public AvgScore(int nodesNum,int totalSocre){
        this.nodesNum = nodesNum;
        this.totalSocre = totalSocre;
    }
    public double calAvg(){
        if(nodesNum!=0){
            return (totalSocre*1.0) / nodesNum;
        }
        return 0.0;
    }
    public  AvgScore union(AvgScore left,AvgScore right){
        int leftNode = left == null ? 0 : left.nodesNum;
        int rightNode = right == null ? 0 : right.nodesNum;
        int leftScore = left == null ? 0 : left.totalSocre;
        int rightScore = right == null ? 0 : right.totalSocre;
        return new AvgScore(leftNode + rightNode + this.nodesNum,this.totalSocre+leftScore + rightScore);
    }
}

public class MaximumAverageSubtree_1120 {
    public double maximumAverageSubtree(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null){
            return 0.0;
        }
        dfs(root,res);
        double maxAvg = Double.MIN_VALUE;
        for (Double re : res) {
            maxAvg = Math.max(re,maxAvg);
        }
        return maxAvg;
    }
    public AvgScore dfs(TreeNode root,List<Double> res){
        if(root.left == null && root.right == null){
            AvgScore avgScore = new AvgScore(1, root.val);
            double avg = avgScore.calAvg();
            res.add(avg);
            return avgScore;
        }
        AvgScore leftAvg = null,rightAvg = null;
        if(root.left != null){
            leftAvg = dfs(root.left,res);
        }
        if(root.right != null){
            rightAvg = dfs(root.right,res);
        }
        AvgScore curAvg = new AvgScore(1,root.val);
        curAvg = curAvg.union(leftAvg,rightAvg);
        double avg = curAvg.calAvg();
        res.add(avg);
        return curAvg;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        n1.right = n2;
        MaximumAverageSubtree_1120 p = new MaximumAverageSubtree_1120();
        p.maximumAverageSubtree(n1);
    }
}
