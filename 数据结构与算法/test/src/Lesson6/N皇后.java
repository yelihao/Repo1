package Lesson6;

public class N皇后 {

    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何俩个皇后不同行，不同列，也不在同一条斜线上。
     * 给定一个整数n，返回n皇后的摆法有多少种。
     * 例如：n=1，return 1
     *     n=2或3，2皇后和三皇后怎么摆都不行，返回0
     *     n=8，返回92
     */
    /*
    用单数组就可以表示棋盘了
    例如：record[i]:表示 第i行的皇后放在了第几列
     */
    public static int num1(int n){
        //此函数主要是用于设置空棋盘
        if (n<1){
            return 0;
        }
        int[] record = new int[n];
        return process1(0,record,n);
    }

    /**
     * record[0··i-1]的皇后，任何俩个皇后，一定不共行，不共列，不共斜线
     * @param i:目前来到了第i行
     *        record[0··i-1]:代表之前的行，放了的皇后位置
     *        n:代表整体一共有多少行
     * @return  返回N皇后摆法有多少种
     */
    //i从0开始到n递归法
    public static int process1(int i,int[] record,int n){
        //终止行----最后一行再往下一行的位置
        if (i == n){
            return 1;
        }   //到终止行依旧能满足任何两个皇后不共行，不共列，不共斜线，就返回1
        int res = 0;
        for (int j = 0;j < n;j++){  //当前行在i行，但是尝试i行所有的列  -> j
            //当前i行的皇后，放在j列，会不会和之前(0··i-1)的皇后共行，共列或者共斜线
            //如果是则，则认为是无效
            //如果不是，则认为是有效
            if (isValid(record,i,j)){
                record[i] = j;
                //去下一行放皇后
                res += process1(i+1,record,n);
            }
        }
        return res;
    }

    //record[0··i-1]你需要看,record[i···]不需要看
    //返回i行的皇后，放在第j列是否有效
    public static boolean isValid(int[] record,int i,int j){
        for (int k = 0;k < i;k++){      //之前的某个k行的皇后
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }


}
