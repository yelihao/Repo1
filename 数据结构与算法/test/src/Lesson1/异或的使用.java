package Lesson1;

public class 异或的使用 {

    /*
    前提：a和b在内存中是俩块独立区域
    优势：省内存空间
    a：10110
    b：00111
    a^b=10001
    异或运算：（理解1）相同为0 不同为1
            （理解2）无进位相加
             性质：（1）0^N=N  N^N=0
                  （2）满足交换律和结合律
     如  a=甲 b=乙
        a=a^b;          a=甲^乙  b=乙
        b=a^b;          a=甲^乙  b=甲^乙^乙=甲^0=甲
        a=a^b;          a=甲^乙^甲=0^乙=乙 b=甲   交换完成
     */
    public static void swap(int[]arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /*
    问：在一个数组中有一种数出现了奇数次，其余数均出现偶数次，找出这个数字(f(N)=o(N))
     */
    public static int findOne(int[]arr){
        int eor = 0;
        for (int i : arr) {
            eor = eor^i;
        }
        return eor;
    }



    /*
    问2：在一个数组中有两种数出现了奇数次，其余数均出现偶数次，找出这个数字
     */
    public static int[] findTwo(int[]arr){
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        //此时eor = a^b   切eor！=0
        //eor二进制数中必然有一个位置不同
        //我们找到最右侧的不同的1
        /*
        如   eor:1010111100
        取反 ~eor:0101000011
            ~eor+1:0101000100
         eor & (~eor+1):0000000100
         */
        int rightOne = eor & (~eor + 1 );   //&相同才为1 不同就为0

        int onlyOne = 0;    //eor'
        for (int cur : arr) {
            if ((cur & rightOne) == 0){   //最右边二进制的数为1的都加入异或套餐，即可找到a
                onlyOne ^=cur;
            }
        }
        eor = eor^onlyOne;
        int[] arr2 = {eor,onlyOne};
        return arr2;
    }


}
