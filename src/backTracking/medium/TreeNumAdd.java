package backTracking.medium;

import java.util.Arrays;
import java.util.List;

public class TreeNumAdd {
    int ans = 0;
    public int findTarget(int[] arr,int target){
        Arrays.sort(arr);
        dfs(arr,0,0,target,0);
        return ans;
    }
    public void dfs( int[] nums, int path,int index,int tar,int count){
        // 剪枝,当发现超标时及时终止
        if(path >= tar){
            return;
        }
        if(count == 3){
            if(path < tar){
                ans = ans + 1;
            }
            return;
        }
        for(int i = index;i < nums.length-1;i++){
            if(count > 3) {
                break;
            }
            if(nums[i] >= tar){
                break;
            }
            dfs(nums,path + nums[i],i+1,tar,count+1);
        }
    }

    public static void main(String[] args) {
        int[] test = {30,30,31,30,30,30,30,30,30,3,3,0,30,3,3,3,3,3,3,0,30,3,1,3,3,3,3,1,3,3,13,1,30,1,30,3,13,0,31,3,3,1,3,30,3,13,0,31,3,3,0,31,30,3,13,0,1,1};
        int tar = 91;
        TreeNumAdd treeNumAdd = new TreeNumAdd();
        System.out.println(treeNumAdd.findTarget(test, tar));
    }

    public int findTarget2(int[] arr,int target){
        Arrays.sort(arr);
        int path = 0;
        int count = 0;
        for(int i = 0;i< arr.length-2;i++,path = 0){
            path += arr[i];
            if(3 * path >= target){
                break;
            }
            for (int j = i+1;j < arr.length;j++){
                if(path + (2 * arr[j]) >= target) break;
                path += arr[j];
                for(int k = j+1; k < arr.length;k++ ){
                    path += arr[k];
                    if(path >= target){
                        break;
                    }else{
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
