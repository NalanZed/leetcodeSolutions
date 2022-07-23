package dp.easy;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        //定义状态--添加nums[i]时最大值为dp[i]
        int[] dp = new int[len];
        dp[0] = nums[0];
        //状态转移方程
        for (int i = 1; i < len; i++) {
            if(dp[i-1]<=0){
                dp[i] = dp[i-1] + nums[i];
            }else {
                dp[i] = nums[i];
            }
        }
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(dp[i],res);
        }
        return res;
    }
}
