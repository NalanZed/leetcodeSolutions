package cyc;

import everyDay.Palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    //两个行程编码数组的积
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int id1 = 0;
        int id2 = 0;
        int len1 = encoded1.length;
        int len2 = encoded2.length;
        while (id1 != len1 && id2 != len2){
            temp.clear();
            //数量相等
            if (encoded1[id1][1] == encoded2[id2][1]){
                temp.add((encoded1[id1][0] * encoded2[id2][0]));
                temp.add(encoded1[id1][1]);
                id1++;
                id2++;
            } else if (encoded1[id1][1] < encoded2[id2][1]) {  //小于
                temp.add((encoded1[id1][0] * encoded2[id2][0]));
                temp.add(encoded1[id1][1]);
                encoded2[id1][1] = encoded2[id1][1] - encoded1[id1][1];
                id1++;
            } else if (encoded1[id1][1] > encoded2[id2][1]) {   //大于
                temp.add((encoded1[id1][0] * encoded2[id2][0]));
                temp.add(encoded2[id1][1]);
                encoded1[id1][1] = encoded1[id1][1] - encoded2[id1][1];
                id2++;
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
}
