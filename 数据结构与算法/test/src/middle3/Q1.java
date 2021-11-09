package middle3;

public class Q1 {

    /**
     * Q1：
     * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批物品放到每个打包机上，放到每个机器上的这些物品数量有多有少，由于物品
     * 数量不相同，需要工人将每个机器上的物品进行移动从而达到物品数量相等才能打包。每个物品重量太大、每次只能搬一个物品进行移动，为了
     * 省力，只在相邻的机器上移动。请计算在搬运最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1。
     */
    /*
    例如：[1,0,5]表示有3个机器，每个机器上有1、0、5个物品，经过这些轮后
    第一轮 1 0 <- 5 => 1 1 4  第二轮 1 <- 1 <- 4 => 2 1 3     第三轮 2 1 <- 3 => 2 2 2
    移动了3轮，每个机器上的物品相等，所以返回3
    例如[2 2 3] 怎么移动都不能物品相等，所以返回-1
     */
    /*
    1：先求累加和，看看能不能被n整除

    2：中间位置来计算：
            s1：左右区域皆负，则a要操作至少 abs(左)+abs(右)
            s2：左右区域皆正，则a至少操作max{abs(左),abs(右)}
            s3：左负右正，则a至少需要操作max{abs(左),abs(右)}
      把每个位置的瓶颈算出来，最痛的瓶颈就是答案
     */
    public static int MinOps(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0;i < size; i++){
            sum += arr[i];
        }
        if (sum % size != 0){return -1;}

        int avg = sum / size;   //每个位置的目标
        int leftSum = 0;
        int ans = 0;
        for (int i = 0;i < arr.length;i++){
            //负 需要输入  正 需要输出
            int leftRest = leftSum - i * avg;
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0){
                ans = Math.max(ans,Math.abs(leftRest)+Math.abs(rightRest));
            }else {
                ans = Math.max(ans,Math.max(Math.abs(leftRest),Math.abs(rightRest)));
            }
        }
        return ans;
    }




}
