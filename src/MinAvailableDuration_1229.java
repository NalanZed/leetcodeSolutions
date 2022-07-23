import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinAvailableDuration_1229 {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        /*
            要产生可能的空间
            先排序，从最小的时间区域开始查
            不断地找两个元素的交集
             如果能容纳就说明可型
         */
        // 1 有len1个空闲时间
        int len1 = slots1.length;
        int len2 = slots2.length;
        Arrays.sort(slots1,( int[] a, int[] b) -> a[0]-b[0]);
        Arrays.sort(slots2,( int[] a, int[] b) -> a[0]-b[0]);
        List<Integer> res = new ArrayList<>();
        int p1 = 0,p2 = 0;
        boolean find = false;
        int slotMidLeft = 0,slotMidRight=0;
        while(p1<len1 && p2 < len2){
            slotMidLeft = Math.max(slots1[p1][0],slots2[p2][0]);
            slotMidRight = Math.min(slots1[p1][1],slots2[p2][1]);
            // [slotMidLeft,slotMidRight]是 slotp1 和 slotp2的交集
            //如果交集不能满足duration,那就要换下一个空闲时间来比较了
            // 右边界小的指针向下移动动
            if(slotMidRight - slotMidLeft < duration){
                if(slotMidRight == slots1[p1][1]){
                    ++p1;
                }else {
                    ++p2;
                }
            }else {
                find = true;
                break;
            }
        }
        if(find){
            res.add(slotMidLeft);
            res.add(slotMidLeft + duration);
        }
        return res;
    }
}
