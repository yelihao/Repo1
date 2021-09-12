package Lesson5;

import java.util.Stack;

public class 二叉树 {
    /*
    二叉树节点结构
     */
    class Node<V>{
        V value;
        Node left;
        Node right;
    }

    //递归序遍历
    public static void f(Node head){
        //1
        if (head == null){
            return;
        }
        //1

        //2
        f(head.right);
        //2

        //3
        f(head.left);
        //3
    }

    //递归方法前序遍历      在第一次碰到节点的时候打印
    public static void preOrderRecur(Node head){
        if (head == null){return;}
        System.out.println(head.value + ",");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //递归方法中序遍历      在第二次碰到节点的时候打印
    public static void inOrderRecur(Node head){
        if (head == null){return;}
        inOrderRecur(head.left);
        System.out.println(head.value + ",");
        inOrderRecur(head.right);
    }

    //递归方法后序遍历      在第三次碰到节点的时候打印
    public static void aferOrderRecur(Node head){
        if (head == null){return;}
        aferOrderRecur(head.left);
        aferOrderRecur(head.right);
        System.out.println(head.value + ",");
    }


    //非递归遍历(使用栈)
    //先序遍历： (1)先把头节点放入栈中
    //(头左右)  *(2)从栈中弹出一个节点
    //          (4)处理弹出的节点cur（打印）
    //          (5)先把右节点压入栈，再把左节点压入栈(如果有的话  )
    //         *(6)返回2
    public static void preOrderUnRecur(Node head){
        System.out.print("pre-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            //(1)
            stack.push(head);
            //没弹完则继续弹
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null){
                    stack.push(head.right);
                }
                if (head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //后序遍历： (1)先把头节点放入栈中
    //(左右头)  *(2)从栈中弹出一个节点
    //          (3)把弹出结点放入收集栈
    //          (4)先把左节点压入栈，再把右节点压入栈(如果有的话  )
    //         *(5)返回2
    //不用收集栈的话是：头右左，因为压入了收集栈在弹出，所以反一反，成为了左右头
    public static void afterOrderUnRecur(Node head){
        System.out.print("in-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            //(1)
            stack.push(head);
            //没弹完则继续弹
            while (!stack.isEmpty()){
                head = stack.pop();
                stack2.push(head);
                if (head.left != null){
                    stack.push(head.left);
                }
                if (head.right != null){
                    stack.push(head.right);
                }
            }
            //从收集栈依次弹出即可实现后续遍历
            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().value+" ");
            }
        }
        System.out.println();
    }

    //中序遍历： (1)先把整颗树左边界全部进栈
    //(左头右)  (2)从栈中弹出一个节点
    //         (3)处理弹出的节点cur（打印）
    //         (4)如果弹出节点有右节点，则对弹出结点的右结点做(1)
    public static void inOrderUnRecur(Node head){
        System.out.print("in-order");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head !=null){
                if (head!=null){
                    stack.push(head);
                    head = head.left;
                }else {
                    //左树已经到头来，则可以pop了,然后pop时也可以观察有无右树
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }

            }
        }
        System.out.println();
    }








}
