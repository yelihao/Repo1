package Lesson3;

public class 奇数排序 {

    //桶排序，几进制准备几个桶
    //only for no-negative value
    public static void radixSort(int []arr){
        if (arr == null || arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    //找到最大值以及他的digit
    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max != 0){
            res++;
            max /= 10;
        }
        return res;
    }

    //digit 表示最大的数，有几个十进制位
    public static void radixSort(int[] arr,int l,int r,int digit){
        final int radix = 10;
        int i = 0,j = 0;
        //有多少个数，准备多少辅助空间
        int[] bucket = new int[r-l+1];
        for (int d = 1;d <= digit;d++){ //有多少位就进出多少次
            //10个空间
            //count[0] 当前位（d位）是0的数字有多少个
            //count[1] 当前位（d位）是0和1的数字有多少个
            //count[i] 当前位（d位）是0~i的数字有多少个
            int[] count = new int[radix];   //count[0```9]
            for (i=l;i<=r;i++){
                j=getDigit(arr[i],d);
                count[j]++;
            }
            for (i =1;i<radix;i++){
                count[i] = count[i] + count[i-1];
            }
            for (i=r;i>=l;i--){
                j=getDigit(arr[i],d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }
            for (i=l,j=0;i<=r;i++,j++){
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x,int d){
        return ((x/((int)Math.pow(10,d-1)))%10);
    }







}
