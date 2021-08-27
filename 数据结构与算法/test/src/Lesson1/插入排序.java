package Lesson1;

public class 插入排序 {


    //插入排序 比 选择排序和冒泡排序在某些好；因为其算法时间复杂度最差才是o(n^2)
    public static void insertionSort(int[]arr){
        if (arr == null||arr.length<2){
            return;
        }
        //0~0有序
        //0～i想有序
        for (int i =1;i<arr.length;i++){
            for (int j =i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }



    public static void swap(int[]arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    /*
    二分法时间复杂度：o(log2N) = o(logN) 简写
     */



}
