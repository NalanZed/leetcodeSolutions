package math;

public class ProbabilityOfHeads_1230 {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        /*
        dp[i][j]：前i枚硬币正面朝上数量为j的概率
        */
        double [][]dp = new double[n+1][target+1];
        //前面0枚硬币正面朝上数量为0的概率为1
        dp[0][0] = 1;
        // 赋初值
        for(int i = 1; i <= n; i++){
            // 前i枚硬币正面朝上数量为0的概率等于前i-1枚硬币正面朝上数量为0的概率*第i枚反面朝上的概率
            dp[i][0] = dp[i-1][0]*(1-prob[i-1]);
        }
        for(int i = 1; i <= n; i++){
            for(int k = 1; k <= i && k <= target; k++){
                /*  前i枚硬币正面朝上数量为j的概率等于
                        前i-1枚硬币正面朝上数量为j的概率*第i枚反面朝上的概率
                        加上
                        前i-1枚硬币正面朝上数量为j-1的概率*第i枚正面面朝上的概率
                */
                dp[i][k] = dp[i-1][k] * (1-prob[i-1]) + dp[i-1][k-1] * prob[i-1];
            }
        }
        return dp[n][target];
    }
}
