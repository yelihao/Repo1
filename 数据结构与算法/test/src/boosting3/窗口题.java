package boosting3;

import java.util.LinkedList;

public class 窗口题 {

    /**
     * Q1:
     * 有一个整形数组arr和一个大小为w的窗口从数组最左边滑到最右边，每一次滑一个位置
     * 如果数组长度为n，窗口大小为w，则一共产生 n-w+1 个窗口的最大值
     * 请实现一个函数：输入：整形数组arr,窗口大小w
     *              输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
     */
    /*
    双端队列(可以从头部进节点也可以从头部出节点，尾部也可进可出)   ----- 放数组下标     O(N)
        STEP1：R往右动的时候，双端队列更新方法
            每次都按大值放入尾部，则头部每次出来的都是最大值
            若从尾部放进去的数值较上一个大，则把尾部数据扔出来，且永远不找回，直到可以放入合适位置
        STEP2：L往右动，双端队列更新方法：
            看过期位置是不是双端队列头部位置，是则从头部弹出
     */
    /*
    解法：先让R优先走w步，然后r走一步，l走一步
     */
    //针对此题的数据结构
    public static class WindowMax{
        private int L;
        private int R;
        private int[] arr;  //arr[ [L...R) ]
        //记录下标
        private LinkedList<Integer> qmax;

        public WindowMax(int[]a){
            arr = a;
            L = -1;
            R = 0;
            qmax = new LinkedList<>();
        }


        public void addNumFromRight(){
            if (R == arr.length){return;}
            //R移动，把尾部比他小的都永远弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                qmax.pollLast();
            }
            qmax.addLast(R);
            R++;
        }

        public void removeNumFromLeft(){
            if (L >= R-1){
                return;
            }
            L++;
            //最大值的下标 已经被左边界L越过了
            if (qmax.peekFirst() == L){
                qmax.pollFirst();
            }
        }

        public Integer getMax(){
            if (!qmax.isEmpty()){
                return arr[qmax.pollFirst()];
            }
            return null;
        }
    }

    //针对这道题的解法
    public static int[] getMaxWindow(int[] arr,int w){
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }
        //下标  值 由大 —> 小
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0;i < arr.length; i++){
            //i -> arr[i]
            //从尾部找到合适位置，比arr[i]小的全部倒出去
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            //加入
            qmax.addLast(i);
            if(qmax.peekFirst() == i - w){  //i - w过期下标，从头倒掉
                qmax.pollFirst();
            }
            if (i >= w -1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }



    /**
     * Q2:单调栈问题：求数组中每一个位置，左边最近比自己大的数，右边最近比自己大的数
     */
    /*
    解法：单调栈(无重复值)：
            准备一个栈，每次压入一个下标，必须保证栈内最里面是最大，然后单调递减的顺序，
            如果新入栈的比最外面栈数要大，则弹出此下标，并且可以生成此下标信息：左边比此数大的是这个数在栈内下一个将要弹出的数，右边
                                                                   比此数大的下标是把此数弹出栈的那个位置
            如果没有东西可以进栈，则直接进入清算阶段，右侧比他大的数都是null，左侧则照搬STEP2
         有重复值：则重复值下标压在一起
     */

    /**
     * Q2的应用
     * def:数组中累积和最小值的乘积，假设叫做指标A
     * 给定一个正数数组，请返回子数组中，指标A最大的值
     */





}
