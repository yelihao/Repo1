package Lesson6;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 贪心算法 {

    /**
    会议问题：按哪个会议结束时间早，就安排哪个，然后把不能用的会议删掉
     */
    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //写一个比较器
    public static class ProgramComparetor implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            //按o1还是o2的结束时间end来由小到大安排
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs,int timePoint){
        Arrays.sort(programs,new ProgramComparetor());
        int result = 0;
        //遍历所有会议
        for (int i = 0;i<programs.length;i++){
            //若会议开始时间在上一个的结束时间之后
            if (timePoint <= programs[i].start){
                result++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }

    /*
    贪心算法在实现时，经常使用到的技巧：
            1.根据某标准建立一个比较器来排序
            2.根据某标准建立一个比较器来组成堆
     */

    /**
     * Q2金条问题：一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的俩半，都需要花费20个铜板。一群人
     *           想整分整块金条，怎么分最省铜板。
     * @param：数组arr[n1,n2,n3]:代表一共三个人，整块金条长度为n1+n2+n3,需要把n1,n2,n3分成这三部分
     */
    /*
    解决方法：准备一个小根堆，把数组塞入小根堆，每次弹出俩个数做结合，结合完毕后在塞入小根堆
     */
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i : arr) {
            pQ.add(i);
        }
        int sum = 0;    //用于记录和返回
        int cur = 0;    //用于弹出俩数的结合
        while (pQ.size()>1){    //只剩一个最终长度时，不弹出
            cur = pQ.poll() + pQ.poll();
            sum = sum + cur;
            pQ.add(cur);
        }
        return sum;
    }







}
