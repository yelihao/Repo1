package Lesson5;

public class Practice2 {

    /*
    二叉树节点结构
     */
    class Node<V>{
        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     *Q1:如何判断一棵二叉树是否是搜索二叉树(所有都是左树小，中间中，右树大)
     *  方法一：中序遍历后，一定是升序的
     *  方法二：递归套路
     */
    /*以下是方法二：
            判断条件：1）左树是搜索二叉树，右树也得是搜索二叉树
                    2）左树上的最大值 < cur < 右树上的最小值
                    所以需要要左树是否为搜索二叉树以及最大值，右树是否为搜索二叉树和最小值
                    因为递归返回的信息是一样的，所以我们就不管几个信息，都要了就完事了
     */
    public static class ReturnData{
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnData process2(Node x){
        if (x == null){
            return null;
        }
        ReturnData leftData = process2(x.left);
        ReturnData rightData = process2(x.right);

        int min = x.value;
        int max = x.value;
        if (leftData != null){
            min = Math.min(min,leftData.min);
            max = Math.max(max,leftData.max);
        }
        if (rightData != null){
            min = Math.min(min,rightData.min);
            max = Math.max(max,rightData.max);
        }

        boolean isBST = true;
        //是否违规
        if ( leftData !=null && (!leftData.isBST || leftData.max >= x.value)){
            isBST = false;
        }
        if ( rightData !=null && (!rightData.isBST || rightData.min <= x.value)){
            isBST = false;
        }
        //不违规则为true
        return new ReturnData(isBST,min,max);
    }




    /**
     * Q2:如何判断一颗二叉树是否是完全二叉树(每一层都是满的，最后一层可以不满，但是一定是从左到右的)
     *  方法：按宽度遍历，依次遇到的结点：1）如果有右孩子，无左孩子，返回false
     *                              2）在条件一不违规情况下，如果遇到第一个左右孩子不双全的情况，那么接下来遇到的所有结点都要为叶结点(无孩子)
     */


    /**
     * Q3:满二叉树判断：已知最大深度L和结点个数N：N = 2^l - 1
     */
    //树形DP法：左右是否是满二叉树，
    public static boolean isF(Node head){
        if (head == null){return true;}
        ReturnData2 rd = process3(head);
        return rd.nodes == (1<< rd.height - 1);
    }
    public static class ReturnData2{
        public int height;  //深度
        public int nodes;   //个数

        public ReturnData2(int h,int n ){
            height = h;
            nodes = n;
        }
    }
    public static ReturnData2 process3(Node x){
        if (x == null){
            return new ReturnData2(0,0);
        }
        ReturnData2 leftData = process3(x.left);
        ReturnData2 rightData = process3(x.right);
        int h = Math.max(leftData.height,rightData.height) + 1;
        int n = 1 + leftData.nodes +rightData.nodes;
        return new ReturnData2(h,n);
    }



    /**
     *Q4: 平衡二叉树判断：
     */
    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    public static class ReturnType{
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static ReturnType process(Node x){
        if (x == null){     //base case
            return new ReturnType(true,0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height,rightData.height)+1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                            && Math.abs(leftData.height - rightData.height)<2;
        return new ReturnType(isBalanced,height);
    }



    /**
     * 二叉树套路：使用递归
     * 解决树形DP
     */
    //使用ReturnData：总结所有需要左树的信息和右树的信息以及所有的可能性


}
