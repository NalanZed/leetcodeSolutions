package everyDay;

class Solution {
    public int missingNumber(int[] arr) {
        /**
         * 可以利用题意：被删除的既不是第一个也不是最后一个，
         * 利用等差数列的特性迅速求出公差d，
         * 当d是0时，特殊处理直接返回arr[0]
         */
        int arrlen = arr.length;
        int an = arr[arrlen-1];
        int a1 = arr[0];
        int n = arrlen + 1;
        int d = (an - a1)/(n-1);
        // 公差为 0 特殊处理
        if(d == 0){
            return arr[0];
        }
        /**
         *
         * 有了公差后，最简单的办法就是，
         * 遍历，发现arr[i+1] - arr[i] ！= d时，返回arr[i] + d即可
         * 考虑到数组有序，可以利用二分法
         * 迅速找到arr[i],然后返回arr[i] + d
         */
        int i = 0, j = arrlen-1;
        while(i+1<j){
            int mid = i + (j-i)/2;
            if(arr[mid] - arr[i] != (mid - i)*d){
                j=mid;
            }else{
                i = mid;
            }
        }
        return arr[i]+d;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test = new int[]{
                2,2,2,2,2
        };
        System.out.println(solution.missingNumber(test));
    }


}

