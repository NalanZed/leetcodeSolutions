package stringAbout;

public class Encode_1256 {
    public String encode(int num) {
        return new StringBuilder(Integer.toBinaryString(++num)).deleteCharAt(0).toString();
    }
}
