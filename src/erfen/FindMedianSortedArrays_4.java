package erfen;

// 困难题，寻找两个正序数组的中位数
public class FindMedianSortedArrays_4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double res = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;

        boolean isOdd = (len1 + len2) % 2 == 1 ? true : false;
        //锁定我们要找的数的次序，即中位数是第几个数？偶数时要求其与其下一个数的平均数
        int theMid = (len1+len2+1)/2 - 1;
        //两个指针
        int point1 = 0;
        int point2 = 0;

        // k为我们每次二分循环时，要找的新次序
        int k = theMid;

        while(point1 < len1 || point2 < len2 || k!=0){
            if(point1 == len1){
                point2 = point2 - k/2;
            }


        }









        return res;
    }
}
