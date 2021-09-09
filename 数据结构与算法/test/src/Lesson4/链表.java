package Lesson4;

import java.util.Stack;

public class 链表 {
    /*
    重要技巧：1.额外数据结构记录（哈希表等）
            2.快慢指针
     */
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    /**
     * Q1：判断一个链表结构是否为回文结构
     *    给定一个单链表的头节点head，请判断该链表是否为回文结构
     * @example：1 -> 2 -> 2 -> 1 return true  1 -> 2 -> 3 return false
     */
    //方法一：依次把链表中的点的值放到栈里去，然后再从栈里弹出和链表对比即可
    //栈是先入后出的结构
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //方法二：快慢指针，快指针一次走两步，慢指针一次走一步，快指针走完的时候，慢指针来到中点位置(只需要n/2的额外空间)
    public static boolean isPalindrome2(Node head){
        if (head == null ||head.next == null){
            return true;
        }
        Node right = head.next; //慢指针
        Node cur = head;    //快指针
        while (cur.next != null && cur.next.next !=null){   //走完时，慢指针在右边的头，而不是在中间位置(无论奇偶)
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null){
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }


    /**
     * Q2:将单向链表按某值划分成左边小，中间相等，右边大的形式
     * @param ：head 头节点
     *         pivot 整数值
     */
    //方法一：Node[]数组，之后做快排操作即可

    //方法二：预先设计6个值：SH，ST小于部分的头，小于部分的尾  EH，ET等于部分， BH，BT大于部分
    //最后一定要注意分开边界：即小于部分可不可能null
    public static Node listPartition2(Node head,int pivot){
        Node sH = null; //small head
        Node sT = null; //small tail
        Node eH = null; //equal head
        Node eT = null; //equal tail
        Node mH = null; //big head
        Node mT = null; //big tail
        Node next = null; //save next node
        //every node distributed to three node lists
        while (head != null){
            next = head.next;
            head.next = null;   //将此节点脱离链表
            if (head.value < pivot){
                //对应刚开始的情况
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = head.next;
        }
        //区域连接
        if (sT != null){    //如果有小于区域
            sT.next = eH;
            eT = eT == null? sT:eT; //如果等于区域为null，则忽略等于区域
        }
        if (eT != null){
            eT.next = mH;
        }
        return sH != null ? sH :(eH !=null? eH:mH);
    }



}
