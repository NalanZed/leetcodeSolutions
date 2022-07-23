//import everyDay.Palindrome;

//import everyDay.Palindrome;

import everyDay.Palindrome;
import wy.Solution;
public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int m = 3, n = 7;
        int i = s.uniquePaths(m, n);
        int ans = 28;
        System.out.println(i == ans);   // answer = 28
    }

}
