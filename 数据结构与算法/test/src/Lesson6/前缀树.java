package Lesson6;

public class 前缀树 {

    /**
     *  一个字符串类型的数组arr1，另一个字符串类型的数组arr2。arr2中有哪些字符，是arr1中出现的？请打印。arr2中有哪些字符，是作为arr1中某个
     * 字符串前缀出现的？请打印。arr2中有哪些字符，是作为arr1中某个字符串前缀出现的？请打印arr2中出现次数最大的前缀
     */
    public static class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;    //HashMap<Char,Node> nexts

        public TrieNode() {
            pass = 0;   //通过这个字母节点的个数
            end = 0;    //以这个字母节点为结束的个数
            //nexts[0] == null 没有走向'a'的路
            //nexts[1] != null 有走向'b'的路
            //...
            //nexts[25] != null 有走向'z'的路
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        //默认
        private TrieNode root;

        public Trie() {
        }

        //对每一个单词
        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0;i<chs.length;i++){   //从左到右遍历字符
                index = chs[i] - 'a';   //由字符转换成该字符对应的 nexts中的路
                if (node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];   //走过这条路
                node.pass++;
            }
            //走完了 到end了，结尾++
            node.end++;
        }

        //word 这个单词之前加入过几次
        public int search(String word){
            if (word == null){return 0;}
            char[] chs = word.toCharArray();
            TrieNode node =root;
            int index = 0;
            for (int i = 0;i<chs.length;i++){
                //找到对应字符
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            //此时没返回0，则root已经是结束值了
            return root.end;
        }

        //delete，删除树中某个单词痕迹
        /*
        1.先查询有无这个单词，没有就不用删除
        2.如果最后他的end>=1，则删除沿途的pass,最后一个end--
         */
        public void delete(String word){
            if (search(word)!=0){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i=0;i<chs.length;i++){
                    index = chs[i] - 'a';
                    node.nexts[index].pass--;
                    //如果pass变成0，那么就没有痕迹了
                    if (node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                //单词走完了 还有更长的单词，或者之前打入了俩次，则
                node.end--;
            }

        }

        //在所有加入的字符中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null){return 0;}
            char[] chs = pre.toCharArray();
            TrieNode node =root;
            int index = 0;
            for (int i = 0;i<chs.length;i++){
                //找到对应字符
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            //此时没返回0，则root已经是结束值了
            return root.pass;
        }

    }





}
