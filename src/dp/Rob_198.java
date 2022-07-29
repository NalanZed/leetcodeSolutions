package dp;

public class Rob_198 {
    /*
        动态规划
        dp[i]表示，选择第i家后，所获得的最大金额
        dp[i] =  Math.max( dp[i-2] + nums[i] , dp[i-3] + nums[i])
     */

    public int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[1],nums[0]);
        }
        int[]dp =  new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[0] + nums[2];
        int len = dp.length;
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2],dp[i-3])+ nums[i] ;
        }
        return Math.max(dp[len-1],dp[len-2]);
    }

    public static void main(String[] args) {
        int[] test = new int[]{
                1,3,1,1,6,1,1
        };
        Rob_198 p = new Rob_198();
        p.rob(test);
    }
}
