package matrix;

public class Rotate_48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n==1){
            return;
        }
        /*
            从内层到外层一层一层地换
            因为内外互不影响
            所以实现起来几乎一样
         */
        // 计算最内层的 n
        // 忽略奇数n时的核心层
        int level = n % 2 == 0 ? 2 : 3;
        // 先找最内层起点
        int innerX = (n / 2)-1;
        int innerY = innerX;
        int tmpLast;
        int tmpNull;
        // 两层循环，一层控制层级，一层控制旋转
        for(int i = level,count = 0;i<=n;i=i+2,count++){
            // 确定某一层的起始点
            int startX = innerX-count;
            int startY = innerY-count;
            //for循环改变同一层内的起始位置,同时控制循环次数
            for (int j = i-1; j >0; j--) {
                //记录起始点，当四次换位后回到原为即可终止。
                int oldX = startX;
                int oldY = startY;
                tmpLast = matrix[startX][startY];
                do{
                    int nextX = startY;
                    int nextY = n - startX - 1;
                    tmpNull = matrix[nextX][nextY];
                    matrix[nextX][nextY] =tmpLast;
                    startX = nextX;
                    startY = nextY;
                    tmpLast = tmpNull;
                }while(startX!=oldX || startY!=oldY);
                ++startX;
            }
        }
    }
}
