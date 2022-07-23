package backTracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 */

public class Permute2 {
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
                if(i>0 && !used[i-1] && nums[i] == nums[i-1]){
                    continue;
                }
                path.addLast(nums[i]);
                used[i] = true;
                backTracing(nums,used,path);
                path.removeLast();
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        Permute2 permute = new Permute2();
        List<List<Integer>> permute1 = permute.permute(nums);
        System.out.println("permute1 = " + permute1);
    }

}
