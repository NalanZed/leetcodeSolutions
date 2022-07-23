package doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum_15 {
    // 用回溯就上当啦

//    List<List<Integer>> res = new ArrayList<>();
//    public List<List<Integer>> threeSum(int[] nums) {
//        boolean[] used = new boolean[nums.length];
//        Arrays.sort(nums);
//        dfs(nums,0,0,new ArrayList<>(),used);
//        return  res;
//    }
//    private void dfs(int[] nums,int startIndex,int curSum,List<Integer> path,boolean[] used){
//
//        if(path.size() == 3){
//            if(curSum == 0 && !res.contains(path)){
//                res.add(new ArrayList<>(path));
//            }
//            return;
//        }
//
//        for (int i = startIndex; i < nums.length; i++) {
//            //防止一样的元组，阻止其在同一层选择同样的数
//            if(i > 1 && nums[i] == nums[i-1] && !used[i-1]){
//                continue;
//            }
//            // 即将加入的数使得sum > 0,那么此后的选择都可以剪掉
//            if(curSum + nums[i]>0){
//                break;
//            }
//             curSum = curSum + nums[i];
//             path.add(nums[i]);
//             used[i] = true;
//             dfs(nums,i+1,curSum,path,used);
//             curSum -= nums[i];
//             path.remove(path.size()-1);
//             used[i] = false;
//        }
//    }
//
//    public static void main(String[] args) {
//        ThreeSum_15 p = new ThreeSum_15();
//        int[] test = new int[]{
//                1,1,-2
//        };
//        p.threeSum(test);
//    }

   public List<List<Integer>> threeSum(int[] nums) {
       List<List<Integer>> result = new LinkedList<>();
       if(nums.length < 3) return result;
       Arrays.sort(nums);
       for(int i=0;i<nums.length;i++){
           if(nums[i] > 0) break;//起始位置为0，直接退出
           if(i != 0 && nums[i] == nums[i-1]) continue;//重复则跳过
           int left = i+1;
           int right = nums.length-1;
           int target = -nums[i];
           while(left < right){
               if(nums[left] + nums[right] < target){
                   left++;
               }else if(nums[left] + nums[right] > target){
                   right--;
               }else{
                   List<Integer> list = new LinkedList<>();
                   list.add(nums[i]);
                   list.add(nums[left]);
                   list.add(nums[right]);
                   result.add(list);
                   //处理重复的三元组
                   while(left < right && nums[left] == nums[left+1]) left++;
                   while(left < right && nums[right] == nums[right-1]) right--;
                   left++;
                   right--;
               }
           }
       }
       return result;
   }
}

