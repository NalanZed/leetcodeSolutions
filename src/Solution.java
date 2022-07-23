import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;



class Solution {
    static String s = "leetcode";
    static public char firstUniqChar(String s) {
        Map<Character,Boolean> dic = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for(char ch : chars){
            dic.put(ch,!dic.containsKey(ch));
        }
        for(Map.Entry<Character, Boolean> entry : dic.entrySet()) {
            if(entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar(s));
    }
}