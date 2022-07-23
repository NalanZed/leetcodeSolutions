/*
    字节原题
    给定一个数字字符串，求它的阶乘，返回字符串
 */
public class Factorial {
    public String factorial(String num){
        String nextNum = nextNum(num);
        // 两数相乘时，更大的那个数
        String BigNum = num;
        while(!nextNum.equals("0")){
            //每个个位相乘后累加起来的和
            //直到最后一位完成，这个和就是完整的一次两数相乘的总和
            String partResult = "0";
            int numLen = nextNum.length();
            for (int i = 0; i < numLen; i++) {
                int powLevel = numLen - i - 1;
                int factor = nextNum.charAt(i) - '0';
                String AProduct = numMultiABit(BigNum,factor);
                // 乘上幂级
                String proAfterPowTen = powTen(powLevel,AProduct);
                //与前面的累加和相加
                partResult = numsPlus(partResult,proAfterPowTen);
            }
            // 把上次的乘积，作为下一次计算的大数
            BigNum = partResult;
            nextNum = nextNum(nextNum);
        }
        return BigNum;
    }


    //测试通过
    //模拟一个0-9的数字与num相乘，返回字符串结果
    private String numMultiABit(String num,int factor){
        if(factor==0){
            return "0";
        }
        // 模拟进位
        Integer carry = 0;
        Integer lowBit;
        StringBuilder numBuilder = new StringBuilder(num);
        for (int i = num.length()-1; i>=0; --i) {
            int charToNum = numBuilder.charAt(i) - '0';
            // 两位乘积，加上之前的进位
            int product = charToNum * factor + carry;
            // 获取新的低位 和 进位
            lowBit = product % 10;
            carry = product / 10;
            //把低位替换到numBuilder中
            numBuilder.replace(i,i+1,lowBit.toString());
        }
        //处理最后的进位，直接加在字符串的最前端
        if(carry!=0){
            numBuilder.insert(0,carry.toString());
        }
        return numBuilder.toString();
    }

    //测试通过
    //模拟两个字符串相加
    private String numsPlus(String num1,String num2){
        StringBuilder reslutBuilder = new StringBuilder();
        Integer carry = 0;
        Integer lowBit;
        int len1 = num1.length();
        int len2 = num2.length();
        for(int p1 = len1-1,p2 = len2 - 1; p1>=0 && p2>=0;--p1,--p2){
            //从个位到最高位不断求和
            int add1 = num1.charAt(p1) - '0';
            int add2 = num2.charAt(p2) - '0';
            int sum = add1 + add2 + carry;
            // 求得进位与低位
            carry = sum / 10;
            lowBit = sum % 10;
            //resultBuilder收集结果
            reslutBuilder.insert(0,lowBit.toString());
        }
        //最后长度更长的还剩一些位数
        String lastNum = len1 > len2 ? num1 : num2;
        int lastBits = len1 > len2 ? len1 - len2 : len2 - len1;
        for (int i = lastBits-1; i >=0 ; --i) {
            int add = lastNum.charAt(i) - '0';
            int sum = add + carry;
            // 求得进位与低位
            carry = sum / 10;
            lowBit = sum % 10;
            //resultBuilder收集结果
            reslutBuilder.insert(0,lowBit);
        }
        //捕捉最后可能的进位
        if(carry!=0){
            reslutBuilder.insert(0,carry);
        }
        return reslutBuilder.toString();
    }


    //获取下一个因子
    private String nextNum(String num){
        StringBuilder nextBuilder = new StringBuilder(num);
        for (int i = num.length()-1; i >= 0; --i) {
            if(num.charAt(i) == '0'){
                nextBuilder.replace(i,i+1,"9");
            }else {
                Integer newBit = num.charAt(i) - '1';
                nextBuilder.replace(i,i+1,newBit.toString());
                break;
            }
        }
        //避免前置0,只有一位0可以允许
        if(nextBuilder.charAt(0) == '0' && nextBuilder.length() != 1){
            nextBuilder.deleteCharAt(0);
        }
        return nextBuilder.toString();
    }

    // 补充幂级
    private String powTen(int powLevel,String num){
        StringBuilder result = new StringBuilder(num);
        while(powLevel>0){
            result.append('0');
            powLevel--;
        }
        return result.toString();
    }


    public static void main(String[] args) {
        Factorial p = new Factorial();

        String factorial = p.factorial("100");
        System.out.println("阶乘长度==="+factorial.length());
        System.out.println("100的阶乘为==="+ factorial);

//        System.out.println("2的阶乘为==="+ p.factorial("2"));
//        System.out.println("3的阶乘为==="+ p.factorial("3"));
//        System.out.println("4的阶乘为==="+ p.factorial("4"));
//        System.out.println("5的阶乘为==="+ p.factorial("5"));
//        System.out.println("6的阶乘为==="+ p.factorial("6"));
    }
}
