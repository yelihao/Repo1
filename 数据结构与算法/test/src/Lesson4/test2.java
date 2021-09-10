package Lesson4;

public class test2 {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    /*
    两链表相交的一系列问题
     */

    /**
     * Q1:解决单链表有无环问题：
     *      如果有环，则快慢指针一定会相遇，若没有环，快指针一定会先指向null
     *      如果快慢指针相遇，则让快指针回到head，而慢指针停在原地，接下来俩个节点都每一次走一步，则一定会在第一个入环节点相遇
     */
    //找到单链表第一个入环节点，如果无环，则返回null
    public static Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;    //至少三个节点才能形成环
        }
        Node n1 = head.next;    //n1 -> slow
        Node n2 = head.next.next;    //n2 -> fast
        while (n1 != n2){
            //该链表开环
            if (n2.next ==null || n2.next.next == null){
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = head;  //把n2回归head，重新追赶n1
        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n2;
    }

    /**
     * Q2:给定俩个可能有环，也可能无环的单链表，头节点head1和head2。请实现一个函数，如果俩个链表相交，请返回相交的第一个节点。如果不相交
     *    则返回null
     * 要求：时间复杂度O(N),空间复杂度O(1)
     */
    //如果没有空间复杂度要求，可以直接使用HashSet
    //O（1）方法是使用快慢指针
    /*
    对俩个链表分别看看是否有环：
    case1：lopp1==null==loop2
                则先走一遍head1，记录他的长度len1和尾节点end1，对head2同理可的len2和end2
                先判断end1和end2内存地址是否是一个，如果不是一个，则不可能相交
                若是一个，则去找相交点：链表1先走(len1-len2)步，之后俩个链表一起走，则一定会在第一个相交节点相遇
    case2：一个null，一个返回node 则直接return
    case3：均有环
     */
    //case1：均无环
    public static Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;  //记录head1和head2链表长度差
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        //此时cur1和cur2都走到了最后,对应不相交情况
        if (cur1 != cur2){
            return null;
        }
        //用n是否大于0判断哪个长，然后把cur1弄成长的，cur2弄成短的
        cur1 = n > 0 ? head1: head2;
        cur2 = n > 0 ? head2: head1;
        n = Math.abs(n);    //变成长度
        while (n != 0){
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
    //case2:直接返回null
    /*case3：三种情况：情况1：俩个链表的环不是一个环
                     情况2：俩个链表的环入环结点不是一个
                       以上俩种情况，就用loop1走一圈环，看看能不能遇到loop2，能遇到则是情况2返回loop1,2都可以，不能遇到则是情况1返回null
                     情况3：俩个链表是一个入环节点：把入环节点看作end，用均无环的方法即可
     */
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        // 情况三
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1:head2;
            cur2 = n > 0 ? head2:head1;
            n = Math.abs(n);
            while (n != 0){
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2 ){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            //走一圈loop
            cur1 = loop1.next;
            //遇到loop2
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            //不遇到
            return null;
        }
    }


}
