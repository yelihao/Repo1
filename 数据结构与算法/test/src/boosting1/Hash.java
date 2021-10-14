package boosting1;

import java.util.HashMap;

public class Hash {
    /*
    Hash函数有均匀性
     */
    /*
    哈希表在使用上可以认为是O(1)的
    但是理论上，复杂度是O(N)
     */

    /**
     * Q1:设计RandomPool结构，给结构有如下三个功能:
     *     insert(key):将某个key加入到该结构，做到不重复加入
     *     delete(key):将原本在结构中的某个key移除
     *     getRandom():等概率随机返回结构中的任何一个key
     *   要求：各方法的时间复杂度都是O(1)
     */
    /*
    解法:O(1)用到简单的HashMap
         insert
         1:准备俩张Map表，map1(str -> index ,int size = 0),map2(index -> str, int size = 0)
         2:若放入"A" 则map1中有("A",0) map2(0,"A")  size = 1
         3:加入"B"  map1("B",1) ,map2(1,"B")  size = 2
         .......

         delete
         删除一个，然后让最后一个(通过int size知道最后一个的位置),去填删除掉的这个的洞

         getrandom
         加到Z size == 26
         用系统上随机函数生成0~26的数,然后利用map2返回
     */
    public static class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<K,Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        public void insert(K key){
            //没有就加入map
            if (!keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key,this.size);
                this.indexKeyMap.put(this.size,key);
                this.size++;
            }
        }

        public void delete(K key){
            if (this.keyIndexMap.containsKey(key)){
                //获取要删除的索引
                int deleteIndex = this.keyIndexMap.get(key);
                //获取最后一个替补的位置然后获得替补的信息
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                //信息互换
                this.keyIndexMap.put(lastKey,deleteIndex);
                this.indexKeyMap.put(deleteIndex,lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);

            }
        }

        public K getRandom(){
            if (this.size==0){return null;}
            int randomIndex = (int)(Math.random() * this.size); //0~ size-1
            return this.indexKeyMap.get(randomIndex);
        }
    }

    /**
     * Q2:布隆过滤器-----极大程度降低内存消耗，但是失误率不可避免
     *      1.确定是否是布隆过滤器模型
     *      2.知道样本量n 和 失误率P
     */







}
