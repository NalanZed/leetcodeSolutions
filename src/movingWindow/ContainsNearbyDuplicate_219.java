package movingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false
 * 来源：力扣（LeetCode）
 */
public class ContainsNearbyDuplicate_219 {
    /**
     * 暴力---超时了
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate_baoli(int[] nums, int k) {
        //暴力做法，对每个数组元素，遍历其位置往左k个以内、往右K个以内的数，看是否有符合要求的数
        for(int i=0;i<nums.length;i++){
            int start = (i - k) < 0 ? 0 : (i - k);
            int end = (i + k) >= nums.length ? (nums.length-1) : (i + k);
            for(int j = start;j<=end;j++){
                if(i!=j && nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate_my(int[] nums, int k) {
        //怎么滑动呢
        //先将 0 - k 存起来，过程中发现有相同的就返回true
        //然后在滑动的过程中，把最前面的删掉，往后加
        if(k==0){
            return false;
        }
        Set window = new HashSet();
        for(int i = 0;i < k && i<nums.length;i++){
            if(!window.contains(nums[i])){
                window.add(nums[i]);
            }else {
                return true;
            }
        }
        for(int i = k,j = 0;i<nums.length;i++,j++){
            if(!window.contains(nums[i])){
                window.remove(nums[j]);
                window.add(nums[i]);
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 在set的使用上做了比较好的优化
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate_standard(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;

        // 滑动窗口，保证set里面最多只有K个数。。不停的滑动
    }

    public static void main(String[] args) {
        ContainsNearbyDuplicate_219 p = new ContainsNearbyDuplicate_219();
        int[] test = {
                1,2,3,1,2,3
        };
        System.out.println(p.containsNearbyDuplicate_standard(test, 0));
    }
}
