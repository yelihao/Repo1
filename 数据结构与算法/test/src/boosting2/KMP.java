package boosting2;

public class KMP {

    /**
     * KMP算法解决的问题:
     *  字符串str1和str2,str1是否包含str2，如果包含返回str2在str1中开始的位置.
     * 如何在时间复杂度O(N)完成？
     */
    /*
    解法1：暴力解法 ---  尝试str1每一个开头能否配出str2
    解法2：KMP
           K:一个字符，前缀和后缀相等的最大长度，不能取到整体。对Str2求的，对str2中每一个元素之前的元素求K，得一个数组，第一个位置默认-1
                    如：[a a b a a b s a a b]  K:[-1 0 1 0 1 2 3 0 0 0 0]
             哪里断掉，就看这个字符之前，str2的最大的前后缀匹配长度，然后跳掉匹配前缀的后一个，str1接着往下移动
     */
    public static int getIndexOf(String s,String m){
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);        //O(M)
        //O(N)
        while (i1 < str1.length && i2 < str2.length){
            //如果都相等，则索引均后移
            if (str1[i1] == str2[i2]){
                i1++;
                i2++;
            }else if (i2 == 0){
                //已经str1[i]和str2中任何一个为头的都不匹配时候，则i1++
                i1++;
            }else {
                //i2跳到前缀的下一个字符位置上
                i2 = next[i2];
            }
        }
        //i1越界或者i2越界了
        return i2 == str2.length ? i1 - i2 : -1;
    }
    public static int[] getNextArray(char[] ms){
        if (ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1; //人为规定
        next[1] = 0;    //因为第一个前后缀的长度都等于本身，不能算
        int i = 2;  //next数组的需要计算的第一个位置
        int cn = 0;     //cn 是你拿哪个位置的字符和i-1的字符比(前缀的字符长度)     //也代表你当前使用的信息值(前缀的长度)
        while(i < next.length){
            // 该值的前一个值 等于前缀的下一个字符，则该值为前一个值的前缀基础上加1
            if (ms[i - 1] == ms[cn]){
                next[i++] = ++cn;   //下一步要求i的下一个位置 所以i++
                                    //更新cn+1的值
            }
            //当前跳到cn位置的字符，和i-1未知的字符配不上
            //cn跳到next的前缀的位置的下一个 也就是next[cn]    //重新匹配ms[i - 1] 和 ms[cn]
            else if (cn > 0){
                cn = next[cn];
            }else {
                //跳到最前面了
                next[i++] = 0;
            }
        }
        return next;
    }




}
