import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.PriorityQueue;

//计算夜宵
public class test1 {

    /**
     *
     * @param XM:X 总价，M商品数量
     * @param xi：每一个商品的数量
     * @return
     */
    public static int calculatenum(int[] XM, int[] xi){
        int X = XM[0];
        int M = XM[1];
        Arrays.sort(xi);
        int sum = 0 ;
        for(int i =M-1;i>0;i--){
            int slow =0;
            int fast =1;
            
        }
        if (sum==0){
            return -1;
        }
        return sum;
    }

    public static void main(String[] args) {
    }




}
