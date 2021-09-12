package Lesson5;

import java.util.HashMap;
import java.util.HashSet;

public class Practice3 {
    /*
二叉树节点结构
 */
    class Node<V>{
        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left,Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * Q1:给定两个二叉树的节点node1和node2，找到他们的最低公共祖节点
     */
    //方法一o1,o2一定属于head为头的树
    public static Node LCA(Node head,Node o1,Node o2){
        //把每一个节点的父节点存储到表中
        HashMap<Node,Node> fatherMap = new HashMap<>();
        fatherMap.put(head,head);
        process(head,fatherMap);
        HashSet<Node> set01 = new HashSet<>();
        set01.add(o1);
        Node cur = o1;
        while (cur != fatherMap.get(cur)){
            set01.add(cur);
            cur = fatherMap.get(cur);
        }
        set01.add(head);
        //然后再让o2往上窜,后面不写了
        return null;
    }

    public static void process(Node head,HashMap<Node,Node> map){
        if (head == null){
            return;
        }
        map.put(head.left,head);
        map.put(head.right,head);
        process(head.left,map);
        process(head.right,map);
    }
    //方法二：
    public static Node lowestAncestor(Node head,Node o1,Node o2){
        if (head ==null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestAncestor(head.left,o1,o2);
        Node right = lowestAncestor(head.right,o1,o2);
        //这个if中了，说明head就是汇聚点，往上扔就行了
        if (left !=null && right != null){
            return head;
        }
        //左右俩颗树，并不都有返回值
        return left != null ? left : right;
    }

    /**
     * 折纸问题：仔细分析，就是中序遍历问题
     */
    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }
    /*
    递归过程，来到了某一个节点
    i是节点的层数，N一共的层数，down==true 凹 ,down==false 凸
    只使用了n的时间
     */
    public static void printProcess(int i,int N,boolean down){
        if (i>N){
            return;
        }
        printProcess(i+1,N,true);
        //中序遍历的位置
        System.out.println(down ? "凹":"凸");
        printProcess(i+1,N,false);
    }


}
