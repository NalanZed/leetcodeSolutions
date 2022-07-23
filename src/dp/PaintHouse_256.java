package dp;

/*


 */
public class PaintHouse_256 {
    public int minCost(int[][] costs) {
        int houseNums = costs.length;
        // 初始化dp数组
        int[] dp =new int[]{
                costs[0][0], costs[0][1], costs[0][2]
        };
        int[] tmp = new int[3];
        for(int i=1;i<houseNums;i++){
            for(int j=0;j<3;j++){
                int a = (j + 1) % 3;
                int b = (j + 2) % 3;
                tmp[j] = costs[i][j] + Math.min(dp[a],dp[b]);
            }
            dp[0] = tmp[0];
            dp[1] = tmp[1];
            dp[2] = tmp[2];
        }
        int ans = Math.min(dp[0],Math.min(dp[1],dp[2]));
        return ans;
    }
        //粉刷房子2
    public int minCostII(int[][] costs) {
        int houseNums = costs.length;
        int k = costs[0].length;
        // 初始化dp数组
        int[] dp = new int[k];
        int[] tmpDP = new int[k];

        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MAX_VALUE;
        // 初始化dp数组,以及两个最小数m1,m2
        for (int i = 0; i < k; i++) {
            dp[i] = costs[0][i];
            if(dp[i]<m2){
                if(dp[i]<m1){
                    m2 = m1;
                    m1 = dp[i];
                }else {
                    m2 = dp[i];
                }
            }
        }
        //状态转移
        for (int i = 1; i < houseNums; i++) {
            int firstMin = Integer.MAX_VALUE;
            int secondMin = Integer.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                int preCost = tmpDP[j];
                if(preCost == m1){
                    tmpDP[j] = costs[i][j] + m2;
                }else {
                    tmpDP[j] = costs[i][j] + m1;
                }
                if(tmpDP[j]<secondMin){
                    if(tmpDP[j]<firstMin){
                        secondMin = firstMin;
                        firstMin = tmpDP[j];
                    }else {
                        secondMin = tmpDP[j];
                    }
                }
            }
            m1 = firstMin;
            m2 = secondMin;
            System.arraycopy(tmpDP,0,dp,0,k);
        }
        int ans=dp[0];
        //开始状态转移
        for(int i=1;i<k;i++){
            if(dp[i]<ans){
                ans=dp[i];
            }
        }
        return ans;
    }

    // 粉刷房子3
    /**
     * dp[m][n][p]表示刷到第m个房子时形成了n个街区，粉刷了第p+1个颜色，此时消耗的最少开销
     *
     * @param houses
     * @param cost
     * @param m
     * @param n
     * @param target
     * @return
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // 最多形成m个街区(0 号 为 哨兵)
        int[][][] dp = new int[m+1][m+1][n+1];
        // 初始化dp，其实就是初始化哨兵
        for (int i = 0; i < n + 1; i++) {
            dp[0][0][i] = 0;
        }
        // 开始状态转移
        // 对房子进行遍历粉刷
        for(int i=0;i<m+1;i++){
            // 要分类讨论
            // 如果房子以及粉刷过了，

        }



        return 0;
    }


    public int minCost_standard(int[] houses, int[][] cost, int m, int n, int target) {
        // INF最大值，/2 是为了防止越界
        int INF = Integer.MAX_VALUE / 2;
        //三个维度分别对应房子编号，颜色编号，分区编号
        int[][][] dp = new int[m + 1][n + 1][target + 1];

        // 将每一个位置初始化
        for(int i = 0; i < m; i++) {
            for(int j = 1; j <= n; j++) {
                for(int k = 0; k <= target; k++) {
                    // 第0个房子
                    dp[i][j][k] = (i == 0 && k == 0) ? 0 : INF;
                }
            }
        }

        // 遍历每个房子
        for(int i = 1; i <= m; i++){
            // 获取房子对应颜色，其中-1表示未上色
            int color = houses[i - 1];

            // 遍历每种可能的颜色
            for(int j = 1; j <= n; j++){
                // 遍历每种分区，分区必然从1开始
                for (int k = 1; k <= i && k <= target; k++){

                    // 第 i 间房子已经上色
                    if (color != 0) {
                        //「本来的颜色」已经固定，不能再刷，不允许状态转移，置为INF
                        if (j != color) {
                            dp[i][j][k] = INF;
                        }
                        // 与「本来的颜色」相同，允许被转移
                        else {
                            // 1. 当前颜色为新分区，前后房子颜色不同
                            // 即，从「上一分区」「不同颜色」房子中找「花费最少」的情况
                            int tmp1 = INF;
                            for (int p = 1; p <= n; p++) {
                                if (p != j) {
                                    tmp1 = Math.min(tmp1, dp[i - 1][p][k - 1]);
                                }
                            }

                            // 2. 不形成新分区，前后房子颜色相同
                            // 即，「上一分区」「相同颜色」的房子
                            int tmp2 = dp[i - 1][j][k];


                            // 两者情况中找花费最少的进行状态转移
                            dp[i][j][k] = Math.min(tmp1, tmp2);
                        }

                        // 第 i 间房子尚未上色
                    }
                    else {
                        // 1. 给当前颜色设立新分区，前后房子颜色不同
                        // 即，从「上一分区」中，「不同颜色」房子中找「花费最少」的情况
                        int tmp1 = INF;
                        for (int p = 1; p <= n; p++) {
                            if (p != j) {
                                tmp1 = Math.min(tmp1, dp[i - 1][p][k - 1]);
                            }
                        }
                        // 2. 不形成新分区，前后房子颜色相同
                        // 即，「上一分区」「相同颜色」的房子
                        int tmp2 = dp[i - 1][j][k];

                        // 两者情况中找花费最少的进行状态转移，同时要加上刷新漆要用的金额
                        dp[i][j][k] = Math.min(tmp1, tmp2) + cost[i - 1][j - 1];
                    }
                }
            }
        }


        // 从「考虑所有房间，并且形成分区数量为 target」的所有方案中找答案
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[m][i][target]);
        }

        return ans == INF ? -1 : ans;
    }





    public static void main(String[] args) {
        PaintHouse_256 p = new PaintHouse_256();
        int h = 2; int k = 2;
        int[][] test = new int[h][k];
        test[0][0] = 1;
        test[0][1] = 3;
        test[1][0] = 2;
        test[1][1] = 4;
//        int[] test0 = new int[]{
//               17,9,6,2,6,18,8,12,3,5,9,11,20,8,13,16
//        };
//        int[][] test = new int[1][16];
//        test[0] = test0;
        p.minCostII(test);
    }
}
