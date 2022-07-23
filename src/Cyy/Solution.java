//package Cyy;
//
//import java.util.*;
//
//
//public class Solution {
//    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
//        int length1 = encoded1.length;
//        int length2 = encoded2.length;
//        if (length1 == 0){
//            return new ArrayList<>();
//        }
//        List<List<Integer>> list = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>();
//        for (int i = 1; i < length1;i++){
//            encoded1[i][1] += encoded1[i -1][1];
//        }
//        for (int i = 1; i < length2;i++){
//            encoded2[i][1] += encoded2[i -1][1];
//        }
//
//        int now = 0;
//        for (int i = 0, j = 0; i < length1 && j < length2;){
//            int k = 0;
//            int num = 0;
//            int min = Math. (encoded1[i][1] , encoded2[j][1]);
//            if (encoded1[i][1] > encoded2[j][1]){
//                k = encoded2[j][1] - now;
//                num = encoded1[i][0] * encoded2[j][0];
//                now+=k;
//                j++;
//            }else {
//                k = encoded1[i][1] - now;
//                num = encoded1[i][0] * encoded2[j][0];
//                now+=k;
//                if (encoded1[i][1] == encoded2[j][1]){
//                    j++;
//                }
//                i++;
//            }
//
//            if (list1.size() != 0 && list1.get(0) == num){//跟前面一个数相等时，在原数基础上加
//                list1.set(1,list1.get(1) + k);
//            }else {
//                if(list1.size() != 0){//若前一个未提交，则提交
//                    list.add(new ArrayList<>(list1));
//                    list1.clear();
//                }
//                list1.add(num);
//                list1.add(k);
//            }
//        }
//        list.add(new ArrayList<>(list1));
//        return list;
//    }
//}
