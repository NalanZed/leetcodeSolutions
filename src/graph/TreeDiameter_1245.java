package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeDiameter_1245 {
    int firstNode=0,maxDepth=0;
    public int treeDiameter(int[][] edges) {
        if(edges.length == 0){
            return 0;
        }
        // 邻接表
        Map<Integer, List<Integer>> neighborTable = new HashMap<>();
        // 初始化
        for (int[] edge : edges) {
            int l = edge[0];
            int r = edge[1];
            if(!neighborTable.containsKey(l)){
                List<Integer> lTable = new ArrayList<>();
                lTable.add(r);
                neighborTable.put(l,lTable);
            }else {
                neighborTable.get(l).add(r);
            }
            if(!neighborTable.containsKey(r)){
                List<Integer> rTable = new ArrayList<>();
                rTable.add(l);
                neighborTable.put(r,rTable);
            }else {
                neighborTable.get(r).add(l);
            }
        }
        boolean[] vis = new boolean[neighborTable.size()];
        dfs(neighborTable,0,0,vis);
        boolean[] vis2 = new boolean[neighborTable.size()];
        dfs(neighborTable,firstNode,0,vis2);
        return maxDepth;
    }

    private void dfs(Map<Integer, List<Integer>> neighborTable,int node,int depth,boolean[] visited){
        List<Integer> nTables = neighborTable.get(node);
        visited[node] = true;
        if(depth>maxDepth){
            firstNode = node;
            maxDepth = depth;
        }
        for (int i = 0; i < nTables.size(); i++) {
            if(!visited[nTables.get(i)]){
                dfs(neighborTable,nTables.get(i),depth+1,visited);
            }
        }
    }


    int res = 0;
    public int treeDiameter_half(int[][] edges) {
        List<Integer>[] map = new ArrayList[edges.length+1];

        for(int i=0 ; i<map.length ; i++){
            map[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }

        dfs(map,0,new boolean[edges.length+1]);
        return res;
    }

    /*
    记录最大的两个数，求和就是res，只用一次dfs
 */
    public int dfs(List<Integer>[] map,int index,boolean[] visited){
        visited[index] = true;
        List<Integer> list = map[index];
        int max1 = 0;
        int max2 = 0;
        for(int next : list){
            if(!visited[next]){
                int num = dfs(map,next,visited);
                if(num>max1){
                    max2 = max1;
                    max1 = num;
                } else if(num>max2){
                    max2 = num;
                }
            }
        }
        res = Math.max(res,max1+max2);

        return Math.max(max1,max2)+1;

    }
}
