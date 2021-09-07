package com.lagou;

import redis.clients.jedis.Jedis;

public class TestJedisPoolUtil {
    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedis();
        Jedis jedis1 = JedisPoolUtil.getJedis();
        System.out.println(jedis==jedis1);
    }
}
