package union_find;

import java.util.Arrays;

public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int nodesNum){
        this.parent = new int[nodesNum];
        // 初始化parent,每个节点的parent初始化为自身
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        this.rank = new int[nodesNum];
        // 每个树初始rank为1
        Arrays.fill(rank,1);
    }

    // 找到自己的root
    public int find(int x){
        while (parent[x]!=x){
            x = parent[x];
        }
        return x;
    }

    // 如果原本就是联通的，返回false
    public boolean union(int x , int y){
        int rootx = find(x);
        int rooty = find(y);
        if(rootx == rooty) return false;
        // 优化结构
        if(rank[rootx] > rank[rooty]){
            parent[rooty] = rootx;
        }else if(rank[rootx] < rank[rooty]){
            parent[rootx] = rooty;
        }else {
            parent[rooty] = rootx;
            rank[rootx]++;
        }
        return true;
    }

    public boolean connected(int x,int y){
        int rootx = find(x);
        int rooty = find(y);
        if(rootx == rooty) return true;
        return false;
    }

}
