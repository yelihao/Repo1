package Lesson2;

import org.junit.Test;

public class 荷兰国旗问题 {
    /*
    问题一：给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。要求额外空间复杂度O(1)，时间复杂度O(N)
     */
    public void sort_q1(int[]arr,int num){
        int l = -1;  //小于区域
        int r = arr.length;   //大于区域
        int flag = 0;   //指针
        while (flag<r){
            if (arr[flag]<num){
                swap(arr,++l,flag++);
            }else if (arr[flag]>num){
                swap(arr,--r,flag);
            }else if (arr[flag]==num){
                flag++;
            }
        }
    }
    @Test
    public void test1(){
        int[]arr = {1,3,4,5,0,9,8,3,4,55,2,8,9};
        sort_q1(arr,9);
        for (int i : arr) {
            System.out.print(i+",");
        }
    }

    /*
    问题二(荷兰国旗问题)：给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组中间，大于num的数放在数组的右边。
                      要求额外空间复杂度O(1)，时间复杂度O(N)
                情况一：[i]<num,则[i]和小于区下一个做交换，且小于区扩大一个格，并且指针i++
                情况二：[i]==num,则指针i++
                情况三：[i]>num,[i]和大于区前一个做交换，大于区往左扩，指针不变
     */
    public void sort_q2(int[]arr,int num){
        int l = -1;  //小于区域
        int r = arr.length;   //大于区域
        int flag = 0;   //指针
        while (flag<r){
            if (arr[flag]<num){
                swap(arr,++l,flag++);
            }else if (arr[flag]>num){
                swap(arr,--r,flag);
            }else if (arr[flag]==num){
                flag++;
            }
        }
    }
    @Test
    public void test2(){
        int[]arr = {1,3,4,5,0,9,8,3,4,55,2,8,9};
        sort_q2(arr,9);
        for (int i : arr) {
            System.out.print(i+",");
        }
    }





    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
