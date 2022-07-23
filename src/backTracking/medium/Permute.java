package backTracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Permute {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if(len == 0) {
            return ans;
        }
        boolean[] used = new boolean[len];
        backTracing(nums,used,new LinkedList<>());
        return ans;
    }
    private void backTracing(int[] nums, boolean[] used, Deque<Integer> path){
        if(path.size() == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length;i++){
            if(!used[i]){
                path.addLast(nums[i]);
                used[i] = true;
                backTracing(nums,used,path);
                path.removeLast();
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {};
        Permute permute = new Permute();
        List<List<Integer>> permute1 = permute.permute(nums);
        System.out.println("permute1 = " + permute1);
    }
}
