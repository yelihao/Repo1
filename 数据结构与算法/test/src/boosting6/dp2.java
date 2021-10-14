package boosting6;

public class dp2 {

    /**
     * Q2:给定一个正数数组arr，给定一个参数n，要求返回数组中任意几个数之和正好等于n的最小数量
     */
    //最初版本
    public static int minCoins1(int[] arr,int aim){
        return process(arr,0,aim);
    }

    //arr[index ....]组成出rest这么多钱，最少的硬币数返回
    public static int process(int[]arr,int index,int rest){
        if (rest < 0){return -1;}
        if (rest == 0){
            return 0;
        }
            //没有硬币了
            if (index == arr.length){
                return -1;
            }
            //还有钱
            //p1和p2可能为-1
            int p1 = process(arr,index+1,rest);
            int p2Next = process(arr,index+1,rest-arr[index]);
            if (p1 == -1 && p2Next==-1){
                return -1;
            }else if (p1 == -1 && p2Next!=-1){
                return 1+p2Next;
            }else if (p1 != -1 &&p2Next == -1){
                return p1;
            }
            //第一种选择是不用当前硬币，第二种选择是选择当前硬币，取较小值
            return Math.min(process(arr,index+1,rest),
                    1+process(arr,index+1,rest-arr[index]));
    }

    //表版本
    public static int minCoins2(int[] arr,int aim){
        int [][]dp = new int[arr.length+1][aim+1];

        for (int i =0;i<=arr.length;i++){
            for (int j = 0;j<=aim;j++){
                dp[i][j] = -2;
            }
        }

        return process2(arr,0,aim,dp);
    }

    //arr[index ....]组成出rest这么多钱，最少的硬币数返回
    public static int process2(int[] arr, int index, int rest, int[][] dp){
        if (rest < 0){return -1;}

        if (dp[index][rest]!=-2){
            return dp[index][rest];
        }
        if (rest == 0){
            dp[index][rest] = 0;
            return 0;
        }
        //没有硬币了
        if (index == arr.length){
            dp[index][rest] = -1;
            return -1;
        }
        //还有钱
        //p1和p2可能为-1
        int p1 = process(arr,index+1,rest);
        int p2Next = process(arr,index+1,rest-arr[index]);
        if (p1 == -1 && p2Next==-1){
            dp[index][rest] = -1;
        }else if (p1 == -1 && p2Next!=-1){
            dp[index][rest] = 1+p2Next;
        }else if (p1 != -1 &&p2Next == -1){
            dp[index][rest] = p1;
        }
        //第一种选择是不用当前硬币，第二种选择是选择当前硬币，取较小值
        dp[index][rest] = Math.min(process(arr,index+1,rest),
                1+process(arr,index+1,rest-arr[index]));
        return dp[index][rest];
    }
}
