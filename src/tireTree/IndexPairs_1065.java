package tireTree;

import java.util.ArrayList;
import java.util.List;

public class IndexPairs_1065 {
    class TireA{
        TireA[] children;
        boolean isEnd;
        public TireA(){
            this.children = new TireA[26];
            this.isEnd = false;
        }
        public void insertWord(String word){
            int l = word.length();
            TireA root = this;
            for (int i = 0; i < l; i++) {
                int index = word.charAt(i) - 'a';
                if(root.children[index]==null){
                    root.children[index] = new TireA();
                }
                root = root.children[index];
            }
            root.isEnd = true;
        }
    }

    public int[][] indexPairs(String text, String[] words) {
        TireA rootTire = new TireA();
        int len = text.length();

        List<List<Integer>> res = new ArrayList<>();

        for (String word : words) {
            rootTire.insertWord(word);
        }

        for (int i = 0; i < len; i++) {
            TireA rootTmp = rootTire;
            int gap = text.charAt(i) - 'a';
            int r = i;
            if(rootTmp.children[gap]==null){
                continue;
            }
            while (rootTmp.children[gap]!=null){
                r++;
                rootTmp = rootTmp.children[gap];

                if(rootTmp.isEnd){
                    List<Integer> t = new ArrayList<>();
                    t.add(i);t.add(r-1);
                    res.add(t);
                }
                if(r>=len){
                    break;
                }
                gap = text.charAt(r) - 'a';
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = res.get(i).get(0);
            ans[i][1] = res.get(i).get(1);
        }
        return ans;
    }

}
