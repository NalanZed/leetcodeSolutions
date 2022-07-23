package doublePointer;

import java.util.Arrays;

public class GetModifiedArray_370 {

    /**
     *  差分思想
     *  可参考连接
     *  https://blog.csdn.net/weixin_51472673/article/details/122495868?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165761560316781683972387%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165761560316781683972387&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-122495868-null-null.142^v32^pc_rank_34,185^v2^control&utm_term=%E5%89%8D%E7%BC%80%E5%92%8C%E4%B8%8E%E5%B7%AE%E5%88%86%E7%9A%84%E6%80%9D%E6%83%B3&spm=1018.2226.3001.4187
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        int start;
        int end;
        int addition;
        for (int i = 0; i < updates.length; i++) {
            start = updates[i][0];
            end = updates[i][1];
            addition = updates[i][2];

            res[start] += addition;
            if(end<length-1){
                res[end] -= addition;
            }
        }
        for(int i=1;i<res.length;i++){
            res[i] += res[i-1];
        }

        return res;
    }
}
