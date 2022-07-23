package backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */



public class CombinedSum2 {
    public List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracing(0,new ArrayList<>(),target,0,candidates);
        return  ans;
    }
    private void backTracing(int startIndex, List<Integer> path, int target, int sum, int[] candidates){
        if(sum == target){
            //path会一直改变，不能直接把path存进去
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            if(i > startIndex && candidates[i] == candidates[i-1]){
                continue;
            }
            path.add(candidates[i]);

            backTracing(i+1,path,target,sum + candidates[i],candidates);
            path.remove(path.size()-1);
        }
    }

//    private List<Integer> removeDup(int[] candidates){
//        List<Integer> c = new LinkedList<>();
//        if(candidates.length == 0) {
//            return c;
//        }
//        c.add(candidates[0]);
//        for (int i = 1; i < candidates.length; i++) {
//            if(candidates[i]!=candidates[i-1]){
//                c.add(candidates[i]);
//            }
//        }
//        return c;
//    }

    public static void main(String[] args) {
        int[] candidates = {
                2,5,2,1,2
        };
        CombinedSum2 combinedSum2 = new CombinedSum2();
        combinedSum2.combinationSum2(candidates,5);
        System.out.println("combinedSum2.ans = " + combinedSum2.ans);
    }
}
