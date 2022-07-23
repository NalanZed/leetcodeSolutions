package everyDay;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnagramMappings {
    /**
     * 怎么处理重复元素
     * 扫描B时，先不添加重复元素，这需要做一个used表记录使用情况
     * 当发现在A向B比对发现不存在在时，再去扫描used表，找到重复元素的新地址，记得改变used[]
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int len = nums2.length;
        int[] res = new int[len];
        Map<Integer,Integer> mapping = new HashMap<>();
        for (int i = 0; i < len; i++) {
            mapping.put(nums2[i],i);
        }
        for (int i = 0; i < len; i++) {
            res[i] = mapping.get(nums1[i]);
        }
        return res;
    }
    /**
     *给定已经按 升序 排列、由不同整数组成的数组 arr，返回满足 arr[i] == i
     * 的最小索引 i。如果不存在这样的 i，返回 -1
     * 二分
     * @param arr
     * @return
     */
    public int fixedPoint(int[] arr) {
        int len = arr.length;
        int l = -1 , r = len;
        while(l + 1 < r){
            int mid = l + ((r - l)>>1);
            if(arr[mid] <= mid){
                l = mid;
            }else {
                r = mid;
            }
        }
        if(l == -1){
            return -1;
        }
        if(arr[l] != l){
            return -1;
        }
        for(int i = l-1; i>=0;i--){
            if(arr[i]==i){
                --l;
                continue;
            }
            break;
        }
        return l;
    }
    public static void main(String[] args) {
        AnagramMappings p = new AnagramMappings();
//        int[] arr = new int[]{
//                -10,-5,3,4,7,9
//
//        };
//        int i = p.fixedPoint(arr);
//        System.out.println(i);
        System.out.println(p.confusingNumber(9));

    }

    public boolean confusingNumber(int n) {
        String s = String.valueOf(n);
        int oBit = s.length();
        char[] chars = s.toCharArray();
        Set<Integer> dic = new HashSet<>();
        for (int i = 0; i < oBit; i++) {
            dic.add(chars[i] - '0');
        }
        if(dic.contains(2) || dic.contains(3) || dic.contains(4)
                || dic.contains(5)|| dic.contains(7)){
            return false;
        }
        StringBuilder sb = new StringBuilder(s);

        String reverseNum = sb.reverse().toString();
        if(dic.contains(6)){
            String r = reverseNum.replace('6', 'a');
            reverseNum = r;
        }
        if(dic.contains(9)){
            String r = reverseNum.replace('9', '6');
            reverseNum = r;
        }
        String a = reverseNum.replace('a', '9');
            reverseNum = a;
        if(reverseNum.equals(s)) {
            return false;
        }
        return true;
    }
}
