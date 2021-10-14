package boosting6;

public class 动态规划 {

    /**
     * Q1:机器人运动问题
     *  给你一个参数int n，代表你有n个位置    >1
     *   第二个参数int s，开始位置    1-n
     *           int e 目标位置     1-n
     *           int k 机器人必须走k步，可以随意往左往右走
     *  问题：s到达e，必须走k步的过程中，共有几种走的方法
     *  */
    //主函数
    public static int walkWays1(int N,int E,int S,int K){
        return f1(N,E,K,S);
    }

    /**
     * @param N 一共1～N这么多位置  固定参数
     * @param E 最终目标E  固定参数
     * @param rest  还剩rest步需要走
     * @param cur   当前在cur位置
     * @return      返回方法数量
     */
    public static int f1(int N,int E,int rest,int cur){
        if (rest == 0){
            return cur == E ? 1 : 0;
        }
        //rest>0，则还有路可以走
        if (cur == 1){
            //只能往右走 1->2
            f1(N,E,rest-1,2);
        }
        if (cur == N){
            //走到最右边的N位置，只能往左走
            f1(N,E,rest-1,N-1);
        }
        //机器人来到的是中间位置
        return f1(N,E,rest-1,cur-1) + f1(N,E,rest-1,cur+1);
        //缺点：会浪费大量时间计算重复的点
    }

    //STPE2：记忆化搜索方法版本
    public static int walkWays2(int N,int E,int S,int K){
        int [][] dp = new int[K+1][N+1];
        for (int i = 0;i<=K;i++){
            for (int j = 0;j <= N ;j++){
                dp[i][j] = -1;
            }
        }
        return f2(N,E,K,S,dp);
    }
    public static int f2(int N, int E, int rest, int cur, int[][] dp){
        //计算过
        if (dp[rest][cur] != -1){
            return dp[rest][cur];
        }
        //没缓存过
        if (rest == 0){
            dp[rest][cur] = cur == E ?1:0;
            return cur == E ? 1 : 0;
        }
        //rest>0，则还有路可以走
        if (cur == 1){
            //只能往右走 1->2
            dp[rest][cur] =  f2(N,E,rest-1,2,dp);
            return  f2(N,E,rest-1,2,dp);
        }
        else if (cur == N){
            //走到最右边的N位置，只能往左走
            dp[rest][cur] = f2(N,E,rest-1,2,dp );
            f2(N,E,rest-1,N-1,dp);
        }else {
        //机器人来到的是中间位置
        dp[rest][cur] = f2(N,E,rest-1,cur-1,dp) + f2(N,E,rest-1,cur+1,dp);}
        return dp[rest][cur];
        //缺点：会浪费大量时间计算重复的点
    }

    //STEP3：严格表结构的动态规划DP版本






}
