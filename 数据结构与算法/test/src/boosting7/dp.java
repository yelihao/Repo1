package boosting7;

public class dp {

    /**
     * Q:给你一个数组arr，都是正数且无重复值，一个位置的值代表一个面值的货币，选择一个货币，就可以选择其任意张
     *      给定一个aim，选择arr中的货币，问有多少种组成的方法
     */
    public static int way1(int[]arr,int aim){
        return process(arr,0,aim);
    }
    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length){
            return rest == 0? 1 : 0;
        }
        //arr[index] 0张 1张 不要超过rest的钱数
        int ways = 0;
        for (int zhang = 0;arr[index] * zhang <= rest; zhang++){
            ways = ways + process(arr,index+1,rest - arr[index] * zhang);
        }
        return ways;
    }


}
