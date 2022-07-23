package stringAbout.easy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Permutation {
    public boolean CheckPermutation(String s1, String s2) {
        /**
         1. 先确保字典相同
         2. 确保字典中所有字出现次数相同
         */
        if(s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character,Integer> dic1 = new HashMap<Character,Integer>();
        for(char ch:s1.toCharArray()){
            if(dic1.get(ch) != null){
                Integer val = dic1.get(ch);
                int i = val + 1;
                dic1.put(ch,i);
                continue;
            }
            dic1.put(ch,1);
        }
        for(char ch:s2.toCharArray()){
            if(dic1.get(ch) == null) {
                return false;
            }
            Integer integer = dic1.get(ch);
            int i = integer-1;
            dic1.put(ch,i);
        }
        Iterator <Map.Entry< Character, Integer >> iterator = dic1.entrySet().iterator();
        while(iterator.hasNext()){
            if(iterator.next().getValue()!=0){
                return false;
            }

        }
        return true;

    }


    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        String s1 = "abs";
        String s2 = "aab";
        boolean b = permutation.CheckPermutation(s1, s2);
        System.out.println("b = " + b);
    }
}
