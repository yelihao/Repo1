package boosting2;

public class Lesson1 {

    /**
     * Q1:岛问题
     *      一个矩阵中只有0和1两种值,每个位置都可以和自己的上，下，左，右四个位置相连，如果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中
     *      有多少个岛？
     *   举例：001010
     *        111010
     *        100100
     *        000000
     *     有3个岛
     */
    /*
    用infect :感染过程，把这个位置的1能连成岛的全连改成2
    记录有几次感染过程即可
     时间复杂度为:O(N*M):其实每个位置主程序碰了一次，感染阶段最多被调用四次
     */
    public static int countIslands(int[][] m){
        if (m == null || m[0] == null){
            //无行或者无列
            return 0;
        }
        int N = m.length;   //行数
        int M = m[0].length ;//列数
        int res = 0;    //用于记录有几次感染过程
        for (int i = 0;i<N; i++){
            for (int j =0 ;j<M;j++){
                if (m[i][j] == 1){
                    res++;
                    infect(m,i,j,N,M);
                }
            }
        }
        return res;
    }

    public static void infect(int[][]m,int i,int j,int N, int M){
        if (i < 0 || i >= N || j < 0 || j>=M || m[i][j] != 1){
            return;
        }
        //i,j没越界，则四处扩散，并且当前位置为1
        m[i][j] = 2;
        infect(m,i+1,j,N,M);
        infect(m,i-1,j,N,M);
        infect(m,i,j+1,N,M);
        infect(m,i,j-1,N,M);
    }









}
