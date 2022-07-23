package everyDay;

public class SortUtil {
    public static void sort(int[] arr){
        quickSort(0, arr.length-1, arr);
    }
    public static void quickSort(int begin,int end,int[] arr){
        int left = begin;
        int right = end;
        if(left < right){
            int temp;
            temp = arr[begin];
            while(left < right){
                while(left < right && arr[right] >= temp) right--;
                arr[left] = arr[right];
                while (left < right && arr[left] <= temp) left++;
                arr[right] = arr[left];
            }
            arr[left] = temp;
            quickSort(begin,left-1,arr);
            quickSort(left+1,end,arr);
        }else
            return;
    }

    public static void mergeSort(int[] arr){
        int len = arr.length;
        int[] tmp = new int[len];
        partition(0,len-1,arr,tmp);

    }
    public static void merge(int begin,int end,int mid,int[] arr,int[] tmp ){
        int l = begin,r = mid+1;
        int tmpk = begin;
        while(l<=mid || r <= end) {
            if (l > mid) {
                tmp[tmpk] = arr[r];
                r++;
            } else if (r > end) {
                tmp[tmpk] = arr[l];
                l++;
            } else if (arr[l] <= arr[r]) {
                tmp[tmpk] = arr[l];
                l++;
            } else {
                tmp[tmpk] = arr[r];
                r++;
            }
            tmpk++;
//            if((l<=mid && arr[l] <= arr[r]) || (l<=mid && r > end) ){
//                tmp[tmpk] = arr[l];
//                l++;
//                tmpk++;
//            }else if(r <= end){
//                tmp[tmpk] = arr[r];
//                r++;
//                tmpk++;
//            }else {
//                break;
//            }
//        }
        }
            System.arraycopy(tmp, begin, arr, begin, end - begin + 1);
    }

    private static void partition(int begin, int end, int[] arr,int[] tmp) {
        if(begin >= end) {
            return;
        }
        int mid = begin + ((end - begin) >> 1);
        partition(begin,mid,arr, tmp);
        partition(mid+1,end,arr,tmp);
        merge(begin,end,mid,arr,tmp);
        return;
    }

    public static void main(String[] args) {
        int[] test = new int[] {
                5,9,6,3,7
        };
        SortUtil.mergeSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
