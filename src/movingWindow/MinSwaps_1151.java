package movingWindow;

public class MinSwaps_1151 {
    public int minSwaps(int[] data) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        int maxOnes = 0;

        for (int i = 0; i < sum; i++) {
            maxOnes += data[i];
        }
        int l = 0,r = sum;

        int tempOnes = maxOnes;
        while(r<data.length){
            tempOnes = tempOnes - data[l] + data[r];
            maxOnes = Math.max(tempOnes,maxOnes);
            tempOnes = maxOnes;
            l++;r++;
        }
        return sum - maxOnes;
    }
}
