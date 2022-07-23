package bfs;

import bfs.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class QueueWithStack {
    static public List<List<Integer>> levelOrder(TreeNode root) {

        LinkedList<Integer> in = new LinkedList<>();
        if(root==null) {
            return new ArrayList<>();
        }
        //判断是否为偶数层
        Boolean evenLevel = false;
        //队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //先加入root
        queue.add(root);
        //result
        List<List<Integer>> result = new ArrayList<>();
        //辅助栈
        Stack<Integer> stack = new Stack<>();
        while(!queue.isEmpty()){
            //临时的list，用来存储某一层的数据
            LinkedList<Integer> tempList = new LinkedList<>();

            for(int i = queue.size();i > 0;i--){
                TreeNode node = queue.poll();
                //说明是偶数层
                if(result.size() % 2 ==0){
                    //偶数层从右到左
                    tempList.addLast(node.val);
                }
                else{
                    tempList.addFirst(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
//            //偶数层则反转temp
//            if(evenLevel){
//                for (int i = 0,j = tempList.size()-1;i<j; i++,j--) {
//                    Integer tem = tempList.get(i);
//                    tempList.set(i,tempList.get(j));
//                    tempList.set(j,tem);
//                }
//            }
//            //boolean计数
//        evenLevel =  !evenLevel;
        result.add(tempList);

        }

    return result;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t4.left = t5;
        t4.right = t6;
        t5.left = t7;
        t5.right = t8;
        List<List<Integer>> ok = levelOrder(t1);
        for (List<Integer> list: ok) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("list.get(i) = " + list.get(i));
            }
        }
    }
}
