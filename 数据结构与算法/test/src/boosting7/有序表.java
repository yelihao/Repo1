package boosting7;

public class 有序表 {
    //所有操作都是O(logN)
    //哪些操作可以实现有序表：红黑树，AVL，SB，跳表

    //搜索二叉树：没有平衡性，他的时间复杂度不会一直是O（logN）
    //删除搜素树节点
    /*
    S1：没有左右孩子：则直接删除该节点
    S2：只有一个孩子：删除该节点，让其孩子代替他
    S3：有左右孩子:可以用左子树最右的节点替，也可以用右树最左的节点代替
     */

    /*
    AVL：在搜索树的基础上增加了左右旋以保持平衡。通过每棵树的左右高度不超过2，超过则左右旋
    LL型：左孩子太长 ----- 做右旋转
    LR型：左孩子的右孩子过长  ------   让左孩子右孩子转成头 ---- 先子树左旋，在整树右旋
    RR型：右孩子太长 ----- 做左旋转
    RL同
     */
    //在这里定义各种操作
    public static class AVLTree{
        //定义节点
        static class AvlNode {
            int data;
            AvlNode lchild;//左孩子
            AvlNode rchild;//右孩子
            int height;//记录节点的高度
        }

        //计算节点的高度
        static int height(AvlNode T) {
            if (T == null) {
                return -1;
            }else{
                return T.height;
            }
        }

        //左左型，右旋操作
        static AvlNode R_Rotate(AvlNode K2) {
            AvlNode K1;

            //进行旋转
            K1 = K2.lchild;
            K2.lchild = K1.rchild;
            K1.rchild = K2;

            //重新计算节点的高度
            K2.height = Math.max(height(K2.lchild), height(K2.rchild)) + 1;
            K1.height = Math.max(height(K1.lchild), height(K1.rchild)) + 1;

            return K1;
        }

        //进行左旋
        static AvlNode L_Rotate(AvlNode K2) {
            AvlNode K1;

            K1 = K2.rchild;
            K2.rchild = K1.lchild;
            K1.lchild = K2;

            //重新计算高度
            K2.height = Math.max(height(K2.lchild), height(K2.rchild)) + 1;
            K1.height = Math.max(height(K1.lchild), height(K1.rchild)) + 1;

            return K1;
        }

        //左-右型，进行左旋，再右旋
        static AvlNode R_L_Rotate(AvlNode K3) {
            //先对其孩子进行左旋
            K3.lchild = R_Rotate(K3.lchild);
            //再进行右旋
            return L_Rotate(K3);
        }

        //右-左型，先进行右旋，再左旋
        static AvlNode L_R_Rotate(AvlNode K3) {
            //先对孩子进行右旋
            K3.rchild = L_Rotate(K3.rchild);
            //在左旋
            return R_Rotate(K3);
        }

        //插入数值操作
        static AvlNode insert(int data, AvlNode T) {
            if (T == null) {
                T = new AvlNode();
                T.data = data;
                T.lchild = T.rchild = null;
            } else if(data < T.data) {
                //向左孩子递归插入
                T.lchild = insert(data, T.lchild);
                //进行调整操作
                //如果左孩子的高度比右孩子大2
                if (height(T.lchild) - height(T.rchild) == 2) {
                    //左-左型
                    if (data < T.lchild.data) {
                        T = R_Rotate(T);
                    } else {
                        //左-右型
                        T = R_L_Rotate(T);
                    }
                }
            } else if (data > T.data) {
                T.rchild = insert(data, T.rchild);
                //进行调整
                //右孩子比左孩子高度大2
                if(height(T.rchild) - height(T.lchild) == 2)
                    //右-右型
                    if (data > T.rchild.data) {
                        T = L_Rotate(T);
                    } else {
                        T = L_R_Rotate(T);
                    }
            }
            //否则，这个节点已经在书上存在了，我们什么也不做

            //重新计算T的高度
            T.height = Math.max(height(T.lchild), height(T.rchild)) + 1;
            return T;
        }
    }



    /*
    SB树：也有左右旋。
    左右旋条件：B树的大小，大于等于侄子树的大小
     */

    /*
    红黑树：每个节点都有标签，不是红就是黑（基本不用）
        头节点和叶节点（红黑树的叶节点是最底层的空节点）必须为黑
        任何俩个红节点不能相邻
        对于任何一个子树，从cur当前头，到每条结束，要求黑节点数量一样
     */







}
