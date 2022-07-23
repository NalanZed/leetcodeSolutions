package everyDay;

import java.util.HashSet;
import java.util.Set;

/**
 * 回文系列
 *
 *
 */
public class Palindrome {

    public boolean isPalindrome(int x) {
        String num = String.valueOf(x);
        int len = num.length();
        if(len <= 1){
            return true;
        }
        char[] bits = num.toCharArray();
        for (int i = 0,j=len-1; i <= j; ++i,--j) {
            if(bits[i] == bits[j]){
                continue;
            }
            return false;
        }
        return  true;
    }

    /**
     * 求 大于等于 n 的最小素数且为回文
     *
     * 思路：
     *  先找回文，再判断是否为素数，因为素数消耗比较大
     *  为了保证是大于等于的最小数，从中间开始改
     *  比如 12310，要改成回文，12321，原来的数比这个小，所以产出第一个数123321
     *  如果12321不行，就改成12421、12521、12621...
     *  3->9不行
     *  改2，13031、14031、15...19031、13131、13231....
     *  3->9 都不行，开始改 2 .... 工作量还是有点大
     * @param n
     * @return
     */
    public int primePalindrome(int n) {

        if(n==1){
            return 2;
        }

        Set<Integer> forbi = new HashSet<>();
        forbi.add(2);
        forbi.add(4);
        forbi.add(5);
        forbi.add(6);
        forbi.add(8);

        String numString = String.valueOf(n);
        //第一次要另外判断
        boolean checkOk = firstCheck(n);
        int len = numString.length();
        // 位数是单还是双
        boolean isOdd = (len % 2 == 1) ? true : false;
        // 取回文根
        int mid = isOdd ? (len/2) : (len/2 -1);
        StringBuilder sb =new StringBuilder();
        StringBuilder sbMax =new StringBuilder();
        for(int i = 0; i<=mid; i++){
            sb.append(numString.charAt(i));
            sbMax.append('9');
        }
        String palinRoot = sb.toString();
        String paliMax = sbMax.toString();
        int numRoot = Integer.parseInt(palinRoot);
        int maxRoot = Integer.parseInt(paliMax);
        int max = changeToNextPalindrome(maxRoot,isOdd) + 1;
        if(!checkOk){
            ++numRoot;
        }
        for(int nums = numRoot;nums<=maxRoot;++nums){
            if(forbi.contains(paliMax.charAt(0))){
                int hBit = paliMax.length();
                int addNum = (int) Math.pow(10, hBit);
                nums += addNum;

            }
            int getPaliNum = changeToNextPalindrome(nums,isOdd);
            if(isPrime(getPaliNum)){
                return getPaliNum;
            }
        }
        return primePalindrome(max + 1);
    }

    // 判断数字是否为质数
    public boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 给一个回文根，造出这个回文根的回文字符串，返回int
     * @param num
     * @return
     */
    public int changeToNextPalindrome(int num,boolean isOdd){
        String s = String.valueOf(num);
        StringBuilder sb = new StringBuilder(s);
        StringBuilder ss = new StringBuilder(s);
        sb.reverse();
        String tmp = sb.toString();
        ss.append(tmp);
        if(isOdd){
            ss.deleteCharAt(ss.length()/2);
        }
        Integer pnum = Integer.valueOf(ss.toString());
        return pnum;
    }

    public boolean firstCheck(int num){
        String numString = String.valueOf(num);
        int len = numString.length();
        int midl = len/2 - 1; // len = 3 midl = 0  len = 4 midl = 1
        int midr = (len+1)/2; // len = 3 midr = 2  len = 4 midr = 2
        for(int i = midl, j = midr;i>=0 && j<len;i--,j++){
            //12345 2<4 check通过
            if(numString.charAt(i) - numString.charAt(j) < 0){
                return false;
            }else if(numString.charAt(i) - numString.charAt(j) > 0){
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
//        System.out.println(p.changeToNextPalindrome(123,true));
//        System.out.println(p.firstCheck(1211));
        System.out.println(p.primePalindrome(9999));
    }








}
