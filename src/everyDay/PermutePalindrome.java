package everyDay;

import java.util.ArrayList;
import java.util.List;

public class PermutePalindrome {
    /**
     * 思路:
     *  a - z
     *  设置一个dic[26] 存储 a-z的个数
     *  如果原字符串长度为偶数，那么每个都必须为0
     *  如果原字符串长度为偶数，那么最多一个为1
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        // 初始值就是false
        boolean[] dic = new boolean[26];
        int len = s.length();
        // 判断长度是否为奇数
        boolean isOdd = (len & 1) == 1 ? true : false;
        int limitCount = 0;
        if(isOdd){
            ++limitCount;
        }
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            dic[index] = !dic[index];
        }
        for (int i = 0,count = 0; i < dic.length; i++) {
            if(dic[i]){
                ++count;
            }
            if(count > limitCount){
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串 s ，返回 其重新排列组合后可能构成的所有回文字符串，并去除重复的组合 。
     * 你可以按 任意顺序 返回答案。如果 s 不能形成任何回文排列时，则返回一个空列表。
     * 思路：
     * 找回文根
     * 记录好每个字符出现的次数
     * 出现2次的，可以直接用来排列组合
     * 出现一次的，必须作为中间
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {
        char[] dics = new char[]{
            'a','b','c','d','e','f',
            'g','h','i','j','k','l',
            'm','n','o','p','q','r',
            's','t','u','v','w','x','y','z'
        };

        List<String> res = new ArrayList<>();
        // 不能成，直接返回空列表
        if(!canPermutePalindrome(s)){
            return res;
        }
        // 能成，给出回文根
        // 怎么给呢，把recorde里超过2的部分，每减一次2，记录下一个字符
        int [] recordes = recordePermutePalindrome(s);
        // 用来存储奇数次出现的字符
        char theOnly = '0';
        // 存储回溯表
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < recordes.length; i++) {
            while(recordes[i] >= 2){
                charList.add(dics[i]);
                recordes[i] -= 2;
            }
            if(recordes[i] == 1){
                theOnly = dics[i];
            }
        }
        char[] chars = new char[charList.size()];
        for(int i = 0;i<charList.size();i++){
            chars[i] = charList.get(i);
        }
        boolean[] used = new boolean[chars.length];
        backTracing(res,new StringBuilder(),chars,theOnly,used);
        return res;
    }
    public void backTracing(List<String> res,StringBuilder path,char[] chars,char theOnly,boolean[] used) {
        int len = chars.length;

        //此时已经把所有字符取完
        if(path.length() == len) {
            //把它翻转后
            String spath = path.toString();
            String completion = completionTheRoot(spath,theOnly);
            res.add(completion);
            return;
        }
        for(int i = 0;i<len;i++){
            // 字符使用过，下一个
            if(used[i]){
                continue;
            }
            // 字符与上一个相同
            if(i >= 1 && (chars[i] == chars[i - 1]) && !used[i-1]) {
                 continue;
            }
            path.append(chars[i]);
            used[i] = true;
            backTracing(res,path,chars,theOnly,used);
            used[i] = false;
            path.deleteCharAt(path.length()-1);
        }
    }
    public String completionTheRoot(String root,char theOnly){
        StringBuilder builder = new StringBuilder(root);
        String reverseRoot = builder.reverse().toString();
        StringBuilder sb = new StringBuilder(root);
        sb.append(reverseRoot);
        if(theOnly != '0'){
            sb.insert(sb.length()/2,theOnly);
        }
        String completion = sb.toString();
        return completion;
    }
    public int[] recordePermutePalindrome(String s) {
        // 初始值就是false
        int[] recorde = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            ++recorde[index];
        }
        return recorde;
    }






    public static void main(String[] args) {
        PermutePalindrome p = new PermutePalindrome();
//        System.out.println(p.recordePermutePalindrome("carerac"));
//        System.out.println(p.completionTheRoot("acb",'a'));
        List<String> ans = p.generatePalindromes("aabb");
        int size = ans.size();

    }

}
