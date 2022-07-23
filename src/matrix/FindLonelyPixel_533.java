package matrix;

import java.util.ArrayList;
import java.util.List;

public class FindLonelyPixel_533 {
    public int findBlackPixel(char[][] picture, int target) {
        List<String> rowStrings = new ArrayList<>();
        int m = picture.length;
        int n = picture[0].length;
        int[] rowCounter = new int[m];
        int[] colCounter = new int[n];
        List<int[]> blacks = new ArrayList<>();

        // 状态压缩，先把每行转换成字符串，并按照相同的分类
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0;j < n;j++){
                if(picture[i][j] == 'B'){
                    blacks.add(new int[]{i,j});
                    rowCounter[i]++;
                    colCounter[j]++;
                }
                sb.append(picture[i][j]);
            }
            rowStrings.add(sb.toString());
        }

        // 对每一列遍历
        for (int i = 0; i < n; i++) {
            if(colCounter[i]!=target){
                continue;
            }



        }




        return 0;
    }
}
