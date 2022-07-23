package matrix;

import java.util.ArrayList;
import java.util.List;

public class FindLonelyPixel_531 {
    // 四个方向

    public int findLonelyPixel(char[][] picture) {
        /*
            记录每一行、每一列的B的个数

         */
        int res = 0;
        int[] rowCount = new int[picture.length];
        int[] cloumCount = new int[picture[0].length];
        List<int[]> blacks = new ArrayList<>();

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length;j++){
                if(picture[i][j] == 'B'){
                    // 发现B，则对应行、列记录B数量+1
                    rowCount[i]++;
                    cloumCount[j]++;
                    // 记录B的位置集合
                    blacks.add(new int[]{i,j});
                }
            }
        }
        for (int[] black : blacks) {
            if(rowCount[black[0]] == cloumCount[black[1]] && rowCount[black[0]] == 1){
                res++;
            }
        }
        return res;
    }
}
