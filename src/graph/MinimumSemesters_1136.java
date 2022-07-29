package graph;

import java.util.*;

public class MinimumSemesters_1136 {

    //用并查集做不了有向图。
//    class DisjointSet{
//        int[] parent;
//        int[] rank;
//        int maxRank;
//
//        public DisjointSet(int n){
//            parent = new int[n];
//            rank = new int[n];
//            maxRank = 1;
//            for (int i = 0; i < n; i++) {
//                parent[i] = i;
//            }
//        }
//        public int find(int x){
//            while(parent[x] != x){
//                x = parent[x];
//            }
//            return parent[x];
//        }
//        public boolean join(int x,int y){
//            if(find(x) == y){
//                return false;
//            }
//            // 有向图，x是y的parent
//            parent[y] = x;
//            // 所有的parent 都要加一
//            if(rank[y]+1>rank[x]){
//                rank[x] = rank[y] + 1;
//                while(x!=parent[x]){
//                    x = parent[x];
//                    rank[x]++;
//                }
//            }
//            maxRank = Math.max(maxRank,rank[x]);
//            return true;
//        }
//    }
//
//    public int minimumSemesters(int n, int[][] relations) {
//        DisjointSet disjointSet = new DisjointSet(n+1);
//
//        for (int[] relation : relations) {
//            int x = relation[0];
//            int y = relation[1];
//            if(!disjointSet.join(x,y)){
//                return -1;
//            }
//        }
//        return disjointSet.maxRank+1;
//    }

    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> indegs = new ArrayList();
        for(int i=0;i<=n;i++)indegs.add(new ArrayList());
        int[] indeg = new int[n+1];
        for(int[] arr:relations){
            indeg[arr[1]]+=1;
            indegs.get(arr[0]).add(arr[1]);
        }
        Queue<Integer> queue = new LinkedList();
        for(int i=1;i<=n;i++){
            if(indeg[i]==0)queue.offer(i);
        }
        int ans = 0,cnt = 0;
        while(!queue.isEmpty()){
            ++ans;
            int size = queue.size();
            for(int i=0;i<size;i++){
                int cur = queue.poll();
                cnt+=1;
                if(cnt==n)return ans;
                for(int next : indegs.get(cur)){
                    if(--indeg[next]==0)queue.offer(next);
                }
            }
        }
        return -1;
    }

    public int minimumSemesters_cyc(int n, int[][] relations) {
        int[] source = new int[n+1];      //入度数组
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        boolean[] visitd = new boolean[n+1];
        visitd[0] = true;
        int cnt = 0;
        for (int i = 0; i < relations.length; i++){
            map.put(relations[i][0],relations[i][1]);
            source[relations[i][1]]++;  //入度数加1
        }
        for (int j = 1; j < source.length; j++){
            if (source[j] == 0){
                queue.offer(j);
            }
        }
        while (!queue.isEmpty()){
            while (!queue.isEmpty()) {//入度为0全部出队
                visitd[queue.peek()] = true;
                if (map.containsKey(queue.peek())) {
                    source[map.get(queue.poll())]--;         //入度数-1
                }else {
                    queue.poll();
                }
                cnt++;
            }
            count++;
            if (cnt == n) return count;
            for (int j = 1; j < source.length; j++){  //入度为0入队
                if (visitd[j] == false && source[j] == 0){
                    queue.add(j);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{
                {1,2},{3,2},{2,4}
        };
        MinimumSemesters_1136 p = new MinimumSemesters_1136();
        System.out.println(p.minimumSemesters(4, test));
    }
}
