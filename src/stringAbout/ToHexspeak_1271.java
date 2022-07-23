package stringAbout;

import java.util.HashSet;
import java.util.Set;

public class ToHexspeak_1271 {
    public String toHexspeak(String num) {
        Long numL = Long.valueOf(num);
        /*
        数字转16进制
         */
        char[] bits = new char[]{
                'O','I','2','3','4','5','6','7',
                '8','9','A','B','C','D','E','F'
        };
        Set<Character> leagal = new HashSet<>();
        leagal.add('A');leagal.add('B');leagal.add('C');leagal.add('D');
        leagal.add('E');leagal.add('F');leagal.add('I');leagal.add('O');
        StringBuilder hexNumBuilder = new StringBuilder();
        while(numL !=0){
            int index = (int) (numL % 16);
            if(!leagal.contains(bits[index])){
                return "ERROR";
            }
            hexNumBuilder.append(bits[index]);
            numL = numL / 16;
        }
        return hexNumBuilder.reverse().toString();
    }
}
