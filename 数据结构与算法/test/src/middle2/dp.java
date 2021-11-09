package middle2;

public class dp {

    /**
     * Q1：给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int process(int n){
        if (n < 0){
            return 0;
        }
        if (n == 0 && n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        int res = 0;
        for (int leftNum = 0; leftNum < n - 1;leftNum++){
            int leftWays = process(leftNum);
            int rightWays = process(n - 1 - leftNum);
            res = res + leftWays * rightWays;
        }
        return res;
    }

    /**
    1-26转为a-z，求一个str中数字，能转化为多少种这种abc
     */
    /*
    dp思路
     */
    public static int process(char[] str,int index){
        if (index == str.length) return 1;
        /*
        index及其后续还有数字字符
         */
        if (str[index] == 0)return 0;
        /*
        有俩种选择，一种是自己单独成字母，二是和后面俩个（加一起小于26）组成字母
         */
        int res = process(str,index+1);
        if (index == str.length){
            return res; //到最后了
        }
        if(((str[index] - '0') * 10 + str[index + 1] - '0') < 27){
            res += process(str,index+2);
        }
        return res;
    }

    //改动态规划
    public static int dpways(int num){
        if (num < 1){
            return 0;
        }
        char [] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int [] dp = new int[N+1];
        dp[N] = 1;
        dp[N - 1] = str[N-1] == '0'?0 : 1;
        for (int i = N - 2;i >= 0; i--){
            if (str[i] == '0'){
                dp[i] = 0;
            }else {
                dp[i] = dp[i+1]
                        + (((str[i]-'0') * 10 + str[i + 1] - '0') < 27? dp[i+2]:0);
            }
        }
        return dp[0];
    }


    /**
     * 二叉树每个节点都有一个int类型权值，给定一颗二叉树，要求计算出从跟节点到叶节点所有路径种，权制和最大的值的多少
     */



}
