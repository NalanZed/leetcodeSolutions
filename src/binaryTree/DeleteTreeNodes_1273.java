package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteTreeNodes_1273 {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        int[] sum = new int[nodes];
        int[] size = new int[nodes];
        Map<Integer, List<Integer>> sonTrees = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            sonTrees.put(i,new ArrayList<>());
        }

        // 将节点与其子节点关系存储在map中
        // 如果 key = 0 , 表示第 0 个节点 的子节点是 List
        for (int i = 1; i < nodes; i++) {
            int par = parent[i];
            List<Integer> sons = sonTrees.get(par);
            sons.add(i);
        }
        /*
            从最后的节点开始遍历，他们是最底层的子树。
            然后记录下子树的和，再向上走
            每次都只用记录自己+下面子树的和即可。
            直到根节点
         */
        sumDfs(sum,value,0,sonTrees,size);
        // 现在已经有了每个节点的和
        // 转变思路，收集那些和不为0的节点，遇到为0，就停止dfs
//        dfs(0,sum,sonTrees);
        return size[0];
    }
    void sumDfs(int[] sum,int[] value,int index,Map<Integer,List<Integer>> sonTrees,int[] size){
        List<Integer> sons = sonTrees.get(index);
        sum[index] = value[index];
        for (Integer son : sons) {
            sumDfs(sum,value,son,sonTrees,size);
            sum[index] += sum[son];
            size[index] += size[son];
        }
        if(sum[index] == 0){
            size[index] = 0;
        }
    }
//    void dfs(int index,int[] sum,Map<Integer,List<Integer>> sonTrees){
//        if(sum[index] == 0){
//            return;
//        }
//        ++res;
//        List<Integer> sons = sonTrees.get(index);
//        for (Integer son : sons) {
//            dfs(son,sum,sonTrees);
//        }
//    }

    public static void main(String[] args) {
        DeleteTreeNodes_1273 p = new DeleteTreeNodes_1273();
        int nodes = 38;
        int[] parent = {-1,7,0,2,22,25,2,0,7,14,25,22,25,14,0,22,22,2,14,14,22,14,25,2,14,0,32,2,2,32,25,22,0,2,2,22,14,14}, value = {-95448,-60462,-61595,68758,68709,32611,22289,-39426,-78078,91853,-56848,26101,-87072,-32610,98615,22254,-70154,86962,-93287,12168,90664,49974,-13514,23360,-12832,-64062,-54784,73509,78689,-72481,-338,81909,-63543,-88910,65612,36464,44448,-29505};
//        int[] parent = {-1,0,0},value = {1,-1,0};
        System.out.println(p.deleteTreeNodes(nodes, parent, value));
    }
}
