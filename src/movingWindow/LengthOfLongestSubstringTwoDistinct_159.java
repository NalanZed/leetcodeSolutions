package movingWindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringTwoDistinct_159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // 初始化
        char char1 = s.charAt(0);
        char char2;
        //记录最大长度
        // 初始化
        int index = 0;
        while (char1 == s.charAt(index)){
            ++index;
            // 说明根本没有第二个字符，直接返回总长度
            if(index==s.length()){
                return s.length();
            }
        }
        char2 = s.charAt(index);
        // 到此为止，char1 和char 2 分别记录了前两个不同的字符
        //因此此时的长度就是此时的索引+1
        int curNum = ++index;
        // 初始化最大值
        int maxNum = 0;
        while (index<s.length()){
            // 还没有找到新字符
            if(char1 == s.charAt(index) || char2 == s.charAt(index)){
                ++curNum;
                ++index;
                continue;
            }
            // 找到新字符了
            // 先更新最大长度
            maxNum = Math.max(maxNum,curNum);
            curNum = 1;
            char newchar = s.charAt(index);
            char lastchar = s.charAt(index-1);
            int tmpIndex = index-1;
            // 往回倒搜索,找到lastchar的长度
            while(s.charAt(tmpIndex) == lastchar){
                ++curNum;
                tmpIndex--;
            }
            // 继续循环
            char1 = newchar;
            char2 = lastchar;
            ++index;
        }
        maxNum =Math.max(curNum,maxNum);
        return maxNum;
    }
}
