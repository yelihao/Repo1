package Lesson3;

import java.util.PriorityQueue;

public class 堆排序拓展 {
    /*
    已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的化，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的排序
    算法针对这个数组进行排序
     */
    //可以使用小根堆，每一次的heapSize为k+1
    //每一次小根堆都能排好头一个位置的数
    //当heapSize碰到arr.length时，heapSize--
    public void sortedArrDistanceLessK(int[]arr,int k){
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (;index <= Math.min(arr.length,k);index++){
            heap.add(arr[index]);
        }
        int i = 0;
        for (;index<arr.length;i++,index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }

    }




}
