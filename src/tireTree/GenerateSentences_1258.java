package tireTree;

import java.text.Collator;
import java.util.*;

class TireTool{
    TireTool[] children;
    boolean isEnd;

    public TireTool() {
        this.children = new TireTool[26];
        this.isEnd = false;
    }

    public void insert(String word){
        int len = word.length();
        TireTool tool = this;
        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if(tool.children[index]==null){
                TireTool newNode = new TireTool();
                tool.children[index] = newNode;

            }
            tool = tool.children[index];
        }
        tool.isEnd = true;
    }
    //
    public boolean findWord(String word){
        int len = word.length();
        TireTool tool = this;
        for (int i = 0; i < len; i++) {
            int index = word.charAt(i) - 'a';
            if(tool.children[index] == null){
                return false;
            }
            tool = tool.children[index];
        }
        return tool.isEnd;
    }
}



public class GenerateSentences_1258 {
    List<String> res = new ArrayList<>();
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        TireTool tool = new TireTool();
        //存储无向图,同时插入字典树
        Map<String,List<String>> groph = new HashMap<>();
        for (int i = 0; i < synonyms.size(); i++) {
            String word1 = synonyms.get(i).get(0);
            String word2 = synonyms.get(i).get(1);
            if(groph.containsKey(word1)){
                groph.get(word1).add(word2);
            }else {
                List<String> ss = new ArrayList<>();
                ss.add(word2);
                groph.put(word1,ss);
            }

            if(groph.containsKey(word2)){
                groph.get(word2).add(word1);
            }else {
                List<String> ss = new ArrayList<>();
                ss.add(word1);
                groph.put(word2,ss);
            }
        }
        // 近义词插入字典树
        for (String word : groph.keySet()) {
            tool.insert(word);
        }
        //将text转换成一系列单词

        String[] words = text.split(" ");
        words[0] = "i";
        for (String word : words) {
            boolean exist = tool.findWord(word);
            if(exist){
                List<String> similars = groph.get(word);
                for (String similar : similars) {
                   res.add(text.replaceAll(word,similar)) ;
                }
            }
        } 
        // 字典序排序
        return res;
    }

    public static void main(String[] args) {
        TireTool tool = new TireTool();
        List<List<String>> synonyms = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        tmp.add("happy");tmp.add("joy");synonyms.add(new ArrayList<>(tmp));
        tmp.clear();
        tmp.add("sad");tmp.add("sorrow");synonyms.add(new ArrayList<>(tmp));
        tmp.clear();
        tmp.add("joy");tmp.add("cheerful");synonyms.add(new ArrayList<>(tmp));
        tmp.clear();
        String text = "I am happy today but was sad yesterday";
        GenerateSentences_1258 p = new GenerateSentences_1258();
        p.generateSentences(synonyms,text);
    }
}
