package matrix;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands_200 {
    public int numIslands(char[][] grid) {
        int res = 0;
        /*
         BFS,往四个方向找陆地，找到就入队，找完出队
         记录一个visited数组
         外层用一个for循环，确保每个位置都找过
         */
        int rowLen = grid.length;
        int colLen = grid[0].length;
        // 记录访问情况
//        boolean[][] visited = new boolean[rowLen][colLen];
        // 待探索队列
        Queue<int[]> queue = new LinkedList<>();
        // 四个方向
        int[][] derections = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++){
                // 如果已经探寻过，直接continue
                // 不是陆地，也continue
                if(grid[i][j]=='0'){
                    continue;
                }
                // 没有continue,说明探索到一个陆地
                ++res;
                //下面要将整块陆地摸索出来。视其为一块陆地
                queue.offer(new int[]{i,j});
                grid[i][j] = '0';
                while(!queue.isEmpty()){
                    // 出队了，设置为已经访问过
                    int[] point = queue.poll();
                    // 四个方向探索
                    for (int[] derection : derections) {
                        int newX = point[0] + derection[0];
                        int newY = point[1] + derection[1];
                        //不能超过边界，且必须是没有访问过的地位置
                        if(newX<rowLen && newY<colLen && newX>=0 && newY>=0 && grid[newX][newY] == '1'){
                            //找到连在一起的陆地，加入队列
                            queue.offer(new int[]{newX,newY});
                            grid[newX][newY] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }
}
