package backTracking;
import java.util.ArrayList;
import java.util.List;

public class TeleNumberAndCharacters {

    private String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<String>();
        if(digits.equals("")) {
            return res;
        }

        findCombination(digits, 0, new StringBuilder());
        return res;
    }

    private void findCombination(String digits, int index, StringBuilder s){

        if(index == digits.length()){
            res.add(s.toString());
            return;
        }

        Character c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        //由于length在本题中可变，所以多了前面的步骤
        for(int i = 0 ; i < letters.length() ; i ++){
            //每一层的s都是一样的，s不可变
            findCombination(digits, index+1, s.append(letters.charAt(i)));
            s.deleteCharAt(index);
        }

        return;
    }


    public static void main(String[] args) {
        String digits = "23";
        TeleNumberAndCharacters teleNumberAndCharacters = new TeleNumberAndCharacters();
        List<String> strings = teleNumberAndCharacters.letterCombinations(digits);
        System.out.println("strings = " + strings);
    }
}
