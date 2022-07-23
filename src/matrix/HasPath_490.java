package matrix;

import java.util.*;

public class HasPath_490 {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        /*
            逆向思维
            将所有可以横冲直撞到达终点的位置全部置为1
            规则，对goal，或者伪goal进行判断。
            如果其周围有墙，那么将墙相对于goal的相反方向全部置为1
            如果能覆盖到start，就返回true

            将goal、伪goal节点置为2
         */
        // 已经判断过的节点=3。
        // 还没有再次判断的=2
        maze[destination[0]][destination[1]] = 2;
        int rowLen = maze.length;
        int colum = maze[0].length;
        Stack<int[]> waitingStack = new Stack<>();
        waitingStack.add(destination);
        while(!waitingStack.isEmpty()){
            int[] p = waitingStack.pop();
            // 四个方向进行判断
            // 说明左边有墙,向右开始扫描，扫过之处，全部变为2
            // 且要收入在stack中
            // 如果已经是2或者3，就不收
            // 如果是1，就停止
            if(p[0]==0 || maze[p[0]-1][p[1]]==1){

                for (int i = p[0]+1; i < rowLen; i++) {
                    int flag = maze[i][p[1]];
                    if(flag==0){
                        waitingStack.add(new int[]{i,p[1]});
                        maze[i][p[1]]=2;
                    }else if(flag==1){
                        break;
                    }
                }
            }
            if(p[0]==rowLen-1 || maze[p[0]+1][p[1]]==1){

                for (int i = p[0]-1; i >=0; i--) {
                    int flag = maze[i][p[1]];
                    if(flag==0){
                        waitingStack.add(new int[]{i,p[1]});
                        maze[i][p[1]]=2;
                    }else if(flag==1){
                        break;
                    }
                }
            }
            // 如果是最后一列，或者下面一个是墙
            if(p[1]==colum-1 || maze[p[0]][p[1]+1]==1){
                for (int i = p[1]-1; i >=0; i--) {
                    int flag = maze[p[0]][i];
                    if(flag==0){
                        waitingStack.add(new int[]{p[0],i});
                        maze[p[0]][i]=2;
                    }else if(flag==1){
                        break;
                    }
                }
            }
            if(p[1]==0 || maze[p[0]][p[1]-1]==1){
                for (int i = p[1]+1; i <colum; i++) {
                    int flag = maze[p[0]][i];
                    if(flag==0){
                        waitingStack.add(new int[]{p[0],i});
                        maze[p[0]][i]=2;
                    }else if(flag==1){
                        break;
                    }
                }
            }

            //把已经判断完成的点记为3
            maze[p[0]][p[1]] = 3;

            //四个方向都判断完成之后，看看起点有没有被包括进去
            // 如果包括了，说明可以
            if(maze[start[0]][start[1]]>1){
                return true;
            }
            // 没有的话就要继续
        }

        return false;
    }


    public boolean hasPath_bfs(int[][] maze, int[] start, int[] destination){
         /*
            建立一个 boolean数组，记录是否已经访问
            不断地移动目标，将可移动到的点位放在队列中
            继续找下一个可能的点位
            知道所有能到的位置都被记录下来
            如果还没有到达目的地，那就不可达
         */
        int rowLen = maze.length;
        int colum = maze[0].length;
        boolean[][] visited = new boolean[rowLen][colum];
        Queue<int[]> wayPoint = new LinkedList<>();
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        wayPoint.add(start);
        visited[start[0]][start[1]] = true;
        while(!wayPoint.isEmpty()){
            int[] p = wayPoint.poll();
            for (int[] direction : directions) {
                int newX = p[0] + direction[0];
                int newY = p[1] + direction[1];
                // 当没有超出边界的前提下，一直朝指定方向移动，直到遇到障碍
                while(newX<colum && newX>=0 && newY>=0 && newY < colum && maze[newX][newY]==0){
                    newX = newX + direction[0];
                    newY = newY + direction[1];
                }
                // 退回到合法位置
                newX -= direction[0];
                newY -= direction[1];
                if(!visited[newX][newY]){
                    visited[newX][newY] = true;
                    wayPoint.offer(new int[]{newX,newY});
                }
            }
            if(visited[destination[0]][destination[1]]){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HasPath_490 p = new HasPath_490();
    }
}
