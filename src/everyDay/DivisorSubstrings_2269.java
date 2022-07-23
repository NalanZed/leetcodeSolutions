package everyDay;

/**
 * 一个整数 num 的 k 美丽值定义为 num 中符合以下条件的 子字符串 数目：
 *     子字符串长度为 k 。
 *     子字符串能整除 num 。
 * 给你整数 num 和 k ，请你返回 num 的 k 美丽值。
 * 注意
 *     允许有 前缀 0 。
 *     0 不能整除任何值。
 * 一个 子字符串 是一个字符串里的连续一段字符序列。
 */

public class DivisorSubstrings_2269 {
    public int divisorSubstrings(int num, int k) {
        String numString = String.valueOf(num);
        int oBit = numString.length();
        int window = 0;
        int beautifulCount = 0;
        for (int i = 0,j = k-1; i < k; i++,j--) {
            int gap = numString.charAt(i) - '0';
            window += (gap * Math.pow(10,j));
        }
        for (int i = k; i <oBit ; i++) {
            if(window!= 0 && num%window == 0){
                ++beautifulCount;
            }
            if(window!= 0){
                window %= Math.pow(10,k-1);
                window *= 10;
            }
            int nextGap = numString.charAt(i) - '0';
            window += nextGap;
        }
        if(window!= 0 && num%window == 0){
            ++beautifulCount;
        }

        return beautifulCount;
    }

    public static void main(String[] args) {
        int test = 430043;
        Integer.parseInt("632",10);
        DivisorSubstrings_2269 p = new DivisorSubstrings_2269();
        System.out.println(p.divisorSubstrings(10, 1));
    }
}
