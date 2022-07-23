package everyDay;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> dic = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            res[0] = i;
            if(dic.containsKey(target - nums[i])){
                res[1] = dic.get(target-nums[i]);
                return res;
            }
            dic.put(nums[i],i);
        }
        return res;
    }
}
