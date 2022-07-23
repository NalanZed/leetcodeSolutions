package erfen;


public class MaximizeSweetness_1231 {
    public int maximizeSweetness(int[] sweetness, int k) {
        /**
         *  大概就是，要让每次切割尽可能靠近平均数
         *  再在里边找最小的
         */
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
        }
        int avg = sum/(k+1) + 1;
        int l = -1,r=avg;
        while(l + 1 < r){
            int mid = l + ((r - l)>>1);
            int returnMin = tryAndCheck(sweetness,mid,k);
            if(returnMin > 0){
                ans = returnMin;
                l = mid+1;
            }else {
                r = mid -1;
            }
        }
        return ans;
    }
    public int tryAndCheck(int[] sweetness,int minNum,int k){
        int tmpSum = 0;
        int count = 0;
        int returnMin = Integer.MAX_VALUE;
        for (int i = 0; i < sweetness.length; i++) {
            tmpSum += sweetness[i];
            if(tmpSum>=minNum){
                ++count;
                // 记录最小的块
                returnMin = Math.min(returnMin,tmpSum);
                tmpSum = 0;
            }
        }
        // 看看是不是能切K+1刀，能切出k+1刀，或者k+n刀，说明一定符合所有
        // 的块都大于等于minNUm
        if(count < k+1){
            returnMin = -1;
        }
        // 返回负数代表这次尝试失败了
        return returnMin;
    }
}
