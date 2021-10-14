package boosting3;

public class Manacher {
    /*
    字符串str中，最长回文子串长度如何求解
     */
    //一般解法都不是很容易即在原字符串数组中，头和尾 以及没俩个之间加入特殊字符，最后在新字符串中，计算每一个位置的回文长度，其真实值等于其/2
    //时间复杂度O(N^2)

    //Manacher代码
    //加特殊字符
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        //让charArr 加入特殊字符
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0;i != res.length;i++){
            res[i] = (i & 1) == 0? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s){
        if (s == null || s.length() == 0){return 0;}
        char[] str = manacherString(s);     //1221 -> #1#2#2#1#
        int[] pArr = new int[str.length];   //回文半径数组
        int C = -1; //刚开始的C设为-1
        int R = -1; //回文右边界的再往右一个位置  最右的有效区是R-1位置
        int max = Integer.MIN_VALUE;    //扩出来的最大值
        for (int i = 0; i != str.length; i++){      //每一个位置都求回文半径
            //i至少的回文区域，献给pArr[i]
            /*
            1）i在R外，则其至少的回文区域是1
            2) i在R内，R-i就是半径，2 * C - i就是i'的位置
             */
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                } else{
                    break;
                }
            }
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max - 1;

    }



}
