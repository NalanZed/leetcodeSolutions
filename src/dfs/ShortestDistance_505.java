package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestDistance_505 {
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        /*
            笨方法，用递归，找到所有可能的路径，再找最小的
            递归过程中要携带已经走过的距离。
            到达目的，或者所有可能的方向都已经走过时返回
            如果是到达目的地，那么要收集结果
         */
        // 用一个数组记录到当前节点最少的steps，如果某个点到这里时距离大于minSteps,可以直接回溯了。
        int[][] minSteps = new int[maze.length][maze[0].length];
        for (int[] minStep : minSteps) {
            Arrays.fill(minStep,Integer.MAX_VALUE);
        }
        minSteps[start[0]][start[1]] = 0;
        dfs(maze,minSteps,start);
        return minSteps[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : minSteps[destination[0]][destination[1]];
    }
    private void dfs(int[][] maze,int[][] minSteps,int[] cur){
        int x = cur[0],y = cur[1];
        // 四个方向遍历
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            int steplus = 0;
            // 一直走到撞到墙壁
            while(nextX>=0 && nextY>=0 && nextX < maze.length && nextY < maze[0].length && maze[nextX][nextY] == 0){
                nextX += direction[0];
                nextY += direction[1];
                steplus++;
            }
            // 退回到非墙壁
            nextX -= direction[0];
            nextY -= direction[1];
            int[] nextP = new int[]{nextX,nextY};
            if(minSteps[nextX][nextY] > minSteps[cur[0]][cur[1]] + steplus){
                minSteps[nextX][nextY] = steplus + minSteps[cur[0]][cur[1]];
                dfs(maze,minSteps,nextP);
            }
        }
    }
}
