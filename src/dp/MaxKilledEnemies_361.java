package dp;

public class MaxKilledEnemies_361 {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] rowKill = new int[m][n];
        int[][] colKill = new int[m][n];
        //计算各点行上和列上杀死的敌人数
        for(int i = 0; i < m; i++){
            int j = 0;
            while(j < n){
                int cnt = 0;
                int last = j;
                while(j < n && grid[i][j] != 'W'){
                    if(grid[i][j] == 'E') cnt++;
                    j++;
                }
                for(int k = last; k < j; k++){
                    if(grid[i][k] == '0') rowKill[i][k] = cnt;
                }
                if(j < n) rowKill[i][j] = 0;
                j++;
            }
        }
        for(int i = 0; i < n; i++){
            int j = 0;
            while(j < m){
                int cnt = 0;
                int last = j;
                while(j < m && grid[j][i] != 'W'){
                    if(grid[j][i] == 'E') cnt++;
                    j++;
                }
                for(int k = last; k < j; k++){
                    colKill[k][i] = cnt;
                }
                if(j < m) colKill[j][i] = 0;
                j++;
            }
        }

        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '0') ans = Math.max(ans, rowKill[i][j] + colKill[i][j]);
            }
        }

        return ans;
    }
}
