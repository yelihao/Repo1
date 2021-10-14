package Lesson7;

import org.junit.Test;

public class Practice1 {

    /**暴力递归就是尝试(？回溯？)
     * 1.把问题转化为规模缩小了的同类问题的子问题
     * 2.有明确的不需要继续进行递归的条件(base case)
     * 3.有当得到了子问题的结果之后的决策过程
     * 4.不记录每一个子问题的解
     */

    /**
     * Q1:汉诺塔问题:打印n层汉诺塔从最左边移动到最右边的全部过程
     */
    public static void hanoi(int n){
        if (n > 0){
            func(n,"左","右","中");
        }
    }

    //1~i 圆盘目标是from -> to,other是另外一个
    public static void func(int i,String start,String end,String other){
        if (i == 1){    //base case 只有一片情况直接挪
            System.out.println("Move 1 from " + start + " to " +end);
        }else {
            func(i - 1,start,other,end);
            //把最大的移动到end
            System.out.println("Move " + i + " from " +start + " to " + end);
            //其他的移动回start
            func(i - 1,other,end,start);
        }
    }
    @Test
    public void test(){
        hanoi(3);
    }


    /**
     * Q2:给定一个整形数组arr,代表数值不同的纸牌排成一条线.玩家A和玩家B依次拿走每张纸牌,规定玩家A先拿,玩家B后拿,但是每个玩家每次只能拿走
     *    最左或最右的纸牌,玩家A和玩家B都绝顶聪明.请返回最后获胜者的分数.
     */




}
