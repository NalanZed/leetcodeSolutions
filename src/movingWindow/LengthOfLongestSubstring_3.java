package movingWindow;

import java.util.Arrays;

public class LengthOfLongestSubstring_3 {
    public int lengthOfLongestSubstring(String s) {
        int[] dic = new int[128];
        Arrays.fill(dic,-1);
        int start = 0,max = 0;
        int sameCharIndex;

        // 滑动窗口，i就是右边界，start是左边界。
        // end - start + 1 是当前长度
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);

            start = Math.max(dic[index] + 1,start);
            max = Math.max(i - start + 1,max);
            dic[index] = i;
        }
        return max;
    }
}
