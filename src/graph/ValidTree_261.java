package graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    判断一个图是否能成为树
 */
/*
    条件
    1. 无环
    2. 连通
 */
public class ValidTree_261 {

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i,new ArrayList<>());
        }
        //初始化边集
        for (int[] edge : edges) {
            int a = edge[0],b = edge[1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        boolean[] visited = new boolean[n];
        if(hasCycle(0,map,visited,-1)){
            return false;
        }
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    private boolean hasCycle(int cur, Map<Integer, List<Integer>> map,boolean[] visited,int parent){
        visited[cur] = true;
        List<Integer> nexts = map.get(cur);
        for (Integer next : nexts) {
            if(!visited[next]){
                if(hasCycle(next,map,visited,cur)) return true;
            }else {
                if(next != parent){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidTree_261 p = new ValidTree_261();
        int[][] test = new int[][]{
                {0,1},{0,2},{0,3},{1,4}
        };
        System.out.println(p.validTree(5, test));
    }
}
