package dp.medium;

import java.util.ArrayList;

public class IntTransWays {
    public static void main(String[] args) {
        int  num = 10;
        // 1. 将数字转换成单数字数组

        ArrayList<Integer> nums = new ArrayList<>();
        while(num >= 10){
            nums.add(num % 10);
            num = num / 10;
        }
        nums.add(num);
//        System.out.println(nums);

        //2. 初始化dp
        int length = nums.size();
        int[] dp = new int[length];
        //第一个数字肯定只有一种翻译方法
        dp[0] = 1;
        int res = 1;

        //3. 开始迭代
        /**
         * dp[i] = {
         * 如果第i-1个数字等于0,res*=dp[i-1],dp[i] = 1
         * 如果 （i-1）* 10 + 第i个数字 <= 25 dp[i] = {如果dp[i-1]==1，dp[i] = 2 or dp[i] = dp[i-1]+dp[i-2]}
         * 如果 （i-1）* 10 + 第i个数字 > 25 res*=dp[i-1],dp[i] = 1;
         * }
         */
        for(int i = length-2,j = 1;i>=0;i--,j++){
            if(nums.get(i+1) == 0){
                res *= dp[j-1];
//                multis.add(dp[j-1]);
                dp[j] = 1;
            }else if(nums.get(i+1) * 10 + nums.get(i) <= 25){
                if(dp[j-1] == 1){
                    dp[j] = 2;
                }else{
                    dp[j] = dp[j-1] + dp[j-2];
                }
            }else{
                res *= dp[j-1];
                dp[j] = 1;
            }
        }
        for(int number : dp){
            System.out.println(number);
        }
        System.out.println(res * dp[length-1]);
    }
}
