package boosting4;

public class 二叉树morris遍历 {

    /*
    一种遍历二叉树的方式，并且时间复杂度o(N),额外空间复杂度o(1)
    通过利用原树中大量空闲指针的方式，达到节省空间的目的
     */
    /**
     * Morris 遍历细节
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1)如果cur没有左孩子，cur向右移动 cur = cur.right
     * 2)如果cur有左孩子，则找到左子树上最右的节点 mostRight
     *          a.如果mostRight的右指针指向孔，让其指向cur，然后cur向左移动(cur = cur.left)
     *          b.如果mostRight的右指针已经指向了cur，让其指向null，然后cur向右移动(cur = cur.right)
     * 3)cur为空时遍历停止
     */
    //了解，面试用



}
