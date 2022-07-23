package backTracking.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PartitonCut {



    List<List<String>> resultSet = new ArrayList<>();

    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        int[][] flags = new int[chars.length][chars.length];
        backTracking(chars,0,new ArrayList<String>(),flags);
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                System.out.print(flags[i][j]+"###");
            }
            System.out.println("-------");
        }
        return resultSet;
    }
    void backTracking(char[] chars,int startIndex,List<String> path,int[][] flags){
        if(startIndex == chars.length){
            resultSet.add(new ArrayList<>(path));
        }
        for(int i = startIndex;i<chars.length;i++){
            if(isPalindrome(chars,startIndex,i,flags) == -1) {
                continue;
            }
            path.add(new String(chars,startIndex,i-startIndex+1));
            backTracking(chars,i+1,path,flags);
            path.remove(path.size()-1);
        }
    }

    //递归判断字符串是否为回文字符串
    int isPalindrome(char[] chars,int leftIndex,int rightIndex,int[][] flags){
        if (flags[leftIndex][rightIndex] != 0) {
            return flags[leftIndex][rightIndex];
        }
        if (leftIndex >= rightIndex) {
            flags[leftIndex][rightIndex] = 1;
        } else if (chars[leftIndex]==chars[rightIndex]) {
            flags[leftIndex][rightIndex] = isPalindrome(chars,leftIndex+1,rightIndex-1,flags);
        } else {
            flags[leftIndex][rightIndex] = -1;
        }
        return flags[leftIndex][rightIndex];
    }



//        if(flags[leftIndex][rightIndex]==-1){
//            return false;
//        }
//        if(flags[leftIndex][rightIndex] == 1){
//            return true;
//        }
//        if(leftIndex>=rightIndex){
//            return true;
//        }
//        if(chars[leftIndex]!=chars[rightIndex]){
//            return false;
//        }
//        return isPalindrome(chars,leftIndex+1,rightIndex-1,flags);
    public static void main(String[] args) {
        PartitonCut partitonCut = new PartitonCut();
        String s = "acca";
        List<List<String>> partition = partitonCut.partition(s);
        System.out.println("partition = " + partition);

//        boolean palindrome = partitonCut.isPalindrome(chars,0,chars.length-1);
//        System.out.println("palindrome = " + palindrome);
    }
}
