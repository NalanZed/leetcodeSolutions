package doublePointer;

import java.util.ArrayList;
import java.util.List;

public class FindRLEArray_1868 {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> res = new ArrayList<>();
        /*
            没有必要真的将encode1 和encode2 都展开
            只要确保他们的值能够全都被处理并存储即可
         */
        List<Integer> tmp = new ArrayList<>();
        // 初始化双指针
        int p1 = 0,p2 = 0;
        int lastNum=encoded1[0][0] * encoded2[0][0];
        int currentNum=0;
        int count = 0;
        while (p1 < encoded1.length && p2 < encoded2.length){
            //这样每次循环时，之前的已经被计算的次数都会被刷新
            //直接在原数组上改！
            //先选数字
            int factor1 = encoded1[p1][0];
            int factor2 = encoded2[p2][0];
            // 得到当前sum,及其count
            currentNum = factor1 * factor2;
            if(currentNum != lastNum){
                // 出现不同的数字，将之前的数加入到res中
                // 更新tmp
                tmp.add(lastNum);
                tmp.add(count);
                res.add(new ArrayList<>(tmp));
                lastNum = currentNum;
                count = 0;
                tmp.clear();
            }
            count += Math.min(encoded1[p1][1], encoded2[p2][1]);
            // 移动，免去不必要的计算
            if(encoded1[p1][1]<encoded2[p2][1]){
                encoded2[p2][1] -= encoded1[p1][1];
                ++p1;
            }else if(encoded1[p1][1]>encoded2[p2][1]){
                encoded1[p1][1] -= encoded2[p2][1];
                ++p2;
            }else {
                ++p1;
                ++p2;
            }
        }
        tmp.add(currentNum);
        tmp.add(count);
        res.add(new ArrayList<>(tmp));
        return res;
    }
}
