package erfen;

import java.util.Arrays;
import java.util.Collections;

public class HIndex_274 {
    public int hIndex(int[] citations) {
        int res = 0;
        int n = citations.length;
        Integer[] ci = new Integer[n];
        for (int i = 0; i < citations.length; i++) {
            ci[i] = citations[i];
        }


        Arrays.sort(ci, Collections.reverseOrder());
        int l = -1,r=n;
        while(l + 1 < r){
            int mid = l + ((r-l)>>1);
            if(citations[mid] < mid){
                l = mid;
            }else {
                r = mid;
            }
        }
        // l即为n-h，n-h即其右边的citation都满足，其引用次数citations【index】>=index
        // 即被引用次数大于自身等于的序号
        if(l==-1){
            return res;
        }
        while(l<n&&l!=citations[l]){
            ++l;
        }


        return res;
    }
}
