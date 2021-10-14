package boosting4;

public class 树形dp套路 {
    /*
    使用前提：如果题目求解目标是S规则，则求解流程可以定成以每一个节点为头节点的子树在S规则下的每一个答案，并且最终答案一定在其中
     */

    /**
     * Q1:二叉树节点间的最大距离问题
     * 描述：从二叉树节点a出发，可以向上走或者向下走，但沿途的节点只能经过一次，到达节点b时路径上的节点个数叫做从a到b的距离，求整颗树上的最大
     *      距离
     * 解法：最大距离-----(1)x不参与，则为左最大距离或右子树最大距离 (2)x参与，则为左高度+右高度+1
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }

    //信息返回体
    public static class Info{
        public int maxDistance; //最大距离
        public int height;      //高度
        public Info(int maxDistance, int height) {
            maxDistance = maxDistance;
            height = height;
        }
    }

    public static Info process(Node x){
        if (x == null){
            return new Info(0,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        //查看info
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int max = Math.max(p3,Math.max(p1,p2)); //求出这个点最大深度到底是什么
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        return new Info(max,height);
    }



}
