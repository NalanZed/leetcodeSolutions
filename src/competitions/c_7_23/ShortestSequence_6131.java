package competitions.c_7_23;

import java.util.HashSet;
import java.util.Set;

public class ShortestSequence_6131 {
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int roll : rolls) {
            set.add(roll);
            if(set.size()==k){
                ans++;
                set.clear();
            }
        }
        return ans+1;
    }
}
