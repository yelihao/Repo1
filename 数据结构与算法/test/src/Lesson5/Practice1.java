package Lesson5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Practice1 {
    /*
    二叉树节点结构
     */
    class Node<V>{
        V value;
        Node left;
        Node right;
    }

    /**
    二叉树的宽度优先遍历(求一颗二叉树宽度)： 使用队列
                (1)把头结点放入队列
                (2)弹出，打印
                (3)然后先放入左再放入右
     */
    public static void w(Node head){
        if (head == null){return;}
        Queue<Node> queue = new LinkedList<Node>();
        if (head!=null){
            queue.add(head);
            while (!queue.isEmpty()){
                Node node = queue.poll();
                System.out.print(node.value+" ");
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        System.out.println( );
    }

    /**
     * 求最大宽度
     */
    //重点：知道每个节点是在第几层------用表结构HashMap
    public static int w2(Node head){
        if (head == null){return 0;}
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);

        //用于记录宽度
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);

        int currentLevel = 1;   //当前曾
        int currentLevelNodes = 0;  //当前层发现几个结点
        int maxLevel = Integer.MIN_VALUE;

        while (!queue.isEmpty()){
            Node node = queue.poll();
            int curNodelLevel = levelMap.get(node);     //当前结点所在第几层
            if (curNodelLevel == currentLevel){     //还在当前层
                currentLevelNodes++;
            }else {     //到下一层
                //结算这一层
                maxLevel = Math.max(currentLevelNodes,maxLevel);
                currentLevel++;
                currentLevelNodes = 1;
            }

            if (node.left!=null){
                levelMap.put(node.left,currentLevel+1);
                queue.add(node.left);
            }
            if (node.right!=null){
                levelMap.put(node.right,currentLevel+1);
                queue.add(node.right);
            }
        }
        return maxLevel;
    }


    /*
    二叉树的前序遍历就是深度优先遍历
     */



}
