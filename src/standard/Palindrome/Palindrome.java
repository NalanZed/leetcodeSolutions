package standard.Palindrome;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    public int primePalindrome(int N) {
        if (N <= 2) {
            return 2;
        }
        while (true) {
            int palindrome = nextPalindrome(N);
            if (isPrime(palindrome)) {
                return palindrome;
            }
            N = palindrome + 1;
        }
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

    // 查找下一个回文数，如果这个数字是递减的，例如654321，那么取这个数的前一半翻转再合并就行，654 456；
    // 但是也有数字如123456，如果翻转合并会得到123321，这个回文数小于N，所以需要判断该数字是否需要改动；
    // 以数字中间那一位为中轴依次向两边看，如果发现左边的数字大于右边，那么数字不需要改动；如果左边数字
    // 大于右边，则需要改动；如果等于，则继续向两边比对
    public boolean needChange(List<Integer> list) {
        int left = list.size() / 2 - 1;
        int right = (list.size() + 1) / 2;
        while (left >= 0 && right < list.size()) {
            if (list.get(left) > list.get(right)) {
                return false;
            } else if (list.get(left) < list.get(right)) {
                return true;
            }
            left--;
            right++;
        }
        return false;
    }

    public int nextPalindrome(int n) {
        List<Integer> list = new ArrayList<>();
        // 将数字存入arraylist
        while (n > 0) {
            list.add(0, n % 10);
            n /= 10;
        }

        //如果数字需要改动，那么将左边需要被翻转的数字+1
        if (needChange(list)) {
            int index = (list.size() + 1) / 2 - 1;
            int carry = 1;
            while (index >= 0 && carry > 0) {
                int val = list.get(index) + carry;
                carry = val / 10;
                val = val % 10;
                list.set(index--, val);
            }
        }
        // 如果第一位是偶数，那么下一个回文数一定以偶数结尾，肯定不是质数（如果N=2会直接返回，不会进入这个函数）,所以第一位数+1
        if (list.get(0) % 2 == 0) {
            list.set(0, list.get(0) + 1);
        }
        int temp = list.size() / 2;
        int result = 0;
        for (int i = 0; i < temp; ++i) {
            result *= 10;
            result += list.get(i);
        }

        // 如果N位数为基数
        if (list.size() % 2 != 0) {
            result *= 10;
            result += list.get(temp);
        }
        for (int i = 0; i < temp; ++i) {
            result *= 10;
            result += list.get(temp - 1 - i);
        }
        return result;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
//        p.nextPalindrome(123456);
        p.primePalindrome2(123456);
    }


    public int primePalindrome2(int n) {
        // cur len
        String numStr = n + "";
        int len = numStr.length();
        int start = n / (int)Math.pow(10, (int)len / 2);
        if(constructNum(start, len) < n){
            start++;
        }
        int end = (int)Math.pow(10, (int)(len + 1) / 2);
        for(int i = start; i < end; i++){
            int num = constructNum(i, len);
            if(check(num)){
                return num;
            }
        }
        // more len
        while(true){
            len++;
            start = (int)Math.pow(10, (int)(len - 1) / 2);
            end = start * 10;
            for(int i = start; i < end; i++){
                int num = constructNum(i, len);
                if(check(num)){
                    return num;
                }
            }
        }
    }
    // construct palindrome
    private int constructNum(int num, int len){
        int res = num;
        if((len & 1) == 1){
            for(int i = 1; i <= len / 2; i++){
                res = res * 10 + num / (int)Math.pow(10, i) % 10;
            }
        }else{
            for(int i = 1; i <= len / 2; i++){
                res = res * 10 + num / (int)Math.pow(10, i - 1) % 10;
            }
        }
        return res;
    }
    // check if num is prime
    private boolean check(int num){
        if(num == 1){
            return false;
        }
        for(int i = 2; i <= (int)Math.pow(num, 0.5); i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
