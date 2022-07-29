package hardToSolve;

public class NumberOfWays_1259 {
    public int numberOfWays(int numPeople) {
        int ans = 0;
        /*
         第1个人只能和 第2，4...n个人握手
         每次和k握手完，他们之间连线，把人群分成了 k-2,和 n-k-1 的两个人群,他们之间可以组合出 dp[k-2] * dp[n-k-2]种连线方式
         所以，dp[n] = sum_K(k=0,2...n)(dp[k]*dp[n-k-2])
         */
        int mod = 1000000007;
        long[] dp = new long[numPeople+1];
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= numPeople; i=i+2) {
            long count = 0;
            for (int k = 0; k <= i-2; k=k+2) {
                count = count + ((dp[k] * dp[i-k-2])%mod);
            }
            dp[i] = count % mod;
        }
        ans = (int)(dp[numPeople] % mod);
        return ans;
    }

    public static void main(String[] args) {
        NumberOfWays_1259 p = new NumberOfWays_1259();
        System.out.println(p.numberOfWays(140));

    }
}
