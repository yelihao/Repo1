package middle2;

public class 完整括号 {

    /**
     * 左右括号能否包括
     */
    /*
    用有限几个变量，遍历字符串，用一个变量count遍历
    有(,count++, ),count--
    count小于0，肯定不完整
    遍历完后count必须为0
     */

    /**
     * 补充：给以左右括号字符串，需要补多少个(和)使其成为完整括号
     */
    /*
    同上，不过需要俩个变量ans 和 count
    在遍历时候，如果count<0，则ans++且count=0；最后count剩余的的数量加到ans中
     */


}
