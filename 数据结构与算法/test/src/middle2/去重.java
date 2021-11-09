package middle2;

import java.util.Arrays;
import java.util.HashSet;

public class 去重 {
    /*
    给定一个数组arr，求差值为k的去重数值对
     */
    //把没个数记录到哈希表，在遍历数组，去哈希表中找差值为k的值在不在


    /**
     * Q2：有arr1和arr2，要从其中一个中拿出一个数到另一个，使俩个数组的平均值都提高
     * 问：最多拿几次？
     */
    /*
    从arr1（均值较大的）中拿出小于均值最小的数，到arr2（均值较小的数组）
     */
    //请保证arr1无重复值，arr2无重复值，arr1和arr2肯定有数字
    public static int maxOps(int[] arr1,int[] arr2){
        double sum1 = 0;
        for (int i = 0;i < arr1.length;i++){
            sum1 += (double) arr1[i];
        }
        double sum2 = 0;
        for (int i = 0;i < arr2.length;i++){
            sum2 += (double) arr2[i];
        }
        //如果俩个均值相等，则无
        if (avg(sum1,arr1.length) == avg(sum2,arr2.length)){
            return 0;
        }
        //均值不等
        //以下用于重定义大小arr
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        if (avg(sum1,arr1.length) > avg(sum2,arr2.length)){
            arrMore = arr1;
            sumMore = sum1;
            arrLess = arr2;
            sumLess = sum2;
        }else {
            arrMore = arr2;
            sumMore = sum2;
            arrLess = arr1;
            sumLess = sum1;
        }
        Arrays.sort(arrMore);
        HashSet<Integer> setLess = new HashSet<>();
        for (int num : arrLess){
            setLess.add(num);
        }
        int moreSize = arrMore.length;  //平均值大的集合还剩几个数
        int lessSize = arrLess.length;  //平均值小的集合还剩几个数
        int ops = 0;    //操作了几次
        for (int i = 0;i < arrMore.length;i++){ //小 -> 大
            double cur = (double) arrMore[i];
            if (cur < avg(sumMore,moreSize)
                    && cur > avg(sumLess,lessSize)
                    && !setLess.contains(arrMore[i])){
                sumMore -= cur;
                moreSize--;
                sumLess += cur;
                lessSize++;
                setLess.add(arrMore[i]);
                ops++;
            }
        }
        return ops;
    }
    public static double avg(double sum,int length){
        return sum/(double) length;
    }



}
