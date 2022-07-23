package stringAbout.easy;

import java.util.LinkedList;
import java.util.List;

public class FindTheFirstUnique {
    public char firstUniqChar(String s) {
        if(s.length() == 0) {
            return ' ';
        }
        int[] count = new int[26];
        LinkedList<Character> chars = new LinkedList<>();
        for(char ch : s.toCharArray()){
            count[ch - 'a']++;
            if(!chars.contains(ch)){
                chars.addLast(ch);
            }
        }
        while(!chars.isEmpty()){
            char tmp = chars.pollFirst();
            if(count[tmp - 'a'] == 1){
                return tmp;
            }
        }
        return ' ';
    }
}
