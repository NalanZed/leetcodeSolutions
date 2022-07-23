package matrix;

import java.util.HashMap;
import java.util.Map;

//稀疏矩阵的乘法
public class Multiply_311 {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int[][] res = new int[mat1.length][mat2[0].length];
        Map<int[],Integer> mat1Nums = new HashMap<>();
        Map<int[],Integer> mat2Nums = new HashMap<>();
        /*
            使用map存储非0值
            int[]存储坐标，value为非0值
            用map的好处是，它含有是否为0的语义
            最后再模拟矩阵乘法
            i0 * 0j + i1 * 1j +...+ ik * kj = C(i,j)

         */
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                if(mat1[i][j] != 0){
                    mat1Nums.put(new int[]{i,j},mat1[i][j]);
                }
            }
        }
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                if(mat2[i][j] != 0){
                    mat2Nums.put(new int[]{i,j},mat2[i][j]);
                }
            }
        }
        //纵坐标决定 乘以 mat2的哪一行
        //横坐标决定，要将结果记录在哪个位置
        for(int[] key:mat1Nums.keySet()){
            int i = key[0]; int j = key[1];
            int value = mat1Nums.get(key);
            for (int k = 0; k < mat2[0].length; k++) {
                int[] muti = new int[]{j,k};
                if(mat2Nums.containsKey(muti)){
                    res[i][k] += value * mat2Nums.get(muti);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        HashMap<Integer,Integer> map1 = new HashMap<>();
        Integer a = 155;
        map1.put(a,1);
    }
}
