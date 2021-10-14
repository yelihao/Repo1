package middle1;

public class Buy {
    /**
     * Q1：小虎去附近买苹果，想买n个，但是只能买6或8个每袋，返回尽可能少的袋子，如果无论如何都装不下则返回-1
     */
    /*
    普通解法：先用8个袋子装，在用6个袋子看
     */
    public static int minBags(int apple){
        if (apple < 0){
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple/8;
        int rest = apple - 8 * bag8;
        //24是用于提早结束循环
        while (bag8 >= 0 && rest < 24){
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1){
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }
    private static int minBagBase6(int rest) {
        return rest % 6 ==- 0 ? (rest/6) : -1 ;
    }

    /*
    面对一个题：输入输出都是一个int，写最傻的代码，分析一下值的输出规律，进而改进
     */

    /**吃草问题
     * @param n 共多少草
     *          每次可选择吃 4的几次方草
     * @return
     */
    public static String winner1(int n){
        //0  1  2  3  4
        //后 先 后 先  先
        if (n < 5){
            return (n == 0||n ==2)? "后手":"先手";
        }
        //n >= 5
        int base = 1;//先手决定吃草
        while (base <= n){
            //当前共n份，先手吃base份，n-base是留给后手的
            if (winner1(n - base).equals("后手")){
                return "先手";
            }
            if (base > n/4){    //防止base * 4 之后溢出
                break;
            }
            base *= 4;
        }
        return "后手";
     }

    //找完规律后
    public static String winner2(int n){
        if (n %5 ==0 ||n%5==2){
            return "后手";
        }else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<100;i++){
            System.out.println(i +":"+winner1(i));
        }
    }


}
