package doublePointer;

public class MaxArea_11 {
    public int maxArea(int[] height) {
        /*
          所谓的 maxArea 其实就是
          求两个点 [i,iheight] , [j,jheight]
            最大的 （j - i） * ( Math.min(iheight,jheight) )

            用双指针，一个指在0 ， 一个指在 length-1
            这样，使得初始 （j - i）最大了
            这时候，就要尽量取 更大的height
            在向内移动指针的时候，毫无疑问 （j - i ）要减小
            所以我们优先移动 height 比较小的那边的指针，并且遇到更大的height时计算 此时的容积
            取过程中最大的值
         */
        int left = 0,right = height.length-1;
        int res = 0;
        while(left<right){
            int tmpVolume = calcuVolume(height,left,right);
            res = Math.max(res,tmpVolume);
            // 高度更小的向内移动
            // 因为（j - i）在移动时必然减少，所以遇到height 比之前还少时，不用计算了，直接下一个
            boolean isLeftBigger = (height[left]>height[right]);
            int lastHeight = isLeftBigger ? height[right] : height[left];
            int lastIndex = isLeftBigger ? right-1 : left+1;
            while((lastIndex<right && lastIndex > left) && height[lastIndex] <= lastHeight) {
                if(isLeftBigger){
                    --lastIndex;
                }else {
                    ++lastIndex;
                }
            }
            if(isLeftBigger) right = lastIndex;
            else left = lastHeight;
        }
        return res;
    }
    private int calcuVolume(int[] height,int i,int j){
        int len = j- i;
        int hei = Math.min(height[i],height[j]);
        return len*hei;
    }
}
