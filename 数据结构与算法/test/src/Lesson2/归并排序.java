package Lesson2;

public class 归并排序 {
    /*
    简单的递归，就是左边排好序，右边排好序，让整体有序（整体有序过程，利用左右俩个空间各方一个指针比较指针大小，小的拷贝，并且指针+1）
    时间复杂度O(N*logN)
     */

    public static void mergeSort(int[]arr){
        if (arr == null || arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    //递归过程
    public static void process(int[]arr,int l,int r){
        //一个数则有序
        if (l==r){
            return;
        }
        int mid = l + ((r-l)>>1);
        process(arr,l,mid); //让左侧有序
        process(arr,mid+1,r);   //让右侧有序
        merge(arr,l,mid,r);     //整体有序
    }

    public static void merge(int[]arr,int l,int m,int r){
        //辅助空间，l到r上有多少个数，就开多大
        int[] help = new int[r-l+1];
        int i = 0;  //给help数组使用的变量
        int p1 = l;     //对于arr数组
        int p2 = m+1;   //对于arr数组使用
        while (p1 <= m && p2 <= r){     //都没越界
            //谁小copy到help里去，然后小的指针往后一格
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]:arr[p2++];
        }
        //从上一个while出来，一定会产生一边越界，一边不越界
        //p1没越界，则后续剩下没拷贝的都拷贝至help
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        //p2没越界
        while (p2 <= r){
            help[i++] = arr[p2++];
        }
        for (i=0; i<help.length; i++){
            arr[l+i] = help[i];
        }
    }




}
