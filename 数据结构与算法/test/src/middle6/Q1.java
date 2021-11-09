package middle6;

import java.util.HashSet;

public class Q1 {

    /**
     * 牛牛准备参加春游，牛牛的背包容量为w。
     * 牛牛家中共有n袋零食，第i袋零食的提及为v[i]
     * 牛牛想知道在总体积不超过背包容量的情况下，他一共有多少种零食放法
     */
    /*
    经典动态规划 f[用此位置的货，bag-v[i]]+f[不用此位置的货，bag]
     */
    public static void main(String[] args) {
        String s = "3123124";

        char[] ch1 = s.toCharArray();
        System.out.println(ch1[1]);

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        set.add(3);
        set.add(5);
        set.add(4);
        set.add(2);
        set.add(3);
        set2.add(3);
        set2.add(0);
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(9);
        set2.add(4);
        Object[] ss = set2.toArray();
        for (Object o : ss) {
            if (set.contains(o)){
                System.out.println("yes");
            }
        }
    }



}
