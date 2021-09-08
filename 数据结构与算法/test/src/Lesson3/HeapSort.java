package Lesson3;

public class HeapSort {

    //大根堆：即每一数在他的数结构下，他都是最大的数
    //思想：就是把每一个数都与父节点比较，如果比他大则交换
    //父节点：(i-1)/2

    //以下俩个就是堆结构最重要的俩个堆调整

    //某个数在index位置，往上继续移动
    public static void heapInsert(int[]arr,int index){
        while (arr[index] >arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            //继续判断这个节点能否继续上移
            index = (index-1)/2;
        }
    }

    //堆化，即某个数在index位置，能否往下移动
    //即把他和他的俩个子节点中较大的那个子节点进行比较，如果子节点较大，则swap，否则就结束循环
    public static void heapify(int[]arr, int index, int heapSize){
        int left = index*2+1;   //左孩子下标 +1则是右孩子
        //heapSize:堆大小，如果左孩子超出堆大小，则已经无孩子，跳出循环
        while(left<heapSize){
            //比较俩个孩子，看谁的值大，较大值的下标给largest
            //第一个表示还有右孩子情况，否则直接传递左孩子
            int largest = left+1<heapSize && arr[left+1] > arr[left] ? left+1 : left;
            //比较父节点和较大孩子谁大
            largest = arr[largest] > arr[index]? largest:index;
            if (largest == index){
                break;
            }
            swap(arr,index,largest);
            index = largest;
            left = index * 2 + 1;
        }

    }

    public static void swap(int[]arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    //堆排序
    //每一次都从堆中找到堆顶，最大值，然后把他移到heapsize的位置，之后heapsize--
    //每一次循环的过程，就是从arr的第一个位置开始，列堆，arr中每一次都执行往上移动
    //每一次都可以排好一个数
    public static void heapSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        for (int i = 0;i < arr.length;i++){
            heapInsert(arr,i);  //对每一个堆位置往上移动，不能往上移动则下一个位置
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);   //排好最大值位置
        while (heapSize > 0){
            //因为0位置俩个子节点都是大跟堆，都是他那颗树的最大值，所以和下面俩个数换就行了
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }

    }







}
