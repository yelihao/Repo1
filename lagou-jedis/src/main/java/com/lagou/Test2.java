package com.lagou;

import redis.clients.jedis.Jedis;

import javax.swing.border.Border;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 常用API
 */
public class Test2 {
    public static void main(String[] args) {
        Test2 t = new Test2();
        t.testZset();

    }

    private void testString(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //String
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");

        Set<String> set = jedis.keys("*");
        Iterator<String> iterator = set.iterator();
        for (set.iterator();iterator.hasNext();){
            String k = iterator.next();
            System.out.println(k+">"+jedis.get(k));
        }
        //查看k2是否存在
        Boolean k2 = jedis.exists("k2");
        System.out.println(k2);

        //查看k1过期时间
        System.out.println(jedis.ttl("k1"));
        jedis.mset("k4","v4","k5","v5");
        System.out.println(jedis.mget("k1","k2","k3","k4","k5"));
        System.out.println("==================================");
    }

    private void testList(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //list
        jedis.lpush("list01","l1","l2","l3","l4","l5");
        List<String> list01 = jedis.lrange("list01", 0, -1);
        for (String s : list01) {
            System.out.println(s);
        }
        System.out.println("==================================");
    }

    private void testSet(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //set
        jedis.sadd("order","jd001");
        jedis.sadd("order","jd002");
        jedis.sadd("order","jd003");
        Set<String> order = jedis.smembers("order");
        Iterator<String> iterator1 = order.iterator();
        while (iterator1.hasNext()){
            String s = iterator1.next();
            System.out.println(s);
        }
        jedis.srem("order","jd002");
        System.out.println(jedis.smembers("order").size());

    }

    private void testHash(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.hset("hash01","username","james");
        System.out.println(jedis.hget("hash01","username"));

        HashMap<String, String> map = new HashMap<>();
        map.put("name","tom");
        map.put("sex","boy");
        map.put("address","beijing");

        jedis.hmset("hash2",map);
        List<String> list = jedis.hmget("hash2", "name", "sex");
        for (String s : list) {
            System.out.println(s);
        }

    }

    private void testZset(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.zadd("zset01",60d,"zs1");
        jedis.zadd("zset01",70d,"zs2");
        jedis.zadd("zset01",80d,"zs3");
        jedis.zadd("zset01",90d,"zs4");

        Set<String> zset01 = jedis.zrange("zset01", 0, -1);
        Iterator<String> iterator = zset01.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            System.out.println(s);
        }

    }



}
