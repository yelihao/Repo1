package Lesson1;

public class 二分法 {

    //局部最小值问题
    //描述：无序数组中，任意相邻数之间一定不相等
    //求一个局部最小的位置；   要求：时间复杂度好于o(N)

    public static int findmin(int[] arr){
        if (arr[0]<arr[1]){
            return 0;
        }        int length = arr.length-1;
        if (arr[length]<arr[length-1]){
            return length;
        }

        //二分策略
        int l = 0,r=length;
        while (l<r){
            int mid = (l+r)/2;
            int mid_l=mid-1,mid_r=mid+1;
            if (arr[mid]<=arr[mid_l]&&arr[mid]<=arr[mid_r]){
                return mid;
            }else if (arr[mid]<=arr[mid_l] && arr[mid]>=arr[mid_r]){
                l = mid_l;
            }else {
                r = mid_r;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[]arr = {6,5,8,5,4,3,1,5,8,10};
        int findmin = findmin(arr);
        System.out.println(findmin);
    }

}
