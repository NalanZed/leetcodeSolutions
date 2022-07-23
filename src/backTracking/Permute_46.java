package backTracking;

import java.util.ArrayList;
import java.util.List;

public class Permute_46 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums,new ArrayList<>(),used);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, boolean[] used) {
        // 收割结果
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums,path,used);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }
}