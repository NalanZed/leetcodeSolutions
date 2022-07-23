package prefixSum;


import java.util.HashMap;
import java.util.Map;

public class PathSum_437 {
    Map<Long,Integer> map;
    int ans = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        map =  new HashMap<>();
        map.put(0L,1);
        dfs(root,0,targetSum);
        return ans;
    }
    public void dfs(TreeNode cur,long lastSum,int targetSum){
        //获取当前和,及其之前出现的次数
        Long newSum = lastSum + cur.val * 1L;
        int count = map.getOrDefault(newSum,0);
        //获取路径
        if(map.containsKey(newSum - targetSum)){
            ans += map.get(newSum - targetSum);
        }
        map.put(newSum,count+1);
        // 递归到叶子节点，递归阶段，一边往map里塞和，一边记录ans
        if(cur.left != null){
            dfs(cur.left,newSum,targetSum);
        }
        if(cur.right != null){
            dfs(cur.right,newSum,targetSum);
        }
        // 把map里的key\value减一,这是一个回溯的过程，不能直接remove
        map.put(newSum,map.getOrDefault(newSum,0)-1);
    }
}
