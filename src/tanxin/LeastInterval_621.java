package tanxin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeastInterval_621 {
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        char[] count = new char[26];
        for (int i = 0; i < len; i++) {
            count[tasks[i]  - 'A']++;
        }
        Arrays.sort(count);

        int maxLen = (count[25]-1) * (n+1) + 1;

        for (int i = 24; i >= 0; --i) {
            if(count[i] == count[25]){
                ++maxLen;
            }
            if(count[i] == 0){
                break;
            }
        }
        return Math.max(len,maxLen);
    }
}
