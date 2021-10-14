package boosting5;

public class 位运算题 {

    /**
     * Q1:给定俩个有符号32位整数a和b，返回a和b中较大的
     *      要求:不能做任何比较判断
     */
     /*
     解法1：互斥思想
      */
    //请保证参数n，不是1就是0的情况下
    //1 -> 0
    //0 -> 1
    public static int flip(int n){
        //^ 一样则为0， A^0则为A
        return n ^ 1;
    }

    //n是非负数，返回1
    //n是负数，返回0
    public static int sign(int n){
        /*
        0&0=0;  0&1=0;   1&0=0;    1&1=1
        n >> 31:n这个数向右移动31位，则n这个数的符号位就移动到了最右边的位置
        正数第一位为0，负数第一位为1
        & 1：非负数得到0，负数得到1
         */
        return flip((n >> 31) & 1);
    }

    //问题：a-b 可能溢出
    public static int getMax1(int a,int b){
        int c = a - b;
        int scA = sign(c);  //a-b为负，scA为0；a-b为正，scA为1
        int scB = flip(scA);    //scA为0，scB为1；scA为1，则scB为0
        //scA为0,scB必为1；scA为1，scB必为0
        return a * scA + b * scB;
    }

    //没问题方式
    public static int getMax2(int a,int b){
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;       //a和b的符号不一样为1;一样为0
        int sameSab = flip(difSab); //a和b的符号一样为1;不一样为0
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return  a * returnA + b * returnB;
    }

    /**
     * Q2:判断一个32位正数，是不是2的幂、4的幂
     * 2的幂：二进制状态中只有一个1 -----  拿到一个数最右侧的1，然后和原来的数比较，如果相等，则就只有一个1
     *                         -----  或者 x & (x-1) == 0
     * 4的幂：STEP1：判断x是否只有一个1
     *      Step2： 把x & (010101010101.....01) != 0 是4的某个幂   (因为四的幂只可能在1 100 10000 这样)
     */
    public static boolean is2Pow(int n){
        return (n & (n-1)) == 0;
    }

    public static boolean is4Pow(int n){
                                        //01010101....0101
        return (n & (n-1)) == 0 && (n & 0x55555555) != 0;
    }


    /**
     * Q3:给定两个有符号32位整数a和b，不能使用算数运算符，分别实现a和b的加，减，乘，除运算
     */
    //^:无进位相加
    //&：可以得到哪些位置要进位        --向左移动一位，即得到进位信息
    //以上俩个信息在无进位相加
    // 一直重复，直到进位信息 全0
    public static int add(int a,int b){
        int sum = a;
        while (b != 0){
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    //减法就是a+ b的相反数
    public static int negNum(int n){
        return add(~n,1);
    }
    public static int minus(int a,int b){
        return add(a,negNum(b));
    }







}
