package stringAbout;

import java.util.*;
import java.util.stream.Collectors;

public class BeforeAndAfterPuzzles_1181 {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String,List<String>> firstWordMap = new HashMap<>();
        Map<String,List<String>> lastWordMap = new HashMap<>();
        Map<String,Integer> lonlyStrings = new HashMap<>();

        for (String phrase : phrases) {
            String firstWord = findFirstWord(phrase);
            String lastWord = findLastWord(phrase);
            // 发现孤独phrase
            if(firstWord.equals(phrase)){
                if(lonlyStrings.containsKey(phrase)){
                    lonlyStrings.replace(phrase,lonlyStrings.get(phrase)+1);
                }else {
                    lonlyStrings.put(phrase,1);
                }
            }
            if(firstWordMap.containsKey(firstWord)){
                firstWordMap.get(firstWord).add(phrase);
            }else {
                List<String> t = new ArrayList<>();
                t.add(phrase);
                firstWordMap.put(firstWord,t);
            }
            if(lastWordMap.containsKey(lastWord)){
                lastWordMap.get(lastWord).add(phrase);
            }else {
                List<String> t = new ArrayList<>();
                t.add(phrase);
                lastWordMap.put(lastWord,t);
            }
        }
        List<String> res = new ArrayList<>();
        for (String lastWord : lastWordMap.keySet()) {
            if(firstWordMap.containsKey(lastWord)){
                List<String> originPhrases = lastWordMap.get(lastWord);
                for (String originPhrase : originPhrases) {
                    List<String> puzzleWords = firstWordMap.get(lastWord);
                    int len = lastWord.length();
                    for (String puzzleWord : puzzleWords) {
                        if(lonlyStrings.containsKey(puzzleWord) && lonlyStrings.containsKey(originPhrase)){
                            if(lonlyStrings.get(puzzleWord)<=1){
                                continue;
                            }
                        }
                        if(puzzleWord.equals(originPhrase) && !lonlyStrings.containsKey(puzzleWord)){
                            continue;
                        }
                        String substring = puzzleWord.substring(len);
                        res.add(originPhrase + substring);
                    }
                }
            }
        }
        List<String> ans = res.stream().distinct().sorted().collect(Collectors.toList());
        return ans;
    }
    private String findFirstWord(String phrase){
        int x = phrase.indexOf(' ');
        if(x==-1){
            return phrase;
        }
        String firstWord = phrase.substring(0,x);
        return firstWord;
    }

    private String findLastWord(String phrase){
        int x = phrase.lastIndexOf(' ');
        if(x==-1){
            return phrase;
        }
        return phrase.substring(x+1);
    }



    public static void main(String[] args) {
        BeforeAndAfterPuzzles_1181 p = new BeforeAndAfterPuzzles_1181();
        String[] test = new String[]{
                "writing code","code rocks"
        };
        p.beforeAndAfterPuzzles(test);
    }
}
