package Lesson2;

public class 递归 {

    /*
    >>1 等同于除以2 /2
     */
    public static int process(int[]arr,int l ,int r){
        if(l==r){return arr[l];}
        int mid = l +((r-l)>>1);
        int leftMax = process(arr,l,mid);
        int rightMax = process(arr,mid+1,r);
        return Math.max(leftMax,rightMax);

    }


}
