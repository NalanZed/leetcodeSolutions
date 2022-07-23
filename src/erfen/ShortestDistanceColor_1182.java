package erfen;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceColor_1182 {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        /*
        思路，因为同色索引必然是递增的，可以用二分查找
        我们把三种颜色的所有索引分类到三个数组中
        然后直接是一个简单的二分查找完成
         */
        int[][] map = new int[3][colors.length];
        // 记录三种颜色分别的长度
        int color1Len=0,color2Len=0,color3Len=0;
        for (int i = 0; i < colors.length; i++) {
            // 存储颜色索引
            if(colors[i]==1){
                map[0][color1Len] = i;
                ++color1Len;
            }else if(colors[i]==2){
                map[1][color2Len] = i;
                ++color2Len;
            }else {
                map[2][color3Len] = i;
                ++color3Len;
            }
        }
        //填充哨兵值
        map[0][color1Len] = Integer.MAX_VALUE;
        map[1][color2Len] = Integer.MAX_VALUE;
        map[2][color3Len] = Integer.MAX_VALUE;


        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int color = queries[i][1] - 1;
            result.add(binarySearch(map[color],queries[i][0],color1Len));
        }
        return result;
    }
    // 传递一个一维数组，一个target，一个数组长度，返回最接近target的值
    public int binarySearch(int[] array,int target,int arrayLen){
        int l = -1, r = arrayLen;
        while(l - 1 < r){
            int mid = l + ((r-l)>>1);
            if(array[mid]<=target){
                l = mid;
            }else {
                r = mid;
            }
        }
        int ans = 0;
        // l不等于-1，说明存在小于等于target的值，这样就返回相差绝对值最小的那个数
        return  ans =  (l==-1) ? array[r] : Math.min(Math.abs(array[l]-target),Math.abs(array[r]-target));
    }

}
