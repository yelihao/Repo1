package Lesson2;

public class QuickSort {
    //做三划分，然后l，r继续做荷兰国旗，但是时间复杂度为O(N2)，不过num用随机一个数，把他移动到最后一个位置
    //时间复杂度：O(N*logN)
    public static void quickSort(int[] arr){
        //排好序了
        if (arr == null || arr.length < 2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    //arr[l···r]排序
    public static void quickSort(int[] arr ,int l ,int r){
        if (l<r){
            //随机抽一个数与最后一个数做交换，然后在l到r—1，做左半边小于arr[r]，中间等于arr[r]，右边大于arr[r]
            //最后再把右边区域的第一个值与arr[r]交换，则成功排列好一组数的位置
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int []p = partition(arr,l,r);
            //对左右半边在进行快排操作
            quickSort(arr,l,p[0]-1);    //对左边界继续快排
            quickSort(arr,p[1]+1,r);    //对右边界继续快排
        }
    }

    //以下是处理arr[1····r]的函数，思想使用：然后在l到r—1，做左半边小于arr[r]，中间等于arr[r]，右边大于arr[r]
    //即：默认以arr[r]做划分，其初始值为p,   <p   =p    >p
    //返回等于区域的左边界，右边界（即第一个等于区域的和最后一个等于区域的值）,所以返回一个长度为2的数组res,res[0],res[1]
    public static int[] partition(int[] arr, int l, int r){
        int less = l-1;     //小于区域的右边界
        int more = r;     //大于区域的左边界
        //判断结束的标识为：指针碰到大于区域
        while (l<more){ //l表示当前数的位置，arr[r]为划分值
            //如果指针的数小于 随机数
            if (arr[l]<arr[r]){
                //与左边届右边一个数交换，且左边界增加，之后指针增加
                swap(arr,++less,l++);
            }else if (arr[l]>arr[r]){
                //与右边界左边一个数交换，且右边界减一，指针不动
                swap(arr,l,--more);
            }else if (arr[l]==arr[r]){
                //指针++
                l++;
            }
        }
        //跳出循环后，把最后一个数与右边界第一个数交换
        swap(arr,r,more);
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int []arr ={3,2,8,9,11,2,3,4,5,1,3,2,3,4,1,22,13,9};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i+",");
        }
    }


}
