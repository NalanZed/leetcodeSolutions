package tireTree;

class Tire{
    Tire[] children;
    boolean isEnd;
    public Tire(){
        this.children = new Tire[26];
        this.isEnd = false;
    }
    public void insert(String word){
        Tire node = this;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            if(node.children[index] == null){
                node.children[index] = new Tire();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    public void search(String word,int[] ans,int start){
        Tire node = this;
        int length = word.length();
        String str = word;
        int end = start;

        for (int i = start; i < length; i++) {
            char ch = str.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if(node == null){
                break;
            }
            if(node.isEnd){
                end = i + 1;
                continue;
            }
        }
        while (start<end){
            ans[start] = 1;
            start++;
        }
        return;
    }
}
// 加粗字体
public class BoldWords_758 {
    /*
        思路
        先找到所有可以加粗的字符串
        使用滑动窗口，匹配最大长度的，且完整的加粗字符串。
        问题：把所有可以加粗的字符串组合出来是一个比较困难的过程

         正解：建立字典树

     */

    public String boldWords(String[] words, String s) {
        Tire tire = new Tire();
        for(String str:words){
            tire.insert(str);
        }
        int length = s.length();
        int[] black = new int[length];
        for(int i = 0;i<length;i++){
            tire.search(s,black,i);
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean in = false;//标记是否在加粗字符串中
        // 将加粗字符串转换成字符串
        for (int i = 0; i < length; i++) {
            if(black[i] == 1 && !in){
                stringBuilder.append("<b>");
                in = true;
            }
            if(black[i] == 0 && in){
                stringBuilder.append("</b>");
                in = false;
            }
            stringBuilder.append(s.charAt(i));
        }
        if(black[length-1] == 1){
            stringBuilder.append("</b>");
        }
        return stringBuilder.toString();
    }
}
