package movingWindow;

import java.util.*;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，
 * 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 */
public class ContainsNearbyAlmostDuplicate_220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //怎么快速的找到差值绝对值呢
        //最小差值绝对值
        if(k == 0){
            return false;
        }
        LinkedList<Integer> window = new LinkedList<>();
        for(int i =0 ; i<nums.length;i++){
            window.addLast(nums[i]);
            // window的最大size是k+1,此时最大索引为k + x,最小索引为x
            if(window.size()>k+1){
                window.removeFirst();
            }
            if(sortAndCheck(window, t)){
                return true;
            }
        }
        return false;
    }

    public boolean sortAndCheck(LinkedList<Integer> list,int t){
        if(list.size()<=1) {
            return false;
        }

        int target = list.getLast();
        int[] arr = list.stream().mapToInt(i -> i).toArray(); //[1, 2, 3, 4]
        Arrays.sort(arr);
//        for(int i = 0;i<arr.length-1;i++){
//            if((arr[i+1] >=t || arr[i] >=t) && (arr[i+1]<0 || arr[i]<0)){
//                continue;
//            }
//            int gap = Math.abs(arr[i+1]-arr[i]);
//            if(gap <= t){
//                return true;
//            }
//        }
        //排序后使用二分法查找最近的一个元素
        int l=-1,r=list.size();
        while(l+1<r){
            int mid = l + ((r - l)>>1);
            if(arr[mid]<=target){
                l = mid;
            }else {
                r = mid;
            }
        }
        //l!=-1，说明有<=target的数，最近的一个是 arr[l]
        //r!=list.size(),有比target大的数，最近一个是arr[r]

        if(l!=-1){
           if((arr[l-1] >=t || target>=t) && (arr[l-1]<0 || target<0)){
               return false;
            }else {
               if(Math.abs(target - arr[l-1]) <= t){
                   return true;
               }
           }
        }
        if(r!=list.size()){
            if((arr[r] >=t || target>=t) && (arr[r]<0 || target<0)){
                return false;
            }else {
                if(Math.abs(target - arr[r]) <= t){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate_standard(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long u = nums[i] * 1L;
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t) {
                return true;
            }
            if(r != null && r - u <= t) {
                return true;
            }
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k) {
                ts.remove(nums[i - k] * 1L);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate_my(int[] nums, int k, int t) {
        if(k == 0){
            return false;
        }
        TreeSet<Long> window = new TreeSet<>();
        for(int i = 0;i< nums.length;i++){
            Long l = window.ceiling(nums[i]*1L);
            Long r = window.floor(nums[i]*1L);
            if(l != null && Math.abs(l-nums[i]) <= t){
                return true;
            }
            if(r != null && Math.abs(r-nums[i]) <= t){
                return true;
            }
            window.add(nums[i] * 1L);
            if(window.size() > k){
                window.remove(nums[i-k] * 1L);
            }
        }
        return false;
    }



    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate_220 p = new ContainsNearbyAlmostDuplicate_220();
        int[] test = {
                -2147483648,2147483647
        };
        System.out.println(p.containsNearbyAlmostDuplicate(test, 1, 1));
    }
}