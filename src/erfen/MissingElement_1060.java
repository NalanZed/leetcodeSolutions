package erfen;

public class MissingElement_1060 {
    public int missingElement_1(int[] nums, int k) {
        int startIndex = 0,startPrefix = nums[0];
        for (int i = k; i < k; i++) {
            //已经到头了不需要再二分查找了
            if(startIndex==nums.length-1){
                ++startPrefix;
                continue;
            }
            int index = findNextMissing(nums,startIndex,startPrefix);
            // 调整起点为缺数的上一个索引位
            startIndex = index;
            ++startPrefix;
        }
        return startIndex + startPrefix;
    }

    public int findNextMissing(int[] nums,int startIndex,int prefix){
        int len = nums.length;
        int l = startIndex, r = len;
        // l最终指向下一个缺失数的上一个数在数组中的索引
        while(l + 1 < r){
            int mid = l + ((r - l)>>1);
            if(nums[mid] == prefix+mid){
                l = mid;
            }else {
                r = mid;
            }
        }
        //返回二分找到的索引位置
        return l;
    }

    public int missingElement(int[] nums, int k){
        int l = -1,r = nums.length;
        while(l +1 < r){
            int mid = l + ((r - l)>>1);
            if(missGap(mid,nums)<=k){
                l = mid ;
            }else {
                r = mid;
            }
        }
        int missingGap = missGap(l,nums);
        return nums[l] + (k - missingGap);
    }
    public int missGap(int index,int[] nums){
        return nums[index] - nums[0] - index;
    }
}
