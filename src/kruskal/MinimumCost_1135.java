package kruskal;

import union_find.UnionFind;

import java.util.Arrays;

public class MinimumCost_1135 {
    public int minimumCost(int n, int[][] connections) {
        /*
        不断添加最小边，至将所有的节点联通。
        因为不可能有环，所以我们只需要选取 n-1条边
        在由小到大选边时，要确保其不形成环，也就是，
        将要选取的边所连接的两个节点在此之前并不联通。
        是否联通可以用标记表示，相同标记的处于联通状态。
        也可以结合并查集判断。
         */
        UnionFind unionFind = new UnionFind(n);

        Arrays.sort(connections,(a,b)->(a[2]-b[2]));
//        int[] sign = new int[n+1];
        int ans = 0;
        // 边数不足，直接返回 -1
        if(connections.length < n-1) {
            return -1;
        }

        // 边数刚好，返回整体之和
        if(connections.length == n-1){
            for(int i=0;i< connections.length;i++){
                ans+=connections[i][2];
            }
            return ans;
        }
//        //建立标记
//        for (int i = 1; i < sign.length; i++) {
//            sign[i] = i;
//        }
        int edgeNeed = n - 1;
        for (int i =0; i < connections.length && edgeNeed>0; i++) {
            int e1 = connections[i][0];
            int e2 = connections[i][1];
             //联通e1,e2,当其返回false时，说明之前就是联通的，可以跳过
            if(unionFind.union(e1,e2)){
                ans += connections[i][2];
            }
            // 不会形成环
//            if(sign[e1]!=sign[e2]){
//                ans += connections[i][2];
//                edgeNeed--;
//                int sig = sign[e2];
//                // 修正标记时，要一窝打进，不能只 sign[e2] = sigh[e1],否则就没起到合并树的效果
//                for (int j = 1; j <sign.length ; j++) {
//                    if(sign[j] == sig){
//                        sign[j] = sign[e1];
//                    }
//                }
//            }
        }
        if(edgeNeed>0){
            return -1;
        }
        return ans;
    }

}
