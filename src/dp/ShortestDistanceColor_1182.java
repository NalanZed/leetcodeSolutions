package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestDistanceColor_1182 {
    /*
     动态规划思路
     状态数组dp[i][j] 表示，索引 i 到 颜色 j 的最短距离
     由于这里的最短距离，可能是从dp[i-1][j] 递推过来的，也可能是从 dp[i+1][j]过来的
     即：最短距离可能是距离左边，也可能是距离右边
     所以要进行两次动态规划并对比。
  */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries){
        int colorLen = colors.length;
        // 构建并初始化dp
        int[][] dp = new int[colorLen][3];
        // 初始值，设定为所有初始距离都为MAXVALUE-1,为了防止最大值溢出
        // 其中，索引位置颜色与目标颜色相同的置为0
        for (int[] ints : dp) {
            Arrays.fill(ints,Integer.MAX_VALUE-1);
        }
        for (int i = 0; i < colorLen; i++) {
            dp[i][colors[i]-1] = 0;
        }
        //第一次状态转移，从左往右
        for (int i = 1; i < colorLen; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + 1 ,dp[i][j]);
            }
        }
        //第二次状态转移，从右往左
        for (int i = colorLen-2; i>=0; --i) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i+1][j] + 1,dp[i][j]);
            }
        }
        List<Integer> res = new ArrayList<>();
        // 索引出答案
        for (int[] query : queries) {
            int tmp = dp[query[0]][query[1]-1] == (Integer.MAX_VALUE - 1) ? -1 : dp[query[0]][query[1]-1];
            res.add(tmp);
        }
        return res;
    }

}
