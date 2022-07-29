package dp;

import java.util.PriorityQueue;

public class MaxinumMinimumPath_1102 {
    class Node{
        public int x;
        public int y;
        public int v;
        public Node(int x,int y,int v){
            this.v = v;
            this.x = x;
            this.y = y;
        }
    }


    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 大根堆
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1,o2)->o2.v- o1.v);
        //初始化大根堆
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                priorityQueue.offer(new Node(1,1,1));
            }
        }

        return 0;
    }
}
